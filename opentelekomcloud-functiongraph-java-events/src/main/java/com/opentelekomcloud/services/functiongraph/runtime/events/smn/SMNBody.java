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

import com.google.gson.annotations.SerializedName;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class SMNBody {

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
