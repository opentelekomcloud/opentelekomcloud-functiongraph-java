package com.opentelekomcloud.services.functiongraph.runtime.events.lts;


import com.opentelekomcloud.services.functiongraph.runtime.core.TriggerEvent;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
public class LTSTriggerEvent implements TriggerEvent {

  /**
   * LTS message body
   */
  private LTSBody lts;
  
}
