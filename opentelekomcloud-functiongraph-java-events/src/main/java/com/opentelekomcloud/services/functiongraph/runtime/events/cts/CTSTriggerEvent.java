package com.opentelekomcloud.services.functiongraph.runtime.events.cts;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
public class CTSTriggerEvent {

  /**
   * CTS message body
   */
  private CTS cts;


}
