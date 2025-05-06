package com.opentelekomcloud.samples;

import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;
import com.opentelekomcloud.services.functiongraph.runtime.core.RequestHandler;
import com.opentelekomcloud.services.functiongraph.runtime.events.kafka.KAFKATriggerEvent;

public class KafkaTriggerFG implements RequestHandler<KAFKATriggerEvent, String> {

  public String handleRequest(KAFKATriggerEvent event, Context context) {
    
    RuntimeLogger log = context.getLogger();

    log.log(String.format("event: %s", event));
    
    return "ok";
  }

}
