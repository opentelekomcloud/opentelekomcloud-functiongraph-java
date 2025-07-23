Invoking FunctionGraph
======================

This example demonstrates how to call a FunctionGraph implemented in
python using java.

Prerequisites
-------------
To run java example following environment variables should be set:

.. list-table:: Environtment Variables
   :widths: 25 25 50
   :header-rows: 1

   * - Name
     - Description
     - Example

   * - OTC_USER_NAME
     - User name
     - John Doe

   * - OTC_USER_PASSWORD
     - User password
     - JOHNDOESPASSWORT

   * - OTC_SDK_PROJECTID
     - :otc_fg_api:`Project Id <appendix/obtaining_a_project_id.html>`
     - 87b7f28e70c14498b3c243af5b4#####

   * - OTC_DOMAIN_NAME
     - Domain name (Account ID)
     - OTC-EU-DE-000000000010000#####

   * - OTC_TENANT_NAME
     - Tenant name (Region)
     - eu-de

   * - OTC_AUTH_URL
     - IAM Endpoint
     - https://iam.eu-de.otc.t-systems.com


Example FunctionGraph written in python
---------------------------------------

.. literalinclude:: /../../samples-doc/doc-sample-invoke-fg/functionGraph.py
   :language: python
   :caption: Example FunctionGraph: functionGraph.py

Deploy this FunctionGraph as follows:

- Type: "Event Function":
- Name: "InvokeSamplePython"
- Agency: "Use no agency"
- Runtime: "python 3.10"


Invoking FunctionGraph using Java
---------------------------------

For ``authorization``, see :otc_fg_api:`Making an API Request <calling_apis/making_an_api_request.html>`

For ``Function Execution``, see :otc_fg_api:`Executing a Function Synchronously <apis/function_invocation/executing_a_function_synchronously.html>`

.. note::
   Supported Java Version 11 and above.

.. tabs::

  .. tab:: InvokeFG.java

     .. literalinclude:: /../../samples-doc/doc-sample-invoke-fg/src/main/java/com/opentelekomcloud/samples/InvokeFG.java
        :language: java

  .. tab:: Constants.java

     .. literalinclude:: /../../samples-doc/doc-sample-invoke-fg/src/main/java/com/opentelekomcloud/samples/Constants.java
        :language: java

