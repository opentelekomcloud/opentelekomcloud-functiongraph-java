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

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * S3TriggerEventRecord is used to represent a single record in the S3/OBS event.
 * It contains information such as event version, source, region, time, name, user identity,
 * request parameters, response elements, and S3 entity.
 */
@NoArgsConstructor
@Data
@ToString(includeFieldNames=true)
public class S3TriggerEventRecord {
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
  private UserIdentityEntity userIdentity;

  /**
   * request parameter
   */
  @SerializedName("requestParameters")
  private RequestParametersEntity requestParameters;

  /**
   * response elements
   */
  @SerializedName("responseElements")
  private ResponseElementsEntity responseElements;

  /**
   * s3 OBS
   */
  @SerializedName("s3")
  private S3Entity s3;
  /**
   * S3 Event Notification Record constructor
   *
   * @param awsRegion AWS region where the event occurred
   * @param eventName Name of the event (e.g., "ObjectCreated:Put")
   * @param eventSource Source of the event (e.g., "aws:s3")
   * @param eventTime Time when the event occurred
   * @param eventVersion Version of the event schema
   * @param requestParameters Request parameters associated with the event
   * @param responseElements Response elements from the S3 service
   * @param s3 S3 entity containing bucket and object details
   * @param userIdentity User identity information related to the event
   */
  public S3TriggerEventRecord(String awsRegion, String eventName, String eventSource, String eventTime,
                                         String eventVersion, RequestParametersEntity requestParameters,
                                         ResponseElementsEntity responseElements, S3Entity s3,
                                         UserIdentityEntity userIdentity) {
            this.awsRegion = awsRegion;
            this.eventName = eventName;
            this.eventSource = eventSource;

            // if (eventTime != null)
            // {
            //     this.eventTime = DateTime.parse(eventTime);
            // }
            this.eventTime = eventTime;

            this.eventVersion = eventVersion;
            this.requestParameters = requestParameters;
            this.responseElements = responseElements;
            this.s3 = s3;
            this.userIdentity = userIdentity;
        }
 
}