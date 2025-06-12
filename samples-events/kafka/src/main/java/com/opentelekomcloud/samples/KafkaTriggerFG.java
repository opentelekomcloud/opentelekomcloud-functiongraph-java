package com.opentelekomcloud.samples;

import com.opentelekomcloud.services.functiongraph.runtime.core.EventRequestHandler;
import com.opentelekomcloud.services.functiongraph.runtime.events.kafka.KAFKATriggerEvent;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

public class KafkaTriggerFG implements EventRequestHandler<KAFKATriggerEvent, String> {

  @Override
  public String handleRequest(KAFKATriggerEvent event, Context context) {
    
    RuntimeLogger log = context.getLogger();

    log.log(String.format("event: %s", event));
    
    return "ok";
  }

}
