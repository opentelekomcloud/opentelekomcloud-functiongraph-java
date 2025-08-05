.. _logging-log4j-ref:

Logging using Log4j
===================

FunctionGraph service supports Java function logging using Log4j and Slf4j.

.. note::
   As all FunctionGraph instances write to the same log, it can be difficult to distinguish logs of different requests.
   Writing to ``stdout`` or ``stderr`` will need to log the ``requestId`` of the request obtained from the ``Context``.

   The provided ``RunTimeLogger`` will add the requestId to the log output (see: see: :ref:`logging-ref`).

   Using a logging framework the user is responible for adding the ``requestId`` to the log output.
   This can be achieved by using ThreadContext object.


This section describes how to use functions and log4j to implement log
printing.

.. tabs::

  .. tab:: FGLoggingSLF4j.java

      .. literalinclude:: /../../samples-doc/doc-sample-logging-slf4j/src/main/java/com/opentelekomcloud/samples/FGLoggingSLF4j.java
        :language: java
        :caption: :github_repo_master:`FGLoggingSLF4j.java <samples-doc/doc-sample-logging-slf4j/src/main/java/com/opentelekomcloud/samples/FGLoggingSLF4j.java>`

  .. tab:: log4j2-custom.xml

      .. literalinclude:: /../../samples-doc/doc-sample-logging-slf4j/src/main/resources/log4j2-custom.xml
        :language: xml
        :caption: :github_repo_master:`log4j2-custom.xml <samples-doc/doc-sample-logging-slf4j/src/main/resources/log4j2-custom.xml>`

  .. tab:: pom.xml

      .. literalinclude:: /../../samples-doc/doc-sample-logging-slf4j/pom.xml
        :language: xml
        :caption: :github_repo_master:`log4j2-custom.xml <samples-doc/doc-sample-logging-slf4j/pom.xml>`


Log output

.. code-block:: text
  :caption: Example Log output
  :linenos:
  :emphasize-lines: 2-5

  2025-08-01T08:13:22Z Start invoke request '0d0c9273-61d9-4dfb-b32a-52a4372a98dc', version: latest
  2025-08-01T08:13:22.792Z|0d0c9273-61d9-4dfb-b32a-52a4372a98dc|DEBUG|FGLoggingSLF4j.java:97|debug log
  2025-08-01T08:13:22.792Z|0d0c9273-61d9-4dfb-b32a-52a4372a98dc|INFO|FGLoggingSLF4j.java:98|info log
  2025-08-01T08:13:22.793Z|0d0c9273-61d9-4dfb-b32a-52a4372a98dc|WARN|FGLoggingSLF4j.java:99|warn log
  2025-08-01T08:13:22.793Z|0d0c9273-61d9-4dfb-b32a-52a4372a98dc|ERROR|FGLoggingSLF4j.java:100|error log
  2025-08-01T08:13:22Z Finish invoke request '0d0c9273-61d9-4dfb-b32a-52a4372a98dc', duration: 60.378ms, billing duration: 61ms, memory used: 104.137MB, billing memory: 512MB, cpu used: 0.195U, storage used: 0.052MB

Lines 2-5 show outputs from configured logger.

.. note::

  If initializer is not set, log output will be as follows:

  .. code-block:: text
    :caption: Example Log output with function initializer not set
    :linenos:
    :emphasize-lines: 2-5

    2025-08-01T08:30:09Z Start invoke request '2146fee3-aa3b-44cb-af6a-d7d292ccf6e6', version: latest
    [2025-08-01 10:30:09.449 INFO FGLoggingSLF4j.java:98] info log
    [2025-08-01 10:30:09.453 WARN FGLoggingSLF4j.java:99] warn log
    [2025-08-01 10:30:09.453 ERROR FGLoggingSLF4j.java:100] error log
    2025-08-01T08:30:09Z Finish invoke request '2146fee3-aa3b-44cb-af6a-d7d292ccf6e6', duration: 59.914ms, billing duration: 60ms, memory used: 99.527MB, billing memory: 512MB, cpu used: 0.398U, storage used: 0.052MB

  Lines 2-5 show outputs from default logger configuration.


Deployment instructions:
------------------------

- Set **Function execution entry point**

  Choose **Settings** > **Basic Settings**, set the Function Execution Entry
  parameter to **com.opentelekomcloud.samples.FGLoggingSLF4j.handleRequest**

- **Enable class isolation.**

   After the code package is successfully deployed, select
   **Settings** > **Advanced Settings**, turn on **Class Isolation**

- Set **function initialization entry point**

  Choose **Settings** > **Advanced Settings**:

  - enable **Initialization**
  - set the **Initializer** parameter to **com.opentelekomcloud.samples.FGLoggingSLF4j.initializer**
  - set the **Initialization Timeout** parameter to appropriate value, e.g. 10s

- Set **Environment Variable "LOG_LEVEL"**

  Choose **Settings** > **Environment Variables** and add new variable
  with key: **LOG_LEVEL** and value: **DEBUG**

  (Possible values are DEBUG, INFO, WARN, ERROR)


Further readings:
-----------------

https://www.slf4j.org/manual.html
