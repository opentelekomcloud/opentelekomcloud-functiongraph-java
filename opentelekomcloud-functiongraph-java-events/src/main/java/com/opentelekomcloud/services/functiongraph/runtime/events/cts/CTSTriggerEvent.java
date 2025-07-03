package com.opentelekomcloud.services.functiongraph.runtime.events.cts;

import com.google.gson.annotations.SerializedName;
import com.opentelekomcloud.services.functiongraph.runtime.core.TriggerEvent;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(includeFieldNames=true)
public class CTSTriggerEvent implements TriggerEvent {

  /**
   * CTS message body
   */
  @SerializedName("cts")
  private CTS cts;

}
