Deploying a function using Terraform
====================================

Prepare the Terraform environment
---------------------------------

Installing Terraform
^^^^^^^^^^^^^^^^^^^^
Terraform provides installation packages for different environments.

For details, see `Install Terraform <https://developer.hashicorp.com/terraform/tutorials/aws-get-started/install-cli>`_.

For details on how to use Terraform on OpenTelekomCloud, see `Open Telekom Cloud Provider <https://registry.terraform.io/providers/opentelekomcloud/opentelekomcloud/latest/docs>`_

Setting Environment Variables
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Set following environment vafiables:

  .. list-table:: Environment variables
      :widths: 20 20 25
      :header-rows: 1

      * - Name
        - Value
        - Remark

      * - TF_VAR_OTC_SDK_AK
        - Access key
        - see: :api_usage:`Generating an AK and SK<guidelines/calling_apis/ak_sk_authentication/generating_an_ak_and_sk.html>` in API usage guide.

      * - TF_VAR_OTC_SDK_SK
        - Secret key
        - see: :api_usage:`Generating an AK and SK<guidelines/calling_apis/ak_sk_authentication/generating_an_ak_and_sk.html>` in API usage guide.

      * - TF_VAR_OTC_SDK_DOMAIN_NAME
        - Domain Name
        - see: :api_usage:`Obtaining the Domain Name and Domain ID<guidelines/calling_apis/obtaining_required_information.html>` in API usage guide.

      * - TF_VAR_OTC_SDK_PROJECTID
        - Project Id
        - see: :api_usage:`Obtaining a Project ID<guidelines/calling_apis/obtaining_required_information.html>` in API usage guide.

      * - TF_VAR_OTC_SDK_PROJECTNAME
        - Project name
        - see: :api_usage:`Obtaining a Project ID<guidelines/calling_apis/obtaining_required_information.html>` in API usage guide.

      * - AWS_ACCESS_KEY_ID
        - set to ``OTC_SDK_AK``
        - Needed for backend "s3" state.  Set set to OTC_SDK_AK

      * - AWS_SECRET_ACCESS_KEY
        - set to ``OTC_SDK_SK``
        - Needed for backend "s3" state.  Set to OTC_SDK_SK

      * - AWS_REQUEST_CHECKSUM_CALCULATION
        - "when_required"
        - needed for Terraform version > 1.11.1 (*)

      * - AWS_RESPONSE_CHECKSUM_VALIDATION
        - "when_required"
        - needed for Terraform version > 1.11.1 (*)

(*) see: `Remote State OBS <https://community.open-telekom-cloud.com/community?id=community_question&sys_id=1207be61138086d0d15a246ea6744162&view_source=searchResult>`_
, `AWS CLI supported environment variables <https://docs.aws.amazon.com/cli/v1/userguide/cli-configure-envvars.html#envvars-list>`_

  To simplify environment variable handling, you can also set
  variables without ``TF_VAR_``
  prefix in you ~\.profile and run script:
  ``samples-doc/doc-sample-deploy-tf/src/main/tf/setupTFvars.sh``


Creating backend state bucket
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Create an OBS bucket that fits to the settings in
:github_repo_master:`provider.tf <samples-doc/doc-sample-deploy-tf/src/main/tf/provider.tf>`.


Adapt terraform files
^^^^^^^^^^^^^^^^^^^^^

Configure variables in:
:github_repo_master:`variables.tf <samples-doc/doc-sample-deploy-tf/src/main/tf/variables.tf>`.

.. literalinclude:: /../../samples-doc/doc-sample-deploy-tf/src/main/tf/variables.tf
   :language: terraform
   :caption: variables.tf


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

Sample
^^^^^^

Full sample can be found :github_repo_master:`here <samples-doc/doc-sample-deploy-tf>`.

For function deployment, see: 

.. literalinclude:: /../../samples-doc/doc-sample-deploy-tf/src/main/tf/function.tf
   :language: terraform
   :caption: function.tf
