Setup testing of FunctionGraph
==============================

Installation
------------

The :github_repo_master:`com.opentelekomcloud:opentelekomcloud-functiongraph-java-test <opentelekomcloud-functiongraph-java-test>` module provides tools for testing.

To install this module, add following dependencies to your pom.xml:

.. code-block:: xml
  :substitutions:

  <dependencies>
    ...
    <dependency>
      <groupId>io.github.opentelekomcloud</groupId>
      <artifactId>opentelekomcloud-functiongraph-java-test</artifactId>
      <version>|pom_version|</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-api</artifactId>
      <version>5.11.0</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-engine</artifactId>
      <version>5.11.0</version>
      <scope>test</scope>
    </dependency>
    ...
  </dependencies>

also have `maven-surefire-plugin` in your pom.xml:

.. code-block:: xml
  :substitutions:

  <build>
    ... 
    <plugins>
          <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-surefire-plugin</artifactId>
              <version>{SEE: pom.xml}</version>
          </plugin>
    </plugins>
    ...
  </build>
