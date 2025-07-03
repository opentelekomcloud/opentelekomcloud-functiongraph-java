package com.opentelekomcloud.services.functiongraph.runtime.events.lts;


import com.google.gson.annotations.SerializedName;
import com.opentelekomcloud.services.functiongraph.runtime.core.TriggerEvent;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class LTSTriggerEvent implements TriggerEvent {

  /**
   * LTS message body
   */
  @SerializedName("lts")
  private LTSBody lts;
  
}
