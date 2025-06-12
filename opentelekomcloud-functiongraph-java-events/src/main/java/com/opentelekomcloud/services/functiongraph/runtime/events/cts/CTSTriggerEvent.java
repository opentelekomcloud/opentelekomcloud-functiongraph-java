package com.opentelekomcloud.services.functiongraph.runtime.events.cts;

import com.opentelekomcloud.services.functiongraph.runtime.core.TriggerEvent;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
public class CTSTriggerEvent implements TriggerEvent {

  /**
   * CTS message body
   */
  private CTS cts;


}
