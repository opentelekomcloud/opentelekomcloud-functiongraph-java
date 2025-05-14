Event Functions
==========================================
.. toctree::
   :maxdepth: 2
   :hidden:

   Handler <handler>
   Deploy <deploy>
   Context <context>
   Initializer <initializer>
   Pre-Stop <prestop>
   Heartbeat <heartbeat>
   Class isolation <classisolation>
   Logging <logging/index>
   Samples <samples>
   Maven Archetype <mavenarchetype>
   Invoke FunctionGraph <invoke>

Event functions can be configured with event triggers and integrate
a variety of OpenTelekomCloud products (such as object storage service
OBS, distributed messaging service RabbitMQ version,
cloud log service LTS, etc.).

FunctionGraph Java libraries
----------------------------

OpenTelekomCloud provides following libraries for FunctionGraph.
These libraries are available through:
`Maven Central Repository <https://search.maven.org/search?q=g:com.opentelekomcloud-functiongraph>`_.

* :github_repo_master:`com.opentelekomcloud:opentelekomcloud-functiongraph-java-core <opentelekomcloud-functiongraph-java-core>` (required)

  Defines handler method interfaces and context object that the runtime
  passes to the handler.

* :github_repo_master:`com.opentelekomcloud:opentelekomcloud-functiongraph-java-events <opentelekomcloud-functiongraph-java-events>`

  Event sources from services that invoke FunctionGraph functions (e.g. APIG)

* :github_repo_master:`com.opentelekomcloud:opentelekomcloud-functiongraph-java-test <opentelekomcloud-functiongraph-java-test>`

  Defines a test classes, that can be used in JUnit tests.

These packages can be included as dependency to your Maven `pom.xml`
or gardle `build.gradle` as follows:

.. tabs::

  .. tab:: pom.xml

     .. code-block:: xml
        :substitutions:

        <dependencies>
          ...
          <dependency>
            <groupId>com.opentelekomcloud</groupId>
            <artifactId>opentelekomcloud-functiongraph-java-core</artifactId>
            <version>|pom_version|</version>
          </dependency>

          <dependency>
            <groupId>com.opentelekomcloud</groupId>
            <artifactId>opentelekomcloud-functiongraph-java-events</artifactId>
            <version>|pom_version|</version>
          </dependency>


          <dependency>
            <groupId>com.opentelekomcloud</groupId>
            <artifactId>opentelekomcloud-functiongraph-java-test</artifactId>
            <version>|pom_version|</version>
            <scope>test</scope>
          </dependency>
          ...
        <dependencies>

  .. tab:: build.gradle

      .. code-block:: groovy
        :substitutions:

        plugins {
          id 'java'
        }

        repositories {
          mavenLocal() // to use maven local repository
          mavenCentral() // to use maven central repository
        }

        dependencies {
          implementation 'opentelekomcloud-functiongraph-java:|pom_version|'
        }

        task buildZip(type: Zip) {
          // ZIP must contain only jar files in root folder
          from jar
          from processResources
          from configurations.runtimeClasspath
        }

        java {
          sourceCompatibility = JavaVersion.VERSION_11
          targetCompatibility = JavaVersion.VERSION_11
        }

        build.dependsOn buildZip
