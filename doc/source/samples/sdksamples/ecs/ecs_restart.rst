Restarting ECSs in a Batch
==========================

.. toctree::
   :hidden:

Example of FuctionGraph on how to stop one or more ECS instances.

API Reference
-------------

See `API Reference: Restarting ECS in a Batch <https://docs.otc.t-systems.com/elastic-cloud-server/api-ref/apis_recommended/batch_operations/restarting_ecss_in_a_batch.html>`_

Configuration
-------------

Handler name:
  - com.otc.sdk.samples.functiongraph.services.ecs.RestartECSInstance.handleRequest

Permissions:
  - Agency (Cloud Service Type) with ECS reboot permissions, e.g.:

 .. code-block:: json

    {
      "Version": "1.1",
      "Statement": [
          {
              "Action": [
                  "ecs:*:reboot"
              ],
              "Effect": "Allow"
          }
      ]
    }

Environment variables:
 - INSTANCE_ID instance id or comma separated list of instance ids
 - ECS_ENDPOINT_URL ecs endpoint url, e.g. https://ecs.eu-de.otc.t-systems.com


Source code
-----------

.. literalinclude:: /../../samples-doc/doc-sample-sdk/src/main/java/com/otc/sdk/samples/functiongraph/services/ecs/RestartECSInstance.java
    :language: java
    :caption: :github_repo_master:`RestartECSInstance.java <samples-doc/doc-sample-sdk/src/main/java/com/otc/sdk/samples/functiongraph/services/ecs/RestartECSInstance.java>`
