Local Testing of FunctionGraph
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
      <groupId>com.opentelekomcloud</groupId>
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


Configuration of TextContext
----------------------------

To benefit of all features of
:github_repo_master:`com.opentelekomcloud.services.functiongraph.runtime.test.TestContext <opentelekomcloud-functiongraph-java-test/src/main/java/com/opentelekomcloud/services/functiongraph/runtime/test/TestContext>`
provide following environment variables:

.. list-table:: Environment Variables
   :widths: 25 25
   :header-rows: 1

   * - Environment Variable
     - Description

   * - OTC_SDK_AK
     - Access Key

   * - OTC_SDK_SK
     - Secret Key

   * - OTC_SDK_SECURITY_TOKEN
     - Security Token

   * - OTC_SDK_PROJECTID
     - Project Id

In JUnit tests environment variables can be set using 

e.g. a configuration in maven-surfire-plugin for all test classes:

.. code-block:: xml
  :substitutions:

      <plugin>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.5.0</version>
        <configuration>
          <environmentVariables>
            <OTC_SDK_AK>MyAccessKey</OTC_SDK_AK>
            <OTC_SDK_SK>MySecretKey</OTC_SDK_SK>
            <OTC_SDK_SECURITY_TOKEN>MySecretKey</OTC_SDK_SECURITY_TOKEN>
            <OTC_SDK_PROJECTID>MyProjectId</OTC_SDK_PROJECTID>
          </environmentVariables>
        </configuration>
      </plugin>


Or for Java using `System Stubs <https://github.com/webcompere/system-stubs>`_
(compatible with JDK 8 to JDK 16).


Example
-------

As an example see :github_repo_master:`doc-sample-testing-1 <samples/doc-samples/doc-sample-testing-1>`.

