/*
 * Copyright (c) 2025 T-Systems International GmbH.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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