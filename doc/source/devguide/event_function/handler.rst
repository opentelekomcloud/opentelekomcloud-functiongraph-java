.. _ref_functionhandler:

Define FunctionGraph function handler in Java
=============================================

Setting up the java handler project
-----------------------------------

A typical Java FunctionGraph project is typically structured as follows:

.. code-block:: console
  :emphasize-lines: 6
  :caption: Project structure

  /project-root
  ├─ src
  |  └─ main
  |     └─ java
  |        └─ mypackage
  |           └─ FGEventHandler.java              # (contains main handler handleRequest())
  |           └─ <other_supporting_classes>
  └─  pom.xml

The main logic for the function resides in Java file
`src/main/java/mypackage` folder.
When deploying to FunctionGraph make sure to specify the correct handler:

[**Package name**].[**Class name**].handleRequest

In this example the handler is: `mypackage.FGEventHandler.handleRequest`.

.. image:: ../../_static/fg-console-handler.png
  :width: 600
  :alt: FunctionGraph Console


Example code for Java FunctionGraph function
--------------------------------------------

The following example receives an event as a JsonObject.

.. code-block:: java
  :caption: Example: FGEventHandler.java
  :name:  FGEventHandler_java

  package mypackage;

  import com.google.gson.JsonObject;
  import com.opentelekomcloud.services.runtime.Context;

  public class FGEventHandler {

    public String handleRequest(final JsonObject event, final Context context) {

      return "Success";
    }

  }


Defining and accessing the input event object
"""""""""""""""""""""""""""""""""""""""""""""

JSON is the most common and standard input format for FunctionGraph functions.
In this example, the function expects an input similar to the following:

.. code-block:: json
    :caption: Input data as json

    {
      "id": "aba006fd-d743-4909-959b-7189ee3fad09",
      "amount": 290.00,
      "item": "Hard disc"
    }


Following examples show how to use Java objects as event:

.. tabs::

  .. tab:: Plain EventData object
      .. code-block:: java
        :caption: EventData 

        import com.opentelekomcloud.services.runtime.Context;
        import com.opentelekomcloud.services.runtime.RuntimeLogger;

        public class SampleFG {

          public String handleRequest(final SampleFG.EventData event, final Context context)  {

            RuntimeLogger log = context.getLogger();

            log.log(String.format("class name: %s", event.getClass().getName()));
            log.log(String.format("key: %s", event.getKey()));

            return "ok";
          }

          public class EventData {
            String id;
            double amount;
            String item;

            public EventData() {
            }

            public String getId(){
              return this.id;
            }

            public void setId(String value){
              this.id=value;
            }

            public String getId(){
              return this.id;
            }

            public void setAmount(double value){
              this.amount=value;
            }

            public double getAmount(){
              return this.amount;
            }

            public String getItem(){
              return this.item;
            }

            public void setItem(String value){
              this.item=value;
            }

          }
        }

  .. tab:: EventData data object using Lombok
      To use Lombok, you will need to `setup maven for Lombok <https://projectlombok.org/setup/maven>`_

      .. code-block:: java
        :caption: EventData

        import com.opentelekomcloud.services.runtime.Context;
        import com.opentelekomcloud.services.runtime.RuntimeLogger;
        import com.google.gson.annotations.SerializedName;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import lombok.ToString;

        public class SampleFG {

          public String handleRequest(final SampleFG.EventData event, final Context context)  {

            RuntimeLogger log = context.getLogger();

            log.log(String.format("class name: %s", event.getClass().getName()));
            log.log(String.format("key: %s", event.getKey()));

            return "ok";
          }

          @Data
          @ToString(includeFieldNames=true)
          @NoArgsConstructor
          public class EventData {

            @SerializedName("id")
            String id;

            @SerializedName("amount")
            double amount;

            @SerializedName("item")
            String item;
          }

        }

This object matches the expected input data.
After you define your object, you can write a handler signature
that takes in a JSON input that conforms to the record definition.
The Java runtime automatically deserializes this JSON into a Java object
and the fields of the object can be accessed.

For example, ``event.getId()`` retrieves the value of Id from the
original input.

OpenTelekomCloud Trigger Events
-------------------------------

For events sent by OpenTelekomCloud services, see :ref:`ref-otc-events`.


Accessing and using the FunctionGraph context object
----------------------------------------------------

The :doc:`Context<./context>` interface allows functions to obtain the
function execution context, such as information about the invocation,
function, execution environment, and so on.

The context is of type ``com.opentelekomcloud.services.runtime.Context``
and is the second argument of the handler function.

* :github_repo_master:`com.opentelekomcloud.services.runtime.Context <opentelekomcloud-functiongraph-java-core/src/main/java/com/opentelekomcloud/services/runtime/Context.java>`

To produce logs in OpenTelekomCloud Log Tank Servics (LTS) you can use
``context.getLogger()`` to get a RuntimeLogger object for logging.

.. code-block:: java

  context.getLogger().log("Hello world!");

Besides of logging, you can also use the context object for
function monitoring.
For more information about the context object,
see :doc:`Using the FunctionGraph context object to retrieve Java function information.<./context>`

Accessing environment variables
-------------------------------

Environment variables defined in ``OpenTelekomCloud`` ->
``Configuration`` -> ``Environment Variables`` can be accessed using:

.. code-block:: java

  // accessing an environment variable named "ENV_VAR1"
  context.getUserData("ENV_VAR1");
