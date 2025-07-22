Maven archetype for FunctionGraph sample project
-------------------------------------------------

To ease project setup, following maven archetype can be used.

Install maven archetype
^^^^^^^^^^^^^^^^^^^^^^^

To install archtype, see :ref:`ref_maven_install`.

Create sample FunctionGraph project
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

To create a simple java 17 project using maven archetype use:

.. tabs::

  .. tab:: From remote maven repository

     .. code-block:: shell
        :substitutions:

        mvn archetype:generate                                 \
          -DarchetypeGroupId=io.github.opentelekomcloud        \
          -DarchetypeArtifactId=opentelekomcloud-functiongraph-archetype \
          -DarchetypeVersion=|pom_version|                     \
          -DgroupId=com.sample                                 \
          -DartifactId=sample                                  \
          -DhandlerClassName=MyHandler

  .. tab:: From local maven repository:

      .. code-block:: shell
         :substitutions:

         mvn archetype:generate                                 \
           -DarchetypeCatalog=local                             \
           -DarchetypeGroupId=io.github.opentelekomcloud        \
           -DarchetypeArtifactId=opentelekomcloud-functiongraph-archetype \
           -DarchetypeVersion=|pom_version|                     \
           -DgroupId=com.sample                                 \
           -DartifactId=sample                                  \
           -DhandlerClassName=MyHandler


Building the project
^^^^^^^^^^^^^^^^^^^^

In root of generated project use one of following commands to build project:

.. tabs::

  .. tab:: Using maven

     .. code-block:: shell
        :substitutions:

        mvn clean install

  .. tab:: Using gradle

     .. code-block:: shell
        :substitutions:

        gradle build

