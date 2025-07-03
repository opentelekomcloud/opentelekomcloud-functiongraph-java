package com.opentelekomcloud.services.functiongraph.runtime.events.cts;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class CTS {
  /**
   * Event generation time
   */
  @SerializedName("time")
  private long time;

  /**
   * User information that triggered the event
   */
  @SerializedName("user")
  private User user;

  /**
   * Event request content
   */
  @SerializedName("request")
  private Map<String, String> request;

  /**
   * Event response content
   */
  @SerializedName("response")
  private Map<String, String> response;

  /**
   * http response code
   */
  @SerializedName("code")
  private int code;

  /**
   * The service name that triggers the event
   */
  @SerializedName("service_type")
  private String serviceType;

  /**
   * The resource type that triggers the event
   */
  @SerializedName("resource_type")
  private String resourceType;

  /**
   * The name of the resource that triggered the event
   */
  @SerializedName("resource_name")
  private String resourceName;

  /**
   * Unique identifier of the event triggering resource
   */
  @SerializedName("resource_id")
  private String resourceId;

  /**
   * Trace name
   */
  @SerializedName("trace_name")
  private String traceName;

  /**
   * How events are triggered
   * (such as ConsoleAction: represents foreground operations)
   */
  @SerializedName("trace_type")
  private String traceType;

  /**
   * CTS service receiving event time
   */
  @SerializedName("record_time")
  private long recordTime;

  /**
   * Unique identifier of the current event
   */
  @SerializedName("trace_id")
  private String traceId;

  /**
   * Event Trace Status
   */
  @SerializedName("trace_status")
  private String traceStatus;

}
