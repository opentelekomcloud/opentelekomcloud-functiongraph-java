##########################################################
# code bucket
##########################################################
resource "opentelekomcloud_obs_bucket" "codebucket" {
  bucket = format("%s-%s", var.prefix, "codebucket")
  acl    = "private"

  tags = {
    "app_group" = var.tag_app_group
  }
}

##########################################################
# code bucket object
##########################################################
resource "opentelekomcloud_obs_bucket_object" "code_object" {
  bucket = opentelekomcloud_obs_bucket.codebucket.bucket
  key    = format("%s/%s", "code", var.zip_file_name)
  source = format("${path.root}/../../../target/%s", var.zip_file_name)
  etag   = filemd5(format("${path.root}/../../../target/%s", var.zip_file_name))

}

##########################################################
# store md5 of zip file in state
##########################################################
resource "terraform_data" "replacement" {
  input = [
    filemd5(format("${path.root}/../../../target/%s", var.zip_file_name))
  ]
}

##########################################################
# function
##########################################################
resource "opentelekomcloud_fgs_function_v2" "MyFunction" {
  depends_on = [opentelekomcloud_obs_bucket_object.code_object]
  name       = format("%s_%s", var.prefix, var.function_name)
  app        = "default"
  # agency      = var.agency_name
  handler          = "index.handler"
  description      = "Terraform deployment of doc-sample-springboot-3.x-rest"
  memory_size      = 512
  timeout          = 30
  max_instance_num = 400

  runtime   = "http"
  code_type = "obs"
  code_url  = format("https://%s/code/%s", opentelekomcloud_obs_bucket.codebucket.bucket_domain_name, var.zip_file_name)

  code_filename = var.zip_file_name

  log_group_id   = opentelekomcloud_lts_group_v2.MyLogGroup.id
  log_group_name = opentelekomcloud_lts_group_v2.MyLogGroup.group_name

  log_topic_id   = opentelekomcloud_lts_stream_v2.MyLogStream.id
  log_topic_name = opentelekomcloud_lts_stream_v2.MyLogStream.stream_name

  tags = {
    "app_group" = var.tag_app_group
  }

  lifecycle {
    # replace if code in bucket changed
    replace_triggered_by = [
      terraform_data.replacement
    ]

  }

}

##########################################################
# log group
##########################################################
resource "opentelekomcloud_lts_group_v2" "MyLogGroup" {
  group_name  = format("%s_%s_%s", var.prefix, var.function_name, "log_group")
  ttl_in_days = 1

  tags = {
    "app_group" = var.tag_app_group
  }
}

##########################################################
# log stream
##########################################################
resource "opentelekomcloud_lts_stream_v2" "MyLogStream" {
  group_id    = opentelekomcloud_lts_group_v2.MyLogGroup.id
  stream_name = format("%s_%s_%s", var.prefix, var.function_name, "log_stream")

  tags = {
    "app_group" = var.tag_app_group
  }
}

##########################################################
# test event "greeting"
##########################################################
resource "opentelekomcloud_fgs_event_v2" "event_greeting" {
  function_urn = opentelekomcloud_fgs_function_v2.MyFunction.urn
  name         = "event-greeting"
  content = base64encode(jsonencode({

    "body" = "",
    "requestContext" = {
      "apiId"     = "bc1dcffd-aa35-474d-897c-d53425a4c08e"
      "requestId" = "11cdcdcf33949dc6d722640a13091c77"
      "stage"     = "RELEASE"
    }
    "queryStringParameters" = {
      "responseType" = "html"
    }
    "httpMethod" = "GET"
    "pathParameters" = {
      "name" = "John Doe"
    }
    "path" = "/greeting",
    "headers" = {
      "accept-language"           = "q=0.5,en-US;q=0.3,en;q=0.2"
      "accept-encoding"           = "gzip, deflate, br"
      "x-forwarded-port"          = "443"
      "x-forwarded-for"           = "103.218.216.98"
      "accept"                    = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
      "upgrade-insecure-requests" = "1"
      "host"                      = "host"
      "x-forwarded-proto"         = "https"
      "pragma"                    = "no-cache"
      "cache-control"             = "no-cache"
      "x-real-ip"                 = "103.218.216.98"
      "user-agent"                = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0"
    }
    "isBase64Encoded" = true
    }
  ))
}

##########################################################
# test event "byebye"
##########################################################
resource "opentelekomcloud_fgs_event_v2" "event_bye_bye" {
  function_urn = opentelekomcloud_fgs_function_v2.MyFunction.urn
  name         = "event-byebye"
  content = base64encode(jsonencode({

    "body" = "",
    "requestContext" = {
      "apiId"     = "bc1dcffd-aa35-474d-897c-d53425a4c08e"
      "requestId" = "11cdcdcf33949dc6d722640a13091c77"
      "stage"     = "RELEASE"
    }
    "queryStringParameters" = {
      "responseType" = "html"
    }
    "httpMethod" = "GET"
    "pathParameters" = {
      "name" = "Jane Doe"
    }
    "path" = "/byebye",
    "headers" = {
      "accept-language"           = "q=0.5,en-US;q=0.3,en;q=0.2"
      "accept-encoding"           = "gzip, deflate, br"
      "x-forwarded-port"          = "443"
      "x-forwarded-for"           = "103.218.216.98"
      "accept"                    = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
      "upgrade-insecure-requests" = "1"
      "host"                      = "host"
      "x-forwarded-proto"         = "https"
      "pragma"                    = "no-cache"
      "cache-control"             = "no-cache"
      "x-real-ip"                 = "103.218.216.98"
      "user-agent"                = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0"
    }
    "isBase64Encoded" = true
    }
  ))
}


output "codebucket" {
  value = format("https://%s/code/%s", opentelekomcloud_obs_bucket.codebucket.bucket_domain_name, var.zip_file_name)
}
