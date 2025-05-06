package com.opentelekomcloud.samples;

import java.io.UnsupportedEncodingException;

import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;
import com.opentelekomcloud.services.functiongraph.runtime.core.RequestHandler;
import com.opentelekomcloud.services.functiongraph.runtime.events.lts.LTSTriggerEvent;

public class LTSTriggerFG implements RequestHandler<LTSTriggerEvent, String> {

  public String handleRequest(LTSTriggerEvent event, Context context) {

    RuntimeLogger log = context.getLogger();

    try {
      log.log(String.format("raw data: %s: ", event.getLts().getRawData()));
    } catch (UnsupportedEncodingException ex) {
      return "not_ok";
    }
    return "ok";
  }

}
