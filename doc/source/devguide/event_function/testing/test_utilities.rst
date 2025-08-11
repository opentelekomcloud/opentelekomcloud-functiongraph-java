Test utilities
========================

The `opentelekomcloud-functiongraph-java-test` module provides
tools to ease Java FunctionGraph testing and is a test dependency.

The implementation is inspired by `aws-lambda-java-test <https://github.com/aws/aws-lambda-java-libs/tree/main/aws-lambda-java-tests>`_.

Key features
----------------

* Load events from json files and get them deserialized into Java Events.
* Inject Events directly in JUnit 5 tests, using the `@ParameterizedTest`
  annotation.


Usage
----------------

Events injection
^^^^^^^^^^^^^^^^^^^^

A set of annotations can be used to inject Events and/or to validate
handler responses against those Events.

.. note::
 All those annotations must be used in conjunction with the
 `@ParameterizedTest <https://junit.org/junit5/docs/current/api/org.junit.jupiter.params/org/junit/jupiter/params/ParameterizedTest.html>`_ annotation from Junit 5.


`@ParameterizedTest` enables to inject arguments into a unit test, so
you can run the same test one or more time with different parameters.
See `JUnit 5 User Guide <https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests>`_ for more details on this.

Event
""""""""""""""""""""""""""

The `@Event` annotation permits to inject one Event into a Junit test.

Example:

.. code-block:: java

  // the json file must be in the classpath (most often in src/test/resources)

  @ParameterizedTest
  @Event(value = "timer_event.json", type = TimerTriggerEvent.class)
  void testEvent(TimerTriggerEvent event) throws Exception {

    assertNotNull(event);

    // explicit create of TestContext()
    TestContext context = new TestContext();

    // call FunctionGraph
    TimerTriggerFG fg = new TimerTriggerFG();
    String ret = fg.handleRequest(event, context);
    ...

    }


Events
""""""""""""""""""""""""""

The `@Events` annotation permits to inject multiple Events into a Junit test.

Examples:

.. code-block:: java
    :caption: Multiple events

    @ParameterizedTest
    @Events(
            events = {
                    @Event("timer/timer_event.json"),
                    @Event("timer/timer2_event2.json"),
            },
            type = TimerTriggerEvent.class
    )
    public void testInjectEvents(TimerTriggerEvent event) {
        // test your handleRequest method
        ...
    }

OR simpler

.. code-block:: java
    :caption: Multiple events from folder

    // timer folder must be in the classpath (most often in src/test/resources)
    @ParameterizedTest
    @Events(folder = "timer", type = TimerTriggerEvent.class)
    public void testInjectEventsFromFolder(TimerTriggerEvent event) {
        // test your handleRequest method with all the JSON events available in the timer folder
        ...
    }


Events and Context
""""""""""""""""""""""

The `@HandlerEventContextParams` annotation permits to inject
`@Event` and `@Context` into a Junit test.

.. code-block:: java
    :caption: Single event with context

    @ParameterizedTest
      @HandlerEventContextParams(//
          event = @Event(value = "cts_event.json", type = CTSTriggerEvent.class), //
          context = @Context("context.json") //
      )
      public void testEvent(CTSTriggerEvent event,
                            TestContext context) throws Exception {
        // test your handleRequest method
        ...
      }


HandlerParams
""""""""""""""""""""""

The `@HandlerParams` permits to provide both input and output as well as
context as arguments to your tests.

Thus you can validate your `handlerRequest` method by providing the output
and asserting on the expected output.

If `@Context("")` is used, the default `TestContext` will be used.

.. code-block:: java
   :caption: Single event, response and context

    @ParameterizedTest
    @HandlerParams(//
          event = @Event(value = "apig_event.json",
                         type = APIGTriggerEvent.class),
          response = @Response(value = "apig_response.json",
                               type = APIGTriggerResponseEntity.class),
          context = @Context("context.json")
      )
      void testApig(APIGTriggerEvent event,
                    APIGTriggerResponseEntity response,
                    TestContext context) throws Exception {
        // test your handleRequest method
        ...
      }

.. code-block:: java
   :caption: Multiple events

    @ParameterizedTest
    @HandlerParams(
            events = @Events(
                    events = {
                            @Event("apig_event.json"),
                            @Event("apig_base64_event.json"),
                    },
                    type = APIGTriggerEvent.class
            ),
            responses = @Responses(
                    responses = {
                            @Response("apig_response"),
                            @Response("apig_base64_response.json")
                    },
                    type = APIGTriggerResponseEntity.class
            ),
            // context for each event
            contexts = @Contexts(
                  contexts = {
                          @Context("context1.json"),
                          @Context("context2.json")
                  }
            )
            // OR
            // same context for all events
            // context = @Context("context.json")

    )
    public void testMultipleEventsResponses(APIGTriggerEvent event,
                                            APIGTriggerResponseEntity response,
                                            TestContext context) {
      // test your handleRequest method
      ...
    }


If you cannot use those annotations (for example if you use TestNG),
or if you want to load the events/contexts on your own, you can directly
use the `EventLoader`/ `ContextLoader`, which is the underlying class
that load the json events/contexts.

EventLoader
""""""""""""""""

`EventLoader` enables to load any Event from a JSON file and
deserialize it into a Java Object.

.. code-block:: java

   MyEvent myEvent = EventLoader.loadEvent("my_event.json", MyEvent.class);


ContextLoader
""""""""""""""""

`ContextLoader` enables to load any Context from a JSON file and
deserialize it into a Java Object.

.. code-block:: java

   TestContext testContext = ContextLoader.loadContext("my_context.json");
