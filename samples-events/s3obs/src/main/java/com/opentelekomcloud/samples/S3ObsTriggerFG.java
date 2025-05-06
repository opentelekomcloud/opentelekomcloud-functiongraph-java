package com.opentelekomcloud.samples;

import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;
import com.opentelekomcloud.services.functiongraph.runtime.core.RequestHandler;
import com.opentelekomcloud.services.functiongraph.runtime.events.s3obs.S3ObsTriggerEvent;

public class S3ObsTriggerFG implements RequestHandler<S3ObsTriggerEvent, String>{

  public String handleRequest(S3ObsTriggerEvent event, Context context)  {
    
    RuntimeLogger log = context.getLogger();

    StringBuffer s = new StringBuffer();
    s.append(String.format("BucketName: %s\n", event.getBucketName()));
    s.append(String.format("EventName : %s\n", event.getEventName()));
    
    log.log(s.toString());
   

    return "ok";
  }
}
