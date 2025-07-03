package com.opentelekomcloud.services.functiongraph.runtime.events.smn;

import com.google.gson.annotations.SerializedName;
import com.opentelekomcloud.services.functiongraph.runtime.core.TriggerEvent;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class SMNTriggerEvent implements TriggerEvent{

  /**
   * SMN Records
   */
  @SerializedName("record")
  private SMNRecord[] record;

  @SerializedName("functionname")
  private String  functionName;

  @SerializedName("requestId")
  private String  requestId;

  @SerializedName("timestamp")
  private String  timestamp;

  /**
   * 
   * @return smn record clones
   */
  public SMNRecord[] getRecord() {
    return (SMNRecord[]) this.record.clone();
  }

  public void setRecord(SMNRecord[] record) {
    this.record = (SMNRecord[]) record.clone();
  }

}
