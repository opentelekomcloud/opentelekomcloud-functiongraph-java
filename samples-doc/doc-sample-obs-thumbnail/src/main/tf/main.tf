
##########################################################
# This bucket is used to store the function code  
##########################################################
resource "opentelekomcloud_obs_bucket" "codebucket" {
  bucket = format("%s-%s", var.prefix, "codebucket")
  acl    = "private"

  force_destroy = true

  tags = {
    "app_group" = var.tag_app_group
  }
}

##########################################################
# This code bucket object stores the function code
##########################################################
resource "opentelekomcloud_obs_bucket_object" "code_object" {
  bucket = opentelekomcloud_obs_bucket.codebucket.bucket
  key    = format("%s/%s", "code", var.jar_file_name)
  source = format("${path.root}/../../../target/%s", var.jar_file_name)
  etag   = filemd5(format("${path.root}/../../../target/%s", var.jar_file_name))

}

##########################################################
# store md5 of zip file in state     
##########################################################
resource "terraform_data" "replacement" {
  input = [
    filemd5(format("${path.root}/../../../target/%s", var.jar_file_name))
  ]
}

##########################################################
# custom role
##########################################################
resource "opentelekomcloud_identity_role_v3" "role" {
  display_name  = format("%s-%s-role", var.prefix, var.function_name)
  description   = "Role for FunctionGraph to access OBS"
  display_layer = "project"

  statement {
    effect = "Allow"
    action = [
      "functiongraph:*:*",
      "lts:*:*",
    ]
  }

  statement {
    effect = "Allow"
    action = [
      "obs:*:*",
    ]
    resource = [
      "OBS:*:*:object:*",
      format("OBS:*:*:bucket:%s", opentelekomcloud_s3_bucket.inbucket.bucket),
      format("OBS:*:*:bucket:%s", opentelekomcloud_s3_bucket.outbucket.bucket),
    ]
  }

}

##########################################################
# Agency for FunctionGraph
##########################################################
resource "opentelekomcloud_identity_agency_v3" "agency" {
  depends_on            = [opentelekomcloud_identity_role_v3.role]
  delegated_domain_name = "op_svc_cff"

  name        = format("%s-%s-agency", var.prefix, var.function_name)
  description = "Agency for FunctionGraph to access OBS"

  project_role {
    all_projects = true
    project      = "eu-de"
    roles = [
      opentelekomcloud_identity_role_v3.role.display_name
    ]
  }

}


##########################################################
# Create Function
##########################################################
resource "opentelekomcloud_fgs_function_v2" "MyFunction" {
  depends_on       = [opentelekomcloud_obs_bucket_object.code_object]
  name             = format("%s_%s", var.prefix, var.function_name)
  app              = "default"
  agency           = opentelekomcloud_identity_agency_v3.agency.name
  handler          = var.function_handler_name
  initializer_handler = var.initializer_handler_name
  initializer_timeout =  30

  description      = "Sample on how to create Thumbnails from images uploaded to OBS"
  memory_size      = 512
  timeout          = 30
  max_instance_num = 1
  #enable_class_isolation = true
  runtime = "Java17"

  code_type = "obs"
  code_url  = format("https://%s/code/%s", opentelekomcloud_obs_bucket.codebucket.bucket_domain_name, var.jar_file_name)

  code_filename = var.jar_file_name

  log_group_id   = opentelekomcloud_lts_group_v2.MyLogGroup.id
  log_group_name = opentelekomcloud_lts_group_v2.MyLogGroup.group_name

  log_topic_id   = opentelekomcloud_lts_stream_v2.MyLogStream.id
  log_topic_name = opentelekomcloud_lts_stream_v2.MyLogStream.stream_name

  # set some environment variables
  user_data = jsonencode({
    "OUTPUT_BUCKET" : opentelekomcloud_s3_bucket.outbucket.bucket,
    # "RUNTIME_LOG_LEVEL" : "ERROR",
    # "RUNTIME_LOG_PATH" : "/tmp"
  })


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
# Create Log Group
##########################################################
resource "opentelekomcloud_lts_group_v2" "MyLogGroup" {
  group_name  = format("%s_%s_%s", var.prefix, var.function_name, "log_group")
  ttl_in_days = 1

  tags = {
    "app_group" = var.tag_app_group
  }
}

##########################################################
# Create Log Stream
##########################################################
resource "opentelekomcloud_lts_stream_v2" "MyLogStream" {
  group_id    = opentelekomcloud_lts_group_v2.MyLogGroup.id
  stream_name = format("%s_%s_%s", var.prefix, var.function_name, "log_stream")

  tags = {
    "app_group" = var.tag_app_group
  }
}

##########################################################
# Input bucket for source images
##########################################################
resource "opentelekomcloud_s3_bucket" "inbucket" {
  bucket        = lower(format("%s-%s-%s", var.prefix, var.function_name, "images"))
  acl           = "private"
  force_destroy = true

  tags = {
    "app_group" = var.tag_app_group
  }

}

##########################################################
# Output bucket for thumbnail images
##########################################################
resource "opentelekomcloud_s3_bucket" "outbucket" {
  bucket        = lower(format("%s-%s-%s", var.prefix, var.function_name, "images-output"))
  acl           = "private"
  force_destroy = true

  tags = {
    "app_group" = var.tag_app_group
  }

}

##########################################################
# Create OBS Trigger
##########################################################
resource "opentelekomcloud_fgs_trigger_v2" "obstrigger" {
  function_urn = opentelekomcloud_fgs_function_v2.MyFunction.urn
  type         = "OBS"
  event_data = jsonencode({
    "bucket" : opentelekomcloud_s3_bucket.inbucket.bucket
    "events" : [
      "s3:ObjectCreated:*"
    ]
    "name" : lower(format("%s-%s-%s", var.prefix, var.function_name, "event"))

  })
}


