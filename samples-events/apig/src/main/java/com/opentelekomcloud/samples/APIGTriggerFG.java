package com.opentelekomcloud.samples;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.opentelekomcloud.services.functiongraph.runtime.events.apig.APIGTriggerEvent;
import com.opentelekomcloud.services.functiongraph.runtime.events.apig.APIGTriggerResponse;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

public class APIGTriggerFG {
  
  public APIGTriggerResponse handleRequest(final APIGTriggerEvent event, final Context context) {

    RuntimeLogger log = context.getLogger();
    
    log.log(String.format("event: %s", event));

    String body = "";
    if (event.getIsBase64Encoded())
      body = new String(Base64.getDecoder().decode(event.getBody()), StandardCharsets.UTF_8);
    else
      body = event.getBody();

    Gson gson = new Gson();
    EventBody eventBody = gson.fromJson(body, EventBody.class);

    String responseBody = String.format("Hello %s",eventBody.getName());

 
    Map<String, String> headers = new HashMap<String, String>();
    headers.put("Content-Type", "application/json");
    return new APIGTriggerResponse(200, headers, responseBody);
  }

  public boolean heartbeat() {
    return true;
  }

  public void prestop() {}

  public void initializer(Context context) {}

}
