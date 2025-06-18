package com.opentelekomcloud.samples;

import com.opentelekomcloud.services.functiongraph.runtime.events.dds.DDSRecord;
import com.opentelekomcloud.services.functiongraph.runtime.events.dds.DDSTriggerEvent;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

public class DDSTriggerFG  {

  public String handleRequest(final DDSTriggerEvent event, final Context context) {

    RuntimeLogger log = context.getLogger();

    log.log(String.format("event: %s", event));


      for (DDSRecord record : event.getRecords()) {
        log.log(String.format("event_name %s: ", record.getEventName()));
      }


    return "ok";
  }

}
