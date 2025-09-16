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

package com.opentelekomcloud.services.functiongraph.runtime.events.smn;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * SMNBodyEntity is used to represent the body of an SMN event.
 * It contains information such as topic URN, timestamp, message attributes,
 * message, type, message ID, and subject.
 */
@Data
@ToString(includeFieldNames = true)
@NoArgsConstructor
public class SMNBodyEntity {

  /**
   * Pattern used for timestamp in SMN Event Record
   * e.g. "2018-01-09T07:11:40Z"
   */
  @SuppressWarnings("unused")
  public transient static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

  /**
   * Topic URN
   */
  @SerializedName("topic_urn")
  private String topicUrn;

  /**
   * Timestamp
   */
  @SerializedName("timestamp")
  private String timestamp;

  /**
   * Get timestamp as Instant
   * 
   * @return timestamp as Instant
   */
  public Instant getTimestampAsInstant() {
    return OffsetDateTime.parse(this.timestamp).toInstant();
  }

  /**
   * Message attributes
   */
  @SerializedName("message_attributes")
  private Map<String, String> messageAttributes;

  /**
   * SMN Message
   */
  @SerializedName("message")
  private String message;

  /**
   * SMN Type
   */
  @SerializedName("type")
  private String type;

  /**
   * SMN message id
   */
  @SerializedName("message_id")
  private String messageId;

  /**
   * MSN subject
   */
  @SerializedName("subject")
  private String subject;

}
