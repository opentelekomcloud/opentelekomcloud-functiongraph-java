package com.opentelekomcloud.samples;

import java.util.HashMap;
import java.util.Map;

import com.opentelekomcloud.services.functiongraph.runtime.core.EventRequestHandler;
import com.opentelekomcloud.services.functiongraph.runtime.events.apig.APIGTriggerEvent;
import com.opentelekomcloud.services.functiongraph.runtime.events.apig.APIGTriggerResponse;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

public class APIGTriggerFG implements EventRequestHandler<APIGTriggerEvent, APIGTriggerResponse> {

  @Override
  public APIGTriggerResponse handleRequest(APIGTriggerEvent event, Context context) {

    RuntimeLogger log = context.getLogger();
    log.log(String.format("event: %s", event));

    Map<String, String> headers = new HashMap<String, String>();
    headers.put("Content-Type", "application/json");
    return new APIGTriggerResponse(200, headers, event.getBody());
  }

}
