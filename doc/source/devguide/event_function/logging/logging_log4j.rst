.. _logging-log4j-ref:

Logging using Log4j
===================

FunctionGraph service supports Java function logging using Log4j and Slf4j.

.. note::
   As all FunctionGraph instances write to the same log, it can be difficult to distinguish logs of different requests.
   Writing to ``stdout`` or ``stderr`` will need to log the ``requestId`` of the request obtained from the ``Context``.

   The provided ``RunTimeLogger`` will add the requestId to the log output (see: see: :ref:`logging-ref`).

   Using a logging framework the user is responible for adding the ``requestId`` to the log output.
   This can be achieved by using MDC (Mapped Diagnostic Context).


This section describes how to use functions and log4j to implement log
printing.

.. literalinclude:: /../../samples-doc/doc-sample-logging-slf4j/src/main/java/com/opentelekomcloud/samples/FGLoggingSLF4j.java
   :language: java
   :caption: :github_repo_master:`FGLoggingSLF4j.java <samples-doc/doc-sample-logging-slf4j/src/main/java/com/opentelekomcloud/samples/FGLoggingSLF4j.java>`

.. literalinclude:: /../../samples-doc/doc-sample-logging-slf4j/src/main/resources/log4j2-custom.xml
   :language: xml
   :caption: :github_repo_master:`log4j2-custom.xml <samples-doc/doc-sample-logging-slf4j/src/main/resources/log4j2-custom.xml>`


.. code-block:: xml
  :caption: :github_repo_master:`pom.xml <samples-doc/doc-sample-logging-slf4j/pom.xml>`

     <dependencies>
     ...
      <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-api</artifactId>
        <version>2.24.3</version>
      </dependency>

      <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.24.3</version>
      </dependency>

      <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-slf4j-impl</artifactId>
        <version>2.24.3</version>
      </dependency>

      <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.38</version>
        <scope>compile</scope>
      </dependency>
    ...
  </dependencies>

Log output

.. code-block:: text
  :caption: Example Log output
  :linenos:

  2025-06-27T10:18:59Z Start load request '53564bdc-8c18-429f-a419-b764c55b1831', version: latest
  2025-06-27T10:19:00Z a0a6f554-0207-4a3e-a5b5-daa62490cbc2 INFO Start initializing...
  2025-06-27T10:19:00Z a0a6f554-0207-4a3e-a5b5-daa62490cbc2 INFO Finished initializing...
  2025-06-27T10:19:00Z Finish load request '53564bdc-8c18-429f-a419-b764c55b1831', duration: 891.558ms, memory used: 110.109MB, storage used: 0.052MB.
  2025-06-27T10:19:00Z Start initialize request '4666abc1-6fe3-4848-bcb0-cad8aad0a8c4', version: latest
  2025-06-27T10:19:00.747410534Z RuntimeThreadPool-1-thread-2 INFO Starting configuration XmlConfiguration[location=jar:file:/opt/function/code/functiongraph-samples-doc-logging-jar-with-dependencies.jar!/log4j2-custom.xml, lastModified=2025-06-27T10:18:57.705Z]...
  2025-06-27T10:19:00.747795157Z RuntimeThreadPool-1-thread-2 INFO Start watching for changes to jar:file:/opt/function/code/functiongraph-samples-doc-logging-jar-with-dependencies.jar!/log4j2-custom.xml every 0 seconds
  2025-06-27T10:19:00.748158166Z RuntimeThreadPool-1-thread-2 INFO Configuration XmlConfiguration[location=jar:file:/opt/function/code/functiongraph-samples-doc-logging-jar-with-dependencies.jar!/log4j2-custom.xml, lastModified=2025-06-27T10:18:57.705Z] started.
  2025-06-27T10:19:00.748605402Z RuntimeThreadPool-1-thread-2 INFO Stopping configuration XmlConfiguration[location=/home/snuser/config/log4j2.xml, lastModified=2022-02-28T02:42:14Z]...
  2025-06-27T10:19:00.749250381Z RuntimeThreadPool-1-thread-2 INFO Configuration XmlConfiguration[location=/home/snuser/config/log4j2.xml, lastModified=2022-02-28T02:42:14Z] stopped.
  2025-06-27T10:19:00Z Finish initialize request '4666abc1-6fe3-4848-bcb0-cad8aad0a8c4', duration: 24.703ms, memory used: 111.270MB, storage used: 0.052MB.
  2025-06-27T10:19:00Z Start invoke request 'a0a6f554-0207-4a3e-a5b5-daa62490cbc2', version: latest
  [2025-06-27T10:19:00,829Z a0a6f554-0207-4a3e-a5b5-daa62490cbc2 DEBUG FGLoggingSLF4j.java:66 RuntimeThreadPool-1-thread-2] - debug log
  [2025-06-27T10:19:00,832Z a0a6f554-0207-4a3e-a5b5-daa62490cbc2 INFO FGLoggingSLF4j.java:67 RuntimeThreadPool-1-thread-2] - info log
  [2025-06-27T10:19:00,833Z a0a6f554-0207-4a3e-a5b5-daa62490cbc2 WARN FGLoggingSLF4j.java:68 RuntimeThreadPool-1-thread-2] - warn log
  [2025-06-27T10:19:00,833Z a0a6f554-0207-4a3e-a5b5-daa62490cbc2 ERROR FGLoggingSLF4j.java:69 RuntimeThreadPool-1-thread-2] - error log
  2025-06-27T10:19:00Z Finish invoke request 'a0a6f554-0207-4a3e-a5b5-daa62490cbc2', duration: 75.049ms, billing duration: 76ms, memory used: 111.656MB, billing memory: 512MB, cpu used: 0.399U, storage used: 0.052MB

Lines 13-16 are outputs from configured logger.

Deployment instructions:
------------------------

- Set **function execution entry point**

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


Resources:
----------

https://www.slf4j.org/manual.html

https://www.slf4j.org/manual.html#mdc
