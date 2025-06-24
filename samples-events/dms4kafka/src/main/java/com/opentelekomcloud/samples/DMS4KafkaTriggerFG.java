package com.opentelekomcloud.samples;

import com.opentelekomcloud.services.functiongraph.runtime.events.dms4kafka.DMS4KafkaTriggerEvent;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

public class DMS4KafkaTriggerFG {

  public String handleRequest(final DMS4KafkaTriggerEvent event, final Context context) {
    
    RuntimeLogger log = context.getLogger();

    log.log(String.format("event: %s", event));
    
    return "ok";
  }

}
