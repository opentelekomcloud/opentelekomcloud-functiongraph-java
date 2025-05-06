package com.opentelekomcloud.samples;

import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;
import com.opentelekomcloud.services.functiongraph.runtime.core.RequestHandler;
import com.opentelekomcloud.services.functiongraph.runtime.events.cts.CTSTriggerEvent;

public class CTSTriggerFG implements RequestHandler<CTSTriggerEvent, String> {

  public String handleRequest(CTSTriggerEvent event, Context context) {

    RuntimeLogger log = context.getLogger();

    log.log(String.format("event: %s", event));
    
    return "ok";
  }

}
