Thumbnail Sample
=================
.. toctree::
   :hidden:


Thumbnail is a sample that processes an image uploaded to OBS,
resizes it to fit within a maximum dimension, and uploads the resized
image back to another OBS using FunctionGraph with OBS trigger event.

Source for this sample can be found in: :github_repo_master:`/samples-doc/doc-sample-obs-thumbnail <samples-doc/doc-sample-obs-thumbnail>`.


Overview
--------

Following diagram shows components used in this example:

.. image:: ./thumbnail.drawio.svg
  :width: 800
  :alt: Components



Deployment
----------
This sample can be deployed using Terraform,
see :ref:`ref_terraform_setup` for setup details.


References
----------

In this sample the `huaweicloud-sdk-java-obs <https://github.com/huaweicloud/huaweicloud-sdk-java-obs>`_ 
is used to access OBS with following dependency added to the pom.xml:

.. code-block:: java
  :caption: :github_repo_master:`/samples-doc/doc-sample-obs-thumbnail <samples-doc/doc-sample-obs-thumbnail/pom.xml>`

    <dependency>
      <groupId>com.huaweicloud</groupId>
      <artifactId>esdk-obs-java</artifactId>
      <version>3.25.5</version>
    </dependency>

Dokumentation on the `huaweicloud-sdk-java-obs` can be found here: `API Overview (SDK for Java) <https://support.huaweicloud.com/intl/en-us/sdk-java-devg-obs/obs_21_0002.html>`_

