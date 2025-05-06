package com.opentelekomcloud.samples;

import java.util.HashMap;
import java.util.Map;

import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;
import com.opentelekomcloud.services.functiongraph.runtime.core.RequestHandler;
import com.opentelekomcloud.services.functiongraph.runtime.events.apig.APIGTriggerEvent;
import com.opentelekomcloud.services.functiongraph.runtime.events.apig.APIGTriggerResponse;

public class APIGTriggerFG implements RequestHandler<APIGTriggerEvent, APIGTriggerResponse> {

  public APIGTriggerResponse handleRequest(APIGTriggerEvent event, Context context) {

    RuntimeLogger log = context.getLogger();
    log.log(String.format("event: %s", event));

    Map<String, String> headers = new HashMap<String, String>();
    headers.put("Content-Type", "application/json");
    return new APIGTriggerResponse(200, headers, event.getBody());
  }

}
