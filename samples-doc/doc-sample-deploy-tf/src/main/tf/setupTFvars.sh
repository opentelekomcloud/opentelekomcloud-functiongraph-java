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