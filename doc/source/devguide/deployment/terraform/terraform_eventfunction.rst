Deploying an Event Function using Terraform
===========================================

This section describes on how to deploy an Event Function using Terraform.

Full sample can be found in :github_repo_master:`doc-sample-deploy-tf <samples-doc/doc-sample-deploy-tf>`.


Adapt Terraform files
^^^^^^^^^^^^^^^^^^^^^

Configure variables in:
:github_repo_master:`variables.tf <samples-doc/doc-sample-deploy-tf/src/main/tf/variables.tf>`.

* for ``function_handler_name``, see :ref:`ref_functionhandler`.
* for ``jar_file_name``, see :ref:`ref_deploymentpackage`.

.. literalinclude:: /../../samples-doc/doc-sample-deploy-tf/src/main/tf/variables.tf
   :language: terraform
   :caption: src/main/tf/variables.tf

The following "function.tf" configurationdeploys:

* ``Event function``
* ``LogGroup``
* ``LogStream``
* ``Event``

.. literalinclude:: /../../samples-doc/doc-sample-deploy-tf/src/main/tf/function.tf
   :language: terraform
   :caption: src/main/tf/function.tf

Using Terraform
^^^^^^^^^^^^^^^

Run following commands in folder ``src/main/tf`` :

.. code-block:: shell

   # init terraform
   terraform init

   # plan terraform
   terraform plan

   # apply changes
   terraform apply
