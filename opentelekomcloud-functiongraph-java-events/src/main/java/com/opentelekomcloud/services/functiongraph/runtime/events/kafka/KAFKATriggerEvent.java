package com.opentelekomcloud.services.functiongraph.runtime.events.kafka;

import com.google.gson.annotations.SerializedName;
import com.opentelekomcloud.services.functiongraph.runtime.core.TriggerEvent;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class KAFKATriggerEvent implements TriggerEvent {

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
   * Trigger type
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
  private KAKFARecord[] records;


  /**
   * 
   * @return clone of message records.
   */
  public KAKFARecord[] getRecords() {
    return (KAKFARecord[]) this.records.clone();
  }

  public void setRecords(KAKFARecord[] records) {
    this.records = (KAKFARecord[]) records.clone();
  }

}
