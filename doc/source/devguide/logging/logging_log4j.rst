.. _logging-log4j-ref:

Logging using Log4j
===================

FunctionGraph service supports Java function logging using Log4j and Slf4j.

.. note:: 
   As all FunctionGraph instances write to the same log, it can be difficult to distinguish logs of different requests.
   Writing to ``stdout`` or ``stderr`` will need to log the requestId of the request obtained from the ``Context``.
   The provided ``RunTimeLogger`` will add the requestId to the log output.
   Using a logging framework the user is responible for adding the request id to the log output.
   This can be achieved by using MDC (Mapped Diagnostic Context).


This section describes how to use functions and log4j to implement log printing.

.. code-block:: java
  :caption: FunctionHandler class

  package org.example;

  import org.apache.logging.log4j.Level;
  import org.apache.logging.log4j.core.config.Configurator;
  import org.apache.logging.log4j.util.LoaderUtil;
  import org.slf4j.MDC;
  import lombok.extern.slf4j.Slf4j;
  
  import com.opentelekomcloud.services.runtime.Context;
  import com.opentelekomcloud.services.functiongraph.runtime.core.RequestHandler;

  @Slf4j
  public class App implements RequestHandler<EventData, String> {

    /**
    * Setup logging in initializer
    */
    public void initializer(Context context) {
      try {
        // put requestId into MDC
        MDC.put("requestid", context.getRequestID());
        // Initialize Log4J
        try {
          Configurator.reconfigure(
            Objects.requireNonNull(LoaderUtil.getThreadContextClassLoader().getResource("log4j2-custom.xml")).toURI());
        } catch (URISyntaxException e) {
          log.error("An error occurred while configuring Log4J", e);
          throw new RuntimeException(e);
        }

        // set log level
        Level level = Level.getLevel(context.getUserData("LOG_LEVEL"));
        Configurator.setRootLevel(level);

      } finally {
        // remove requestId from MDC
        MDC.remove("requestid");
      }
    }

    /**
    * FunctionGraph Handler
    */
    @Override
    public String handleRequest(EventData event, Context context) {
      try {
        // put requestId into MDC
        MDC.put("requestid", context.getRequestID());

        log.debug("debug log");
        log.info("info log");
        log.warn("warn log");
        log.error("error log");

      } finally {
        // remove requestId from MDC
        MDC.remove("requestid");
      }
    }

  }


.. code-block:: xml
  :caption: src/main/resources/log4j2-custom.xml

  <?xml version="1.0" encoding="UTF-8"?>
  <Configuration status="INFO">
    <Properties>
      <!-- requestid will be taken from MDC -->
      <Property name="LOG_PATTERN">[%d{ISO8601}{GMT}Z '%X{requestid}' %p %F:%L %t] - %m%n</Property>
    </Properties>

    <Appenders>
      <Console name="Console" target="SYSTEM_OUT">
        <PatternLayout pattern="${LOG_PATTERN}" />
      </Console>
    </Appenders>

    <Loggers>
      <Root level="DEBUG">
        <AppenderRef ref="Console" />
      </Root>
    </Loggers>

  </Configuration>


.. code-block:: xml
  :caption: pom.xml

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

     </dependencies>

Deployment hints:
-----------------
   
- **Set function execution entry point**
  
  Choose **Settings** > **Basic Settings**, set the Function Execution Entry parameter to **org.example.App.handleRequest**

- **Enable class isolation.**
  
   After the code package is successfully deployed, select **Settings** > **Advanced Settings**, turn on **Class Isolation**

- **Set function initialization entry point**

  Choose **Settings** > **Advanced Settings**:
  
  - enable **Initialization**
  - set the **Initializer** parameter to **org.example.App.initializer**
  - set the **Initialization Timeout** parameter to appropriate value, e.g. 10s
  
- **Set Environment Variable "LOG_LEVEL"**

  Choose **Settings** > **Environment Variables** and add new variable with key: **LOG_LEVEL** and Value: DEBUG
  (Possible values are DEBUG, INFO, WARN, ERROR) 



Resources:
----------

https://www.slf4j.org/manual.html

https://www.slf4j.org/manual.html#mdc
