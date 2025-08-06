Thumbnail Sample
=================

.. toctree::
   :hidden:


Thumbnail is a sample that processes an image uploaded to OBS,
resizes it to fit within a maximum dimension, and uploads the resized
image back to another OBS using FunctionGraph with OBS trigger event.

Source for this sample can be found in:
:github_repo_master:`/samples-doc/doc-sample-obs-thumbnail <samples-doc/doc-sample-obs-thumbnail>`.

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

Terraform deployment scripts can be found in:
:github_repo_master:`/samples-doc/doc-sample-obs-thumbnail/src/main/tf <samples-doc/doc-sample-obs-thumbnail/src/main/tf>`


.. tabs::

  .. tab:: variables.tf

     Adapt file *variables.tf* according to your needs, see comments.

     .. literalinclude:: /../../samples-doc/doc-sample-obs-thumbnail/src/main/tf/variables.tf
        :language: terraform
        :caption: /variables.tf

  .. tab:: main.tf

    This files contains all resources to be created for this sample.

     .. literalinclude:: /../../samples-doc/doc-sample-obs-thumbnail/src/main/tf/main.tf
        :language: terraform
        :caption: /main.tf

  .. tab:: provider.tf

     This file contains the terraform provider configuration.

     Adapt it according to :ref:`ref_terraform_setup`

     .. note::

        Check especially the **backend "s3"** configuration for **bucket** and **key**.

     .. literalinclude:: /../../samples-doc/doc-sample-obs-thumbnail/src/main/tf/provider.tf
        :language: terraform
        :caption: /provider.tf

To deploy use:

.. code-block:: bash

  terraform apply --auto-approve


References
----------

In this sample the `huaweicloud-sdk-java-obs <https://github.com/huaweicloud/huaweicloud-sdk-java-obs>`_
is used to access OBS with following dependency added to the pom.xml:

.. code-block:: java
  :caption: :github_repo_master:`/samples-doc/doc-sample-obs-thumbnail/pom.xml <samples-doc/doc-sample-obs-thumbnail/pom.xml>`

    <dependency>
      <groupId>com.huaweicloud</groupId>
      <artifactId>esdk-obs-java</artifactId>
      <version>3.25.5</version>
    </dependency>

Documentation on the `huaweicloud-sdk-java-obs` can be found here:
`API Overview (SDK for Java) <https://support.huaweicloud.com/intl/en-us/sdk-java-devg-obs/obs_21_0002.html>`_

Upload image file to source bucket
----------------------------------

Linux/Ubuntu
^^^^^^^^^^^^

.. note::

   For *s3cmd* tool installation see: `S3cmd <https://github.com/opentelekomcloud/obs-s3/tree/master/s3cmd>`_
   on github.


To upload image to source bucket, following script an be used for Ubuntu users:

.. literalinclude:: /../../samples-doc/doc-sample-obs-thumbnail/testUpload.sh
   :language: bash
   :caption: /samples-doc/doc-sample-obs-thumbnail/testUpload.sh


Microsoft Windows
^^^^^^^^^^^^^^^^^

.. note::
   For *Microsoft Windows*, see `OBS Browser+ <https://docs.otc.t-systems.com/object-storage-service/tool-guide/index.html#>`_


Alternatives (unsupported)
^^^^^^^^^^^^^^^^^^^^^^^^^^

Huawei obsutil
""""""""""""""

.. note::

  Huawei *obsutil* is available for Windows, Linux and Mac from Huawei,
  see: `obsutil Introduction <https://support.huaweicloud.com/intl/en-us/utiltg-obs/obs_11_0001.html>`_

.. literalinclude:: /../../samples-doc/doc-sample-obs-thumbnail/testUpload.cmd
   :language: winbatch
   :caption: /samples-doc/doc-sample-obs-thumbnail/testUpload.cmd

