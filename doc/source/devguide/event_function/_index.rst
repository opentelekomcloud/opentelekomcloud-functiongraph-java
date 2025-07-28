Event Functions
==========================================
.. toctree::
   :hidden:

   Handler <handler>
   Context <context>
   Initializer <initializer>
   Pre-Stop <prestop>
   Heartbeat <heartbeat>
   Logging <logging/_index>
   Class isolation <classisolation>
   Maven Archetype <mavenarchetype>
   Invoke FunctionGraph <invoke>
   Local Testing <localtesting>
   Trigger Events <event_trigger/_index>
   Call OpenTelekomCloud Services <call_services>


Event functions can be configured with event triggers and integrate
a variety of OpenTelekomCloud products (such as object storage service
OBS, distributed messaging service RabbitMQ version,
cloud log service LTS, etc.).

FunctionGraph Java libraries
----------------------------

OpenTelekomCloud provides following libraries for FunctionGraph.

* :github_repo_master:`com.opentelekomcloud:opentelekomcloud-functiongraph-java-core <opentelekomcloud-functiongraph-java-core>` (required)

  Defines handler method interfaces and context object that the runtime
  passes to the handler.

* :github_repo_master:`com.opentelekomcloud:opentelekomcloud-functiongraph-java-events <opentelekomcloud-functiongraph-java-events>`

  Event sources from services that invoke FunctionGraph functions (e.g. APIG)

* :github_repo_master:`com.opentelekomcloud:opentelekomcloud-functiongraph-java-test <opentelekomcloud-functiongraph-java-test>`

  Defines a test classes, that can be used in JUnit tests.

OpenTelekomCloud community provides following libraries for Java development:

* The community edition of :github_java_sdk:`OTC Java SDK v1<>` provides utility methods to handle request signing.

.. _ref_maven_install:

Installing libraries
^^^^^^^^^^^^^^^^^^^^

These libraries are available through:

.. tabs::

  .. tab:: Source

     Install libraries to local .m2 repository

     .. code-block:: shell
        :substitutions:

        # Check out the repository with tag v|pom_version|
        git clone --branch v|pom_version| --single-branch |github_repo|

        # change to folder
        cd opentelekomcloud-functiongraph-java

        # run maven clean install
        mvn clean install

  .. tab:: Maven Central

     For Maven Central, there is no special configuration needed.

     Just add the dependencies to your `pom.xml` as shown below.

     For list of libraries available in Maven Central see:
     `Maven Repository <https://mvnrepository.com/search?q=io.github.opentelekomcloud>`_


Usage
^^^^^

These packages can be included as dependency to your Maven `pom.xml`
or gardle `build.gradle` as follows:

.. tabs::

  .. tab:: pom.xml

     .. code-block:: xml
        :substitutions:

        <dependencies>
          ...
          <dependency>
            <groupId>io.github.opentelekomcloud</groupId>
            <artifactId>opentelekomcloud-functiongraph-java-core</artifactId>
            <version>|pom_version|</version>
          </dependency>

          <dependency>
            <groupId>io.github.opentelekomcloud</groupId>
            <artifactId>opentelekomcloud-functiongraph-java-events</artifactId>
            <version>|pom_version|</version>
          </dependency>


          <dependency>
            <groupId>io.github.opentelekomcloud</groupId>
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
          implementation 'opentelekomcloud-functiongraph-java-core:|pom_version|'
          implementation 'opentelekomcloud-functiongraph-java-events:|pom_version|'
          implementation 'opentelekomcloud-functiongraph-java-test:|pom_version|'
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
