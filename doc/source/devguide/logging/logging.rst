Logging using RunTimeLogger
===========================

Creating a function that writes logs
------------------------------------

To output logs from your function code, you can use methods on ``java.lang.System``, or any logging module
that writes to ``stdout`` or ``stderr``. 

.. note:: 
   As all FunctionGraph instances write to the same log, it can be difficult to distinguish logs of different requests.
   Writing to ``stdout`` or ``stderr`` will need to log the requestId of the request obtained from the ``Context``.
   The provided ``RunTimeLogger`` will add the requestId to the log output.

   For logging using Log4J framework and requestId see: :ref:`logging-log4j-ref`.


The ``opentelekomcloud-functiongraph-java`` library provides a logger class named ``RuntimeLogger`` that can be accessed from the context object.

This class provides following methods:

.. list-table:: RuntimeLogger methods
   :widths: 25 25
   :header-rows: 1

   * - Method name
     - Description
     
   * - void log(String message);
     - Log message independent of Log Level
     
   * - void debug(String message);
     - Log message as ``debug`` message
      
   * - void info(String message);
     - Log message as ``info`` message

   * - void warn(String message);
     - Log message as ``warn`` message

   * - void error(String message);
     - Log message as ``error`` message

   * - void setLevel(String level);
     - set Log Level, valid values are:
        - DEBUG
        - INFO
        - WARN
        - ERROR

The following :github_repo_master:`example <samples/doc-samples/doc-sample-logging>`
uses the RuntimeLogger logger provided by the context object.

.. literalinclude:: /../../samples-doc/doc-sample-logging/src/main/java/com/opentelekomcloud/samples/FGLogSample1.java
   :language: java
   :caption: Example: FGLogSample1.java

.. code-block:: text
   :caption: Log Output

    2025-02-18T11:16:19Z Start invoke request 'a8f4c82d-a506-4ba3-9fc6-fb60471683d5', version: latest
    2025-02-18T11:16:19Z a8f4c82d-a506-4ba3-9fc6-fb60471683d5 INFO Event: {
    "key": "value"
    }
    2025-02-18T11:16:19Z Finish invoke request 'a8f4c82d-a506-4ba3-9fc6-fb60471683d5', duration: 1.634ms, billing duration: 2ms, memory used: 106.969MB, billing memory: 512MB


The Java runtime logs the **Start invoke request** and **Finish invoke request** lines for each invocation. 

The **Start invoke request** line provides the following details:

* **RequestId** - The unique request Id for the invocation
* **version** - Version of the FunctionGraph 

The **Finish invoke request** line provides the following details:

* **RequestId** - The unique request Id for the invocation
* **duration** - The amount of time that your function's handler method spent processing the event.
* **billing duration** - The amount of time billed for the invocation.
* **memory used** - The amount of memory used for the invocation.
* **billing memory** - The amount of memory billed for the invocation.



Getting current LogLevel
------------------------

The Api implementation of **Context**, currently does not provide a method to get the current log level the RuntimeLogger is using.

Following sample can be used as a workaround:

.. literalinclude:: /../../samples-doc/doc-sample-logging/src/main/java/com/opentelekomcloud/samples/FGLogSample2.java
   :language: java
   :caption: Example: FGLogSample2.java
