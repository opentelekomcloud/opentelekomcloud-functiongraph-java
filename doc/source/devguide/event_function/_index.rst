Event Functions
==========================================
.. toctree::
   :maxdepth: 2
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
   Samples <samples>

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

  .. tab:: GitHub and Maven

    To use GitHub maven repository modify your ``settings.xml`` (e.g. ~/.m2/settings.xml) as follows.

    If you need to use a proxy for internet connections, see `Configuring a proxy <https://maven.apache.org/guides/mini/guide-proxies.html>`_.

    The environment variable ``GITHUB_TOKEN`` has to be set with your `GitHub personal access token <https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#authenticating-with-a-personal-access-token>`_,
    e.g. in your ``~/.profile``.

     Detailed instructions can be found `Working with the Apache Maven registry: Installing a package <https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#installing-a-package>`_

     .. code-block:: xml
        :caption: settings.xml

        <servers>
          <server>
            <id>github</id>
            <configuration>
              <httpHeaders>
                <property>
                  <name>Authorization</name>
                  <value>Bearer ${env.GITHUB_TOKEN}</value>
                </property>
              </httpHeaders>
            </configuration>
        </servers>

        <profiles>
          <profile>
            <id>default</id>

            <repositories>
              <repository>
                <id>central</id>
                <url>https://repo1.maven.org/maven2</url>
              </repository>

              <repository>
                <id>github</id>
                <url>https://maven.pkg.github.com/opentelekomcloud/opentelekomcloud-functiongraph-java</url>

                <releases>
                  <enabled>true</enabled>
                  <updatePolicy>daily</updatePolicy>
                </releases>

                <snapshots>
                  <enabled>true</enabled>
                  <updatePolicy>always</updatePolicy>
                </snapshots>

              </repository>
            </repositories>
          </profile>
        </profiles>

        <activeProfiles>
          <activeProfile>default</activeProfile>
        </activeProfiles>

  .. tab:: GitHub and Gradle

     To use GitHub maven repository with Gradle see:
     `Working with the Gradle registry: Using a published package <https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#using-a-published-package>`_

     The environment variables ``GITHUB_TOKEN`` and ``GITHUB_USERNAME`` have to be set with your `GitHub personal access token <https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#authenticating-with-a-personal-access-token>`_,
     e.g. in your ``~/.profile``.

     Add the repository to your build.gradle file (Gradle Groovy):

    .. code-block:: groovy
       :caption: build.gradle

        repositories {

          mavenCentral()

          maven {
              url = uri("https://maven.pkg.github.com/opentelekomcloud/opentelekomcloud-functiongraph-java")
              credentials {
                  username = project.findProperty("gpr.user") ?: System.getenv("GITHUB_USERNAME")
                  password = project.findProperty("gpr.key") ?: System.getenv("GITHUB_TOKEN")
              }
          }

          mavenLocal()

        }



  .. tab:: Maven Central

     Comming soon: `Maven Central Repository <https://search.maven.org/search?q=g:com.opentelekomcloud-functiongraph>`_


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
