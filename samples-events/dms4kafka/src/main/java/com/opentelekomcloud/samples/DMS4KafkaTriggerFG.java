package com.opentelekomcloud.samples;

import com.opentelekomcloud.services.functiongraph.runtime.core.EventRequestHandler;
import com.opentelekomcloud.services.functiongraph.runtime.events.dms4kafka.DMS4KafkaTriggerEvent;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

public class DMS4KafkaTriggerFG implements EventRequestHandler<DMS4KafkaTriggerEvent, String> {

  public String handleRequest(DMS4KafkaTriggerEvent event, Context context) {
    
    RuntimeLogger log = context.getLogger();

    log.log(String.format("event: %s", event));
    
    return "ok";
  }

}
