package com.opentelekomcloud.services.functiongraph.runtime.events.lts;


import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
public class LTSTriggerEvent {

  /**
   * LTS message body
   */
  private LTSBody lts;
  
}
