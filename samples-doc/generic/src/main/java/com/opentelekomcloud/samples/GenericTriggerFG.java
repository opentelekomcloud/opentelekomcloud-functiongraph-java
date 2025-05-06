package com.opentelekomcloud.samples;

import com.google.gson.JsonObject;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;
import com.opentelekomcloud.services.functiongraph.runtime.core.RequestHandler;

public class GenericTriggerFG implements RequestHandler<JsonObject, String>{

  public String handleRequest(JsonObject event, Context context)  {
    
    RuntimeLogger log = context.getLogger();

    log.log(String.format("class name: %s", event.getClass().getName()));
    log.log(String.format("toString: %s", event.toString()));

    for(String key : event.keySet()){
      log.log(String.format("key: %s = %s", key, event.get(key)));
    }
    
    return "ok";
  }
}
