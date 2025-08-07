# Test utilities

The `opentelekomcloud-functiongraph-java-test` module provides tools to ease Java FunctionGraph testing and is a test dependency.

The implemention is inspired by [aws-lambda-java-test](https://github.com/aws/aws-lambda-java-libs/tree/main/aws-lambda-java-tests).

**Key features**

* Load events from json files and get them deserialized into Java Events.
* Inject Events directly in JUnit 5 tests, using the `@ParameterizedTest` annotation.

## Installation

To install this utility, add the following dependency to your project as a test dependency.

```xml
<dependency>
    <groupId>io.github.opentelekomcloud</groupId>
    <artifactId>opentelekomcloud-functiongraph-java-test</artifactId>
    <version>{VERSION SEE PARENT POM}</version>
    <scope>test</scope>
</dependency>
```

Also have surefire in your plugins:

```xml
<build>
  <plugins>
	<plugin>
	    <groupId>org.apache.maven.plugins</groupId>
	    <artifactId>maven-surefire-plugin</artifactId>
	    <version>{VERSION SEE PARENT POM}</version>
	</plugin>
  </plugins>
</build>
```

## Usage

### Events injection

A set of annotations can be used to inject Events and/or to validate handler responses against those Events.
**All those annotations must be used in conjunction with the [`@ParameterizedTest`](https://junit.org/junit5/docs/current/api/org.junit.jupiter.params/org/junit/jupiter/params/ParameterizedTest.html) annotation from Junit 5.**

`ParameterizedTest` enables to inject arguments into a unit test, so you can run the same test one or more time with different parameters.
See [the doc](https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests) for more details on this.

**Event**

The `@Event` annotation permits to inject one Event into a Junit test.

Example:

```java
// the json file must be in the classpath (most often in src/test/resources)
@ParameterizedTest
@Event(value = "timer/timer_event.json", type = TimerTriggerEvent.class)
public void testInjectTimerEvent(TimerTriggerEvent event) {
    // test your handleRequest method with this event as parameter
}
```

**Events**

The `@Events` annotation permits to inject multiple Events into a Junit test

Examples:

```java
@ParameterizedTest
@Events(
        events = {
                @Event("timer/timer_event.json"),
                @Event("timer/timer2_event2.json"),
        },
        type = TimerTriggerEvent.class
)
public void testInjectEvents(TimerTriggerEvent event) {
    // test your handleRequest method with all the JSON events available in the timer folder
}

// OR simpler

// timer folder must be in the classpath (most often in src/test/resources)
@ParameterizedTest
@Events(folder = "timer", type = TimerTriggerEvent.class)
public void testInjectEventsFromFolder(TimerTriggerEvent event) {
    // test your handleRequest method with all the JSON events available in the timer folder
}
```

**HandlerParams**

The `@HandlerParams` is the most advanced one as it permits to provide both input and output as arguments to your tests.
Thus you can validate your `handlerRequest` method by providing the output and asserting on the expected output.

```java

// Single event
@ParameterizedTest
@HandlerParams(
   event = @Event(value = "apigw/events/apigw_event.json", type = APIGatewayProxyRequestEvent.class),
   response = @Response(value = "apigw/responses/apigw_response.json", type = APIGatewayProxyResponseEvent.class))
public void testSingleEventResponse(APIGatewayProxyRequestEvent event, APIGatewayProxyResponseEvent response) {
}

// Multiple events in folder
@ParameterizedTest
@HandlerParams(
   events = @Events(folder = "apigw/events/", type = APIGatewayProxyRequestEvent.class),
   responses = @Responses(folder = "apigw/responses/", type = APIGatewayProxyResponseEvent.class))
public void testMultipleEventsResponsesInFolder(APIGatewayProxyRequestEvent event, APIGatewayProxyResponseEvent response) {
}

// Multiple events
@HandlerParams(
        events = @Events(
                events = {
                        @Event("apigw/events/apigw_event.json"),
                        @Event("apigw/events/apigw_event2.json"),
                },
                type = APIGatewayProxyRequestEvent.class
        ),
        responses = @Responses(
                responses = {
                        @Response("apigw/responses/apigw_response.json"),
                        @Response("apigw/responses/apigw_response2.json")
                },
                type = APIGatewayProxyResponseEvent.class
        )
)
public void testMultipleEventsResponses(APIGatewayProxyRequestEvent event, APIGatewayProxyResponseEvent response) {
}
```

If you cannot use those annotations (for example if you use TestNG), or if you want to load the events on your own, you can directly use the `EventLoader`, which is the underlying class that load the json events.

### EventLoader

`EventLoader` enables to load any Event from a JSON file and deserialize it into a Java Object.

```java
MyEvent myEvent = EventLoader.loadEvent("my_event.json", MyEvent.class);
```
