.. _ref_terraform_setup:

Prepare the Terraform environment
---------------------------------

.. toctree::
   :maxdepth: 2
   :hidden:

Installing Terraform
^^^^^^^^^^^^^^^^^^^^
Terraform provides installation packages for different environments.

For details, see `Install Terraform <https://developer.hashicorp.com/terraform/tutorials/aws-get-started/install-cli>`_ on Terraform site.

For details on how to use Terraform on OpenTelekomCloud, see
`Open Telekom Cloud Provider <https://registry.terraform.io/providers/opentelekomcloud/opentelekomcloud/latest/docs>`_.

Setting Environment Variables
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Set following environment variables:

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

.. note:: **Simplify Environment variables handling for Terraform**

    As Terraform can only access environment variables starting with ``TF_VAR_``
    you can use follow script to transform ``OTC_*`` variables to
    ``TF_VAR_OTC_*`` variables:

    .. literalinclude:: /../../samples-doc/doc-sample-deploy-tf/src/main/tf/setupTFvars.sh
      :language: shell
      :caption: src/main/tf/setupTFvars.sh

    You can run this script before running ``terraform init`` to set the environment variables.


Configure ``provider.tf`` file
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

For terraform provider configuration create a file like the following.

Adapt values for:

* ``bucket`` - this is the bucket for terraform state files
* ``key`` - this is the path and name in the bucket of the terraform state file
* ``s3``  - configure OBS endpoint according to your tenant

.. literalinclude:: /../../samples-doc/doc-sample-deploy-tf/src/main/tf/provider.tf
   :language: terraform
   :caption: src/main/tf/provider.tf


Backend state bucket
^^^^^^^^^^^^^^^^^^^^

Terraform must store state about your managed infrastructure and configuration.
This state is used by Terraform to map real world resources to your configuration,
keep track of metadata, and to improve performance for large infrastructures.

See: `Terraform State <https://developer.hashicorp.com/terraform/language/state>`_

.. note::

   As you cannot create the state bucket in this terraform setup,
   you have to create it either:

   * using OpenTelekomCloud OBS console with bucket name as defined in ``provider.tf`` file for ``bucket``.

   * using the OpenTelekomCloud CLI with command `s3cmd <https://github.com/opentelekomcloud/obs-s3/blob/master/s3cmd/README.md>`_

     .. code-block:: shell

          s3cmd \
            --access_key=${OTC_SDK_AK} \
            --secret_key=${OTC_SDK_SK} \
            --no-ssl \
            mb s3://<bucket_name>
