##########################################################
# Create Function
##########################################################
resource "opentelekomcloud_fgs_function_v2" "MyFunction" {
  name = format("%s_%s", var.prefix, var.function_name)
  app  = "default"
  #  agency      = var.agency_name
  handler          = var.function_handler_name
  description      = "Sample on how to deploy using terraform"
  memory_size      = 128
  timeout          = 30
  max_instance_num = 400

  runtime       = "Java17"
  code_type     = "jar"
  func_code     = filebase64(format("${path.module}/../../../target/%s", var.jar_file_name))
  code_filename = var.jar_file_name

  log_group_id   = opentelekomcloud_lts_group_v2.MyLogGroup.id
  log_group_name = opentelekomcloud_lts_group_v2.MyLogGroup.group_name

  log_topic_id   = opentelekomcloud_lts_stream_v2.MyLogStream.id
  log_topic_name = opentelekomcloud_lts_stream_v2.MyLogStream.stream_name
  
  # set some environment variables
  user_data = jsonencode({ "LOG_LEVEL" : "INFO", "DATA" : "Test" })

}

##########################################################
# Create Log Group
##########################################################
resource "opentelekomcloud_lts_group_v2" "MyLogGroup" {
  group_name  = format("%s_%s_%s", var.prefix, var.function_name, "log_group")
  ttl_in_days = 1
}

##########################################################
# Create Log Stream
##########################################################
resource "opentelekomcloud_lts_stream_v2" "MyLogStream" {
  group_id    = opentelekomcloud_lts_group_v2.MyLogGroup.id
  stream_name = format("%s_%s_%s", var.prefix, var.function_name, "log_stream")
}

##########################################################
# Create Test Event
##########################################################
resource "opentelekomcloud_fgs_event_v2" "test_event" {
  function_urn = opentelekomcloud_fgs_function_v2.MyFunction.urn
  name         = "my-value-event"
  content = base64encode(jsonencode({
    "key" = "MyValue"
  }))
}