.. _sdk-samples-ref:

SDK Samples
===========
.. toctree::
  :hidden:

   ECS start <ecs_start>
   ECS stop <ecs_stop>
   ECS restart <ecs_restart>
   FG list functions <fg_getfunctions>

To access OpenTelekomCloud services from FunctionGraph using the API
request have to be signed.
The OTC Java SDK v1 provides utility methods to handle this request signing.

SDK samples can be found in: :github_repo_master:`/samples-doc/doc-sample-sdk <samples-doc/doc-sample-sdk>`

.. note::
   To prevent extended logging deploy function with :ref:`class-isolation-ref`.

Dependencies
------------
Currently the `OTC Java SDK v1` is only available as jar file.

To use this jar the pom.xml has to be adapted.

After adaption install the OTC Java SDK v1 jar (:github_repo_master:`com-otc-sdk-core-1.0.0.jar <samples-doc/doc-sample-sdk/lib/com-otc-sdk-core-1.0.0.jar>`) using:

.. code-block:: console
  :emphasize-lines: 6
  :caption: Install local jar

  mvn validate

As an example see:

.. literalinclude:: /../../samples-doc/doc-sample-sdk/pom.xml
    :language: xml
    :caption: :github_repo_master:`pom.xml <samples-doc/doc-sample-sdk/pom.xml>`

