Building a SpringBoot 3.x REST FunctionGraph
============================================
.. toctree::
   :hidden:

Overview
--------

This guide shows how to use SpringBoot to develop applications and
deploy services to FunctionGraph.

Users can usually use `SpringInitializr <https://start.spring.io/>`_
or IntelliJ IDEA to build SpringBoot in various ways.

This section takes a slightly modified version of the `<https://spring.io/guides/gs/rest-service/>`_ project of Spring.io
as an example and deploys it to FunctionGraph using HTTP functions.

.. note::
  Full sample can be found in GitHub repository: :github_repo_master:`doc-sample-springboot-3.x-rest <samples-doc/doc-sample-springboot-3.x-rest>`

  Provided example here uses Java 17 with SpringBoot v3.5.0.


Operation process
-----------------

To deploy an existing project to FunctionGraph,
you usually only need to change:
- the project listening port number to **8000**,
-  create a **bootstrap** file in the same directory as the jar package, and
-  write the command to execute the jar package.


Step 1: Configure SprintBoot web port
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

The HTTP function currently supports only port **8000**. You need to configure
the project web port to 8000 in SpringBoot ``application.yaml``.

.. literalinclude:: /../../samples-doc/doc-sample-springboot-3.x-rest/src/main/resources/application.yaml
    :language: properties
    :caption: application.yaml


Step 2: Create bootstrap file
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Create a ``bootstrap`` file in the same directory as the jar package
and enter the startup parameters.

Check line 1 of ``bootstrap`` file for correct java runtime version, for availabe Runtimes paths,
see :otc_fg_umn:`Table 1 Paths for different runtimes <building_functions/creating_a_function_from_scratch/creating_an_http_function.html#id2>`.

.. literalinclude:: /../../samples-doc/doc-sample-springboot-3.x-rest/bootstrap
    :language: bash
    :caption: bootstrap file

.. note::
   The Java runtime environment can be called directly in the FunctionGraph bootstrap
   without the need for additional installation.


Step 3: Create deployment zip file
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

The structure of the deployment zip file is:

.. code-block:: console
   :caption: zip structure of doc-sample-springboot-{POM_VERSION}.zip

    /
    ├─ lib
    |  ├─ dependancy1.jar
    |  ├─ dependancy2.jar
    |  └─ ...
    |
    ├─ doc-sample-springboot.jar
    └─ bootstrap

To create the deployment zip file run in project root:

.. code-block:: bash

   doc-sample-springboot-3.x-rest$ mvn package

This will create the deployment zip file in folder ``target`` using the `Apache Maven Assembly Plugin <https://maven.apache.org/plugins/maven-assembly-plugin/>`_ .

For details see:

* :github_repo_master:`pom.xml <samples-doc/doc-sample-springboot-3.x-rest/pom.xml>`
* :github_repo_master:`assembly/zip.xml <doc-sample-springboot-3.x-rest/src/main/assembly/zip.xml>`


Step 4: Create FunctionGraph HTTP Function
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Create an HTTP function and upload the packaged zip package.
For details, see :otc_fg_umn:`Creating an HTTP Function <building_functions/creating_a_function_from_scratch/creating_an_http_function.html#procedure>`.

.. note::
   It is recommended that you increase the function memory specification and timeout period during testing, such as 512MB and 5s.

Step 5: Verify the results
^^^^^^^^^^^^^^^^^^^^^^^^^^

On the function details page, select the function version and click
``Configure Test Events``. The Configure Test Events page pops up.
Select the event template "API Gateway (Dedicated gateway), modify the
**path** (see line 13 in sample) and
**pathParameters** (see line 15 in sample) parameters in the test event,
and build a simple **Get** request.

.. code-block:: json
    :caption: Test event sample data with adaptions on line 13 and 15
    :linenos:
    :emphasize-lines: 13,15

    {
        "body": "",
        "requestContext": {
            "apiId": "bc1dcffd-aa35-474d-897c-d53425a4c08e",
            "requestId": "11cdcdcf33949dc6d722640a13091c77",
            "stage": "RELEASE"
        },
        "queryStringParameters": {
            "responseType": "html"
        },
        "httpMethod": "GET",
        "pathParameters": {
            "name": "John Doe"
        },
        "path": "/greeting",
        "headers": {
            "accept-language": "q=0.5,en-US;q=0.3,en;q=0.2",
            "accept-encoding": "gzip, deflate, br",
            "x-forwarded-port": "443",
            "x-forwarded-for": "103.218.216.98",
            "accept": "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
            "upgrade-insecure-requests": "1",
            "host": "host",
            "x-forwarded-proto": "https",
            "pragma": "no-cache",
            "cache-control": "no-cache",
            "x-real-ip": "103.218.216.98",
            "user-agent": "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0"
        },
        "isBase64Encoded": true
    }


Step 6: Configure APIG Trigger
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

1. Please refer to :otc_fg_umn:`User manual "Creating an HTTP Function" <building_functions/creating_a_function_from_scratch/creating_an_http_function.html#procedure>`
   and :otc_fg_umn:`Create a Trigger <building_functions/creating_a_function_from_scratch/creating_an_http_function.html#id4>` to create an APIG exclusive version trigger

   It is recommended to select **None** for Security Authentication to
   facilitate debugging.

   When defining the API request, set values according to:

   .. image:: ../../_static/springbootrest-console-edit-api-request.png
      :width: 800
      :alt: Edit API Request


   .. note::
      Generated URL can be found in FunctionGraph console -> Tab Configuation -> Triggers

      .. image:: ../../_static/springbootrest-console-trigger.png
         :width: 800
         :alt: URL location

2. Copy the generated call URL and curl it:

   .. code-block:: bash
      :caption: Test event sample data

      $ curl https://XXXXXXXXXX.apic.eu-de.otc.t-systems.com/greeting?name=John
      {"id":2,"content":"Hello, John!"}


3. Or use copied URL in web browser:

   .. image:: ../../_static/springbootrest-browserresult.png
      :width: 800
      :alt: Browser result


Frequently asked questions
--------------------------

How to test local?
^^^^^^^^^^^^^^^^^^
To test local use following command in root of project:

.. code-block:: bash

   doc-sample-springboot-3.x-rest$ ./mvnw spring-boot:run

Why ClassNotFoundException after deployment?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Check ``bootstrap`` file for correct:

* ``jar`` file name in classpath
* correct main class name

What directories can my code access?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
According to the commands in the bootstrap file above, it can be seen that the
uploaded code package is finally stored in the function instance (referring to
the environment/computing resources where the function runs,
which can be understood as a container) ``/opt/function/code/`` path. However,
this directory can only be read, not written.

If you want to write some data to the instance during code execution, write
to the ``/tmp`` directory.

How are my logs collected and how should I output them?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
If there is no request for a period of time, the function instance will be
destroyed, and the data written to the local log will be destroyed at the
same time. The current user cannot view the local log of the function while
the function is running, so it is recommended not to write the log only to
the local.

It is recommended to output the generated log to the console, such as
configuring the ``log4j`` output target to ``System.out``, or directly
using the print function to print the log.

The logs output to the console will be collected by the function system.
If the user activates the LTS service, the logs will be put into LTS for more
real-time log analysis.

It is recommended to enable :otc_fg_umn:`LTS logs <configuring_functions/configuring_a_log_group_and_log_stream.html#functiongraph-01-1841>`
during commissioning and click Go to LTS for log analysis to observe and
analyze the real-time logs.

As all FunctionGraph instances write to the same log, it can be difficult to
distinguish logs of different requests.
Writing to ``stdout`` or ``stderr`` will need to log the requestId of the
request obtained from the incoming request header ``x-cff-request-id``.
(see :otc_fg_umn:`Common Function Request Headers <building_functions/creating_a_function_from_scratch/creating_an_http_function.html#common-function-request-headers>`)

Using a logging framework the user is responible for adding the request id to
the log output.

This can be achieved by using ThreadContext object.

The provided example ``OTCRequestContextLoggingFilter.java`` uses SpringBoot ``@Component``
annotation and request/reponse filtering to add header values to the ThreadContext.

.. literalinclude:: /../../samples-doc/doc-sample-springboot-3.x-rest/src/main/java/com/opentelekomcloud/samples/springboot/components/OTCRequestContextLoggingFilter.java
   :language: java
   :caption: OTCRequestContextLoggingFilter.java

For more details on request filtering, see: `How to Define a Spring Boot Filter <https://www.baeldung.com/spring-boot-add-filter>`_

In the logging configuration of ``application.yaml``

.. literalinclude:: /../../samples-doc/doc-sample-springboot-3.x-rest/src/main/resources/application.yaml
   :language: yaml
   :caption: application.yaml

you can see that for ``logging.pattern.console`` the
 ``x-cff-request-id`` is added to the log line.

What user permissions does my code have?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Like ordinary event functions, the code is executed without root privileges,
so code or commands that require root privileges cannot be executed
in HTTP functions.
