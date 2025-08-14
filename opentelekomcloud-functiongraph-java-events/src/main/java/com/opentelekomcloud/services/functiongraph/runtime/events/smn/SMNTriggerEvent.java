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

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.opentelekomcloud.services.functiongraph.runtime.core.TriggerEvent;
import com.opentelekomcloud.services.functiongraph.runtime.events.smn.utils.SMNTimestampInstantAdapter;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * SMNTriggerEvent is used to represent the event triggered by SMN.
 * It contains an array of SMNRecordEntity, function name, request ID, and timestamp.
 */
@Data
@ToString(includeFieldNames = true)
@NoArgsConstructor
public class SMNTriggerEvent implements TriggerEvent {

  /**
   * SMN Records
   */
  @SerializedName("record")
  private SMNRecordEntity[] record;

  /**
   * Function name
   */
  @SerializedName("functionname")
  private String functionName;

  /**
   * Request ID
   */
  @SerializedName("requestId")
  private String requestId;

  /**
   * Event type
   */
  @SerializedName("timestamp")
  @JsonAdapter(SMNTimestampInstantAdapter.class)
  private Instant  timestamp;

  /**
   * 
   * @return smn record clones
   */
  public SMNRecordEntity[] getRecord() {
    return this.record.clone();
  }

  /**
   * Set SMN record
   * 
   * @param record SMNRecord array
   */
  public void setRecord(SMNRecordEntity[] record) {
    this.record = record.clone();
  }

}
