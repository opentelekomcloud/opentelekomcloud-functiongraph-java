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

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * SMNRecordEntity is used to represent the record entity in SMN events.
 * It contains information such as event version, event subscription URN, event source, and SMN body.
 */
@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class SMNRecordEntity {
  /**
   * Event version
   */
  @SerializedName("event_version")
  private String eventVersion;

  /**
   * Event subscription URN
   */
  @SerializedName("event_subscription_urn")
  private String eventSubscriptionUrn;

  /**
   * Event source
   */
  @SerializedName("event_source")
  private String eventSource;

  /**
   * SMN body
   */
  @SerializedName("smn")
  private SMNBodyEntity smn;

}
