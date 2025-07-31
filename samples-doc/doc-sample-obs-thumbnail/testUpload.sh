
# https://github.com/opentelekomcloud/obs-s3/blob/master/s3cmd/README.md
s3cmd --access_key=${OTC_SDK_AK} --secret_key=${OTC_SDK_SK} --no-ssl \
  put ./src/test/resources/otc.jpg \
  s3://doc-sample-obs-thumbnail-createthumbnails-images/otc.jpg