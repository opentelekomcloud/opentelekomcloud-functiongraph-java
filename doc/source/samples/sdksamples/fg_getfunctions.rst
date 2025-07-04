Get list of FunctionGraph
=========================
.. toctree::
   :hidden:

Example of FuctionGraph on how to get a list FunctionGraph names.

API Reference
-------------

See `API Reference: Querying Functions <https://docs.otc.t-systems.com/function-graph/api-ref/apis/function_lifecycle_management/querying_functions.html>`_

Configuration
-------------

Handler name:
  - com.otc.sdk.samples.functiongraph.services.fg.ListFunctions.handleRequest

Permissions:
  - Agency (Cloud Service Type) with FG permissions, e.g.:

 .. code-block:: json

    {
      "Version": "1.1",
      "Statement": [
          {
              "Action": [
                  "functiongraph:*:list",
              ],
              "Effect": "Allow"
          }
      ]
    }

Environment variables:
 - INSTANCE_ID instance id or comma separated list of instance ids
 - FG_ENDPOINT_URL ecs endpoint url, e.g. https://functiongraph.eu-de.otc.t-systems.com


Source code
-----------

.. literalinclude:: /../../samples-doc/doc-sample-sdk/src/main/java/com/otc/sdk/samples/functiongraph/services/fg/ListFunctions.java
    :language: java
    :caption: :github_repo_master:`ListFunctions.java <samples-doc/doc-sample-sdk/src/main/java/com/otc/sdk/samples/functiongraph/services/fg/ListFunctions.java>`
