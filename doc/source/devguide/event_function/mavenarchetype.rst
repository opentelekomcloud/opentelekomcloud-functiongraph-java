Maven archetype for FunctionGraph sample project
-------------------------------------------------

To ease project setup, following maven archetype can be used.

Install maven archetype from source
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Run following command in folder ``opentelekomcloud-functiongraph-archetype``

.. code-block:: console

   mvn clean install

Create sample FunctionGraph project
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

To create a sample java project using maven archetype use:

From remote maven repository:

.. code-block:: console
  :substitutions:

  mvn archetype:generate                                 \
    -DarchetypeGroupId=com.opentelekomcloud              \
    -DarchetypeArtifactId=opentelekomcloud-functiongraph-archetype \
    -DarchetypeVersion=|pom_version|                    \
    -DgroupId=com.sample                                 \
    -DartifactId=sample                                  \
    -DhandlerClassName=MyHandler

From local maven repository:

.. code-block:: console
  :substitutions:

  mvn archetype:generate                                 \
    -DarchetypeCatalog=local                             \
    -DarchetypeGroupId=com.opentelekomcloud              \
    -DarchetypeArtifactId=opentelekomcloud-functiongraph-archetype \
    -DarchetypeVersion=|pom_version|                    \
    -DgroupId=com.sample                                 \
    -DartifactId=sample                                  \
    -DhandlerClassName=MyHandler


Building the project
^^^^^^^^^^^^^^^^^^^^

In root of generated project use one of following commands to build project:

Using Maven:

.. code-block:: console

    mvn clean install


Using Gradle:

.. code-block:: console

    gradle build
