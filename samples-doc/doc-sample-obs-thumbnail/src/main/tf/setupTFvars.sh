#!/bin/bash

for var in "${!OTC_@}"; do
    export $(printf 'TF_VAR_%s=%s\n' "$var" "${!var}")
done


export AWS_ACCESS_KEY_ID=$OTC_SDK_AK
export AWS_SECRET_ACCESS_KEY=$OTC_SDK_SK

# https://community.open-telekom-cloud.com/community?id=community_question&sys_id=1207be61138086d0d15a246ea6744162&view_source=searchResult
export AWS_REQUEST_CHECKSUM_CALCULATION=when_required
export AWS_RESPONSE_CHECKSUM_VALIDATION=when_required