Deploy Java FunctionGraph functions with .zip or JAR file archives
==================================================================

Package Specification
---------------------

JAR Package
^^^^^^^^^^^^^^^^^^^^^^^^^^^
If the function does not reference any third-party software,
you can directly compile the function project into a JAR package.

.. note::
  The maximum size of a JAR to be uploaded using console is 40MB,
  for larger files store JAR in OBS

Example JAR Package
"""""""""""""""""""

.. code-block:: console
  :caption: Example JAR package structure
  :emphasize-lines: 2

  example.jar
  ├─ com
  |  └─ sample                              # FunctionGraph handler
  |  |  └─ SampleFunctionHandler.class
  |  └─ opentelekomcloud                     # OpenTelekomCloud dependencies
  |     └─ services
  |        └─ runtime
  |           └─ Context.class
  |           └─ RuntimeLogger.class
  |           └─ Runtime.class
  |           └─ ...
  |        └─ functiongraph
  |           └─ runtime
  |              └─ core
  |              |  └─ ...
  |              └─ entity
  |                 └─ ...


Jar with dependencies
^^^^^^^^^^^^^^^^^^^^^

Using ``maven-assembly-plugin`` a jar with all dependencies will be created.
This file ``*-jar-with-dependencies`` located in the target folder can be
used as deployment file.

ZIP Package
^^^^^^^^^^^^^^^^^^^^^^^^^^^
If the function references a third-party software, compile the function
project into a Jar package, and then package all the dependent third-party
software and the function JAR package into a ZIP package.

.. note::
  The maximum size of a ZIP to be uploaded using console is 40MB, for larger files store ZIP in OBS.

Example ZIP Package
"""""""""""""""""""""""

.. code-block:: console
  :caption: Example ZIP package structure
  :emphasize-lines: 2
  :substitutions:

  example.zip
  ├─ myfunctionhandler.jar                                        # business function JAR package
  ├─ opentelekomcloud-functiongraph-java-core-|pom_version|.jar   # OTC dependency JAR package
  ├─ opentelekomcloud-functiongraph-java-events-|pom_version|.jar # OTC dependency JAR package
  ├─ gson-2.11.0.jar                                              # third-party dependency JAR package
  ├─ handler.txt                                                  # optional file with name of handler
  └─ ...

OBS
^^^^^^^^^^^^^^^^^^^^^^^^^^^
Package the project into a ZIP package and upload it to the OBS storage
bucket.



Building a deployment package
-----------------------------

Deployment packages can be built using gradle or maven.

Following snippets show how to build a ZIP package with gradle or a
JAR Package with dependencies using maven.

.. tabs::

  .. tab:: pom.xml

     .. literalinclude:: /../../samples-doc/doc-sample-deploy/pom.xml
        :language: xml

     Generated JAR file can be found in folder ``${PROJECT_ROOT}/target/doc-sample-deploy-jar-with-dependencies.jar``

  .. tab:: build.gradle

     .. literalinclude:: /../../samples-doc/doc-sample-deploy/build.gradle
        :language: Groovy

     Generated ZIP file can be found in folder ``${PROJECT_ROOT}/build/distributions/doc-sample-deploy.zip``



Deployment with the FunctionGraph console
-------------------------------------------------------------

See: :otc_fg_umn:`Creating an Event Function <building_functions/creating_a_function_from_scratch/creating_an_event_function.html>`
