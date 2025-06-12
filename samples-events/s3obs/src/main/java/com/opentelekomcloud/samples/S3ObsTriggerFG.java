package com.opentelekomcloud.samples;

import com.opentelekomcloud.services.functiongraph.runtime.core.EventRequestHandler;
import com.opentelekomcloud.services.functiongraph.runtime.events.s3obs.S3ObsTriggerEvent;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

public class S3ObsTriggerFG implements EventRequestHandler<S3ObsTriggerEvent, String>{

  @Override
  public String handleRequest(S3ObsTriggerEvent event, Context context)  {
    
    RuntimeLogger log = context.getLogger();

    StringBuffer s = new StringBuffer();
    s.append(String.format("BucketName: %s\n", event.getBucketName()));
    s.append(String.format("EventName : %s\n", event.getEventName()));
    
    log.log(s.toString());
   

    return "ok";
  }
}
