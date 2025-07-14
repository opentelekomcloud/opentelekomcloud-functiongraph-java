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


Simplify Environment variables handling for Terraform
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

As Terraform can only access environment variables starting with ``TF_VAR_``
you can use follow script to transform ``OTC_*`` variables to
``TF_VAR_OTC_*`` variables:

.. code-block:: shell
    :caption: src/main/tf/setupTFvars.sh

    #!/bin/bash

    # get all env variables starting with "OTC_" and prepend with "TF_VAR_"
    for var in "${!OTC_@}"; do
        export $(printf 'TF_VAR_%s=%s\n' "$var" "${!var}")
    done

    # for provider configuration set AK/SK to be used.
    export AWS_ACCESS_KEY_ID=$OTC_SDK_AK
    export AWS_SECRET_ACCESS_KEY=$OTC_SDK_SK

    # configure terraform s3 backend to work with obs
    # https://community.open-telekom-cloud.com/community?id=community_question&sys_id=1207be61138086d0d15a246ea6744162&view_source=searchResult
    export AWS_REQUEST_CHECKSUM_CALCULATION=when_required
    export AWS_RESPONSE_CHECKSUM_VALIDATION=when_required


Configure ``provider.tf`` file
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

For terraform provider configuration create a file like the following.

Adapt values for:

* ``bucket`` - this is the bucket for terraform state files
* ``key`` - this is the path and name in the bucket of the terraform state file
* ``s3``  - configure OBS endpoint according to your tenant


.. code-block::
    :caption: /src/main/provider.tf

    # ----------------------------------------------------------------------------
    # Secret variables to be injected as envvar (capital letters for Windows systems)
    # - no defaults
    # - Declared as sensitive --> Not printed in console or log if used in resources
    # ----------------------------------------------------------------------------


    # set by environment variable TF_VAR_OTC_SDK_AK
    variable OTC_SDK_AK {
      description = "Personal access key"
      type        = string
      sensitive   = true
    }

    # set by environment variable TF_VAR_OTC_SDK_SK
    variable OTC_SDK_SK {
      description = "Personal secret key"
      type        = string
      sensitive   = true
    }

    # set by environment variable TF_VAR_OTC_SDK_DOMAIN_NAME
    variable OTC_SDK_DOMAIN_NAME {
      description = "Domain Name, eg. OTC-EU-DE-000000000010000XXXXX"
      type        = string
    }

    # set by environment variable TF_VAR_OTC_SDK_PROJECTID
    variable OTC_SDK_PROJECTID {
      description = "Project Id"
      type        = string
    }

    # set by environment variable TF_VAR_OTC_SDK_PROJECTNAME
    variable OTC_SDK_PROJECTNAME {
      description = "Project Name, eg. eu-de_MYPROJECT"
      type        = string
    }


    terraform {
      required_providers {
        # specifies required provider, source and version
        # see https://registry.terraform.io/providers/opentelekomcloud/opentelekomcloud/latest

        opentelekomcloud = {
          source  = "opentelekomcloud/opentelekomcloud"
          version = ">= 1.36.42"
        }
      }
      backend "s3" {
        # See: https://registry.terraform.io/providers/opentelekomcloud/opentelekomcloud/latest/docs/guides/backends

        # (Required) Specifies the endpoint for OpenTelekomCloud OBS.
        # The value is https://obs.{{region}}.otc.t-systems.com.
        # This can also be sourced from the AWS_S3_ENDPOINT environment variable
        endpoints = {
          s3 = "https://obs.eu-de.otc.t-systems.com"
        }

        # (Required) Specifies the bucket name where to store the state.
        # Make sure to create it before.
        bucket = "sample-tf-backend"

        # (Required) Specifies the path to the state file inside the bucket.
        key    = "terraform_state/sample_statefile.tf"

        # (Required) Specifies the region where the bucket is located.
        # This can also be sourced from the AWS_DEFAULT_REGION and
        # AWS_REGION environment variables.
        region = "eu-de"

        # (Required) Skip credentials validation via the STS API.
        # It's mandatory for OpenTelekomClou.
        skip_credentials_validation = true

        # (Required) Skip validation of provided region name.
        # It's mandatory for OpenTelekomCloud.
        skip_region_validation = true

        skip_requesting_account_id = true

        # (Required) Skip usage of EC2 Metadata API.
        # It's mandatory for OpenTelekomCloud.
        skip_metadata_api_check = true

        # (Optional) Do not include checksum when uploading S3 Objects.
        # Useful for some S3-Compatible APIs.
        skip_s3_checksum = true

        # Although the terraform block does not accept variables or locals and
        # all backend configuration values must be hardcoded, you can provide
        # the credentials via the AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY
        # environment variables to access OBS, respectively:
        #
        # export AWS_ACCESS_KEY_ID="your accesskey"
        # export AWS_SECRET_ACCESS_KEY="your secretkey"
        #
        # secret_key                  set env var: AWS_ACCESS_KEY_ID
        # access_key                  set env var: AWS_SECRET_ACCESS_KEY

      }

    }

    # ----------------------------------------------------------------------------
    # Providers settings --> OTC
    # We use the AKSK auth scheme
    # See https://registry.terraform.io/providers/opentelekomcloud/opentelekomcloud/latest/docs
    # ----------------------------------------------------------------------------
    #

    provider "opentelekomcloud" {
      auth_url = "https://iam.eu-de.otc.t-systems.com/v3"

      access_key = var.OTC_SDK_AK
      secret_key = var.OTC_SDK_SK

      domain_name = var.OTC_SDK_DOMAIN_NAME
      tenant_name = var.OTC_SDK_PROJECTNAME

    }


Backend state bucket
^^^^^^^^^^^^^^^^^^^^

As you cannot create the state bucket in this terraform setup,
you have to create it using OpenTelekomCloud OBS console with
bucket name as defined in ``provider.tf`` file for ``bucket``.
