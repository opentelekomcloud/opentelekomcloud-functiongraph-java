.. _ref_deploymentpackage_http:

Deploy HTTP Functions with .zip archive
=======================================

Package Specification
---------------------

HTTP functions are packaged into zip archives.

.. note::
  The maximum size of a zip to be uploaded using console is 40MB,
  for larger files store zip in OBS

Example zip  Package
""""""""""""""""""""

.. code-block:: console
  :caption: Example zip package structure
  :emphasize-lines: 2

  zip.jar
  ├─ lib                    # folder contains all third party jars
  |  ├─ thirdparty1.jar
  |  ├─ thirdparty2.jar
  |  └─ ...
  |
  ├─ bootstrap              # bootstrapfile
  └─ functiongraph.jar      # jar with functiongraph code

Building a deployment package
-----------------------------

Deployment packages can be built using maven.

Following snippets show how to build zip archive using maven:

.. literalinclude:: /../../samples-doc/doc-sample-springboot-3.x-rest/pom.xml
   :language: xml
   :caption: pom.xml

Assembly plugin configuration
"""""""""""""""""""""""""""""

The following assembly configuration used in the ``maven-assemply-plugin``
will create a zip file as described above.

.. literalinclude:: /../../samples-doc/doc-sample-springboot-3.x-rest/src/main/assembly/zip.xml
   :language: xml
   :caption: src/main/assembly/zip.xml


Generated zip archive
---------------------

Generated zip file can be found in folder ``${PROJECT_ROOT}/target/functiongraph-samples-doc-springboot-2x-0.0.1-SNAPSHOT.zip``


See full code in :github_repo_master:`doc-sample-springboot-3.x-rest <samples-doc/doc-sample-springboot-3.x-rest>`




Deployment with the FunctionGraph console
-------------------------------------------------------------

See: :otc_fg_umn:`Creating an HTTP Function <building_functions/creating_a_function_from_scratch/creating_an_http_function.html>`
