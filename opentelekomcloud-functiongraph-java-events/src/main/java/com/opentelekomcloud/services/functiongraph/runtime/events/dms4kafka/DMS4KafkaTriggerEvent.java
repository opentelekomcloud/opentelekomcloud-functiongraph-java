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

package com.opentelekomcloud.services.functiongraph.runtime.events.dms4kafka;

import com.google.gson.annotations.SerializedName;
import com.opentelekomcloud.services.functiongraph.runtime.core.TriggerEvent;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * DMS4KafkaTriggerEvent is used to represent the event triggered by DMS for Kafka.
 * It contains information such as event version, region, event time, trigger type, instance ID,
 * and Kafka records.
 */
@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class DMS4KafkaTriggerEvent implements TriggerEvent {

  /**
   * Event version
   */
  @SerializedName("event_version")
  private String eventVersion;

  /**
   * region
   */
  @SerializedName("region")
  private String region;

  
  /**
   * Event time
   */
  @SerializedName("event_time")
  private long eventTime;

  /**
   * Trigger type: KAFKA
   */
  @SerializedName("trigger_type")
  private String triggerType;

  /**
   * Instance Id
   */
  @SerializedName("instance_id")
  private String instanceId;

  /**
   * Kafka records
   */
  @SerializedName("records")
  private DMS4KafkaRecordEntity[] records;


  /**
   * 
   * @return clone of message records.
   */
  public DMS4KafkaRecordEntity[] getRecords() {
    return this.records.clone();
  }

  public void setRecords(DMS4KafkaRecordEntity[] records) {
    this.records = records.clone();
  }

}
