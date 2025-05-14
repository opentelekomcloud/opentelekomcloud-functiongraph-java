package com.opentelekomcloud.samples;

import com.opentelekomcloud.services.functiongraph.runtime.core.EventRequestHandler;
import com.opentelekomcloud.services.functiongraph.runtime.events.smn.SMNRecord;
import com.opentelekomcloud.services.functiongraph.runtime.events.smn.SMNTriggerEvent;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

public class SMNTriggerFG implements EventRequestHandler<SMNTriggerEvent, String> {

  @Override
  public String handleRequest(SMNTriggerEvent event, Context context) {

    RuntimeLogger log = context.getLogger();

    StringBuffer s = new StringBuffer();

    SMNRecord records[] = event.getRecord();
    for (SMNRecord smnRecord : records) {
      s.append(String.format("Subject: %s\n", smnRecord.getSmn().getSubject()));  
    }
   
    
    log.log(s.toString());

    return "ok";
  }

}
