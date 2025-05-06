package com.opentelekomcloud.services.functiongraph.runtime.events.s3obs;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString(includeFieldNames=true)
public class S3Record {
  /**
   * event version
   */
  @SerializedName("eventVersion")
  private String eventVersion;

  /**
   * Event source (aws:s3)
   */
  @SerializedName("eventSource")
  private String eventSource;

  /**
   * Region
   */
  @SerializedName("awsRegion")
  private String awsRegion;

  /**
   * event time (2024-12-02T09:49:37.939Z)
   */
  @SerializedName("eventTime")
  private String eventTime;

  /**
   * event name
   */
  @SerializedName("eventName")
  private String eventName;

  /**
   * User Identity
   */
  @SerializedName("userIdentity")
  private S3IdEntity userIdentity;

  /**
   * request parameter
   */
  @SerializedName("requestParameters")
  private RequestParameters requestParameters;

  /**
   * response elements
   */
  @SerializedName("responseElements")
  private Map<String, Object> responseElements;

  /**
   * s3 OBS
   */
  @SerializedName("s3")
  private S3Body s3obs;

  public S3Record(String eventVersion, String eventSource, String awsRegion, String eventTime, String eventName,
      S3IdEntity userIdentity, RequestParameters requestParameters,
      Map<String, Object> responseElements, S3Body s3obs) {
    this.eventVersion = eventVersion;
    this.eventSource = eventSource;
    this.awsRegion = awsRegion;
    this.eventTime = eventTime;
    this.eventName = eventName;
    this.userIdentity = userIdentity;
    this.requestParameters = requestParameters;
    this.responseElements = responseElements;
    this.s3obs = s3obs;
  }
 
}