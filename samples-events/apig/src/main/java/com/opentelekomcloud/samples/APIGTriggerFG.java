/*
 * Copyright (c) 2025 T-Systems International GmbH.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
