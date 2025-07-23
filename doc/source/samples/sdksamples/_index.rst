.. _sdk-samples-ref:

SDK Samples
===========
.. toctree::
  :hidden:

   ECS Service  <ecs/_index>
   FunctionGraph Service <fg/_index>

To access OpenTelekomCloud services from FunctionGraph using the API
request have to be signed.

The community edition of :github_java_sdk:`OTC Java SDK v1<>` provides
utility methods to handle request signing.


.. warning::

   Currently the :github_java_sdk:`OTC Java SDK v1<>` is not available on maven central.

   To use the SDK samples, the dependency has to be built manually:

   Check out the `OTC Java SDK v1` repository and build it using:

    .. code-block:: shell

       # clone repo to your favorite location        
       git clone https://github.com/opentelekomcloud-community/otc-java-sdk-v1.git

       # and change to the directory
       cd otc-java-sdk-v1

       # build the project
       mvn clean install -DskipTests

SDK samples can be found in: :github_repo_master:`/samples-doc/doc-sample-sdk <samples-doc/doc-sample-sdk>`.

.. note::
   To prevent extended logging deploy function with :ref:`class-isolation-ref` set to `enabled`.

Dependencies
------------

The community edition of the `OTC Java SDK v1` can be found on :github_java_sdk:`otc-java-sdk-v1<>`

For installation see: :github_java_sdk:`Getting started <blob/main/doc/source/gettingstarted/index.rst>`

To use this jar the pom.xml has to be adapted.

As an example see:

.. literalinclude:: /../../samples-doc/doc-sample-sdk/pom.xml
    :language: xml
    :caption: :github_repo_master:`pom.xml <samples-doc/doc-sample-sdk/pom.xml>`

