package com.opentelekomcloud.samples;

import com.google.gson.JsonObject;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

public class GenericTriggerFG {

  public String handleRequest(final JsonObject event, final Context context)  {
    
    RuntimeLogger log = context.getLogger();

    log.log(String.format("class name: %s", event.getClass().getName()));
    log.log(String.format("toString: %s", event.toString()));

    for(String key : event.keySet()){
      log.log(String.format("key: %s = %s", key, event.get(key)));
    }
    
    return "ok";
  }
}
