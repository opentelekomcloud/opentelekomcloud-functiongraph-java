package com.opentelekomcloud.samples;

import com.opentelekomcloud.services.functiongraph.runtime.events.cts.CTSTriggerEvent;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

public class CTSTriggerFG {

  public String handleRequest(final CTSTriggerEvent event, final Context context) {

    RuntimeLogger log = context.getLogger();

    log.log(String.format("event: %s", event));
    
    return "ok";
  }

}
