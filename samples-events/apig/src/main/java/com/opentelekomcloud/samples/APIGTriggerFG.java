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
import com.opentelekomcloud.services.functiongraph.runtime.events.apig.APIGTriggerResponseEntity;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

/**
 * APIGTriggerFG is a sample function that demonstrates how to handle API
 * Gateway events.
 * It processes the incoming event, decodes the body if necessary, and returns a
 * response.
 */
public class APIGTriggerFG {

  /**
   * Handles the incoming API Gateway event and returns a response entity.
   * It retrieves the RuntimeLogger from the context and logs the event data.
   *
   * @param event   the API Gateway event data received by the function
   * @param context the runtime context providing access to logging and other
   *                services
   * @return an APIGTriggerResponseEntity containing the response body and headers
   */
  public APIGTriggerResponseEntity handleRequest(final APIGTriggerEvent event, final Context context) {

    RuntimeLogger log = context.getLogger();

    log.log(String.format("event: %s", event));

    String body = "";
    if (event.getIsBase64Encoded())
      body = new String(Base64.getDecoder().decode(event.getBody()), StandardCharsets.UTF_8);
    else
      body = event.getBody();

    Gson gson = new Gson();
    EventBody eventBody = gson.fromJson(body, EventBody.class);

    String responseBody = String.format("Hello %s", eventBody.getName());

    if (event.getIsBase64Encoded()) {
      responseBody = new String(Base64.getEncoder().encode(responseBody.getBytes(StandardCharsets.UTF_8)),
          StandardCharsets.UTF_8);
    } 
    
    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "application/json");
    return new APIGTriggerResponseEntity(200, headers, responseBody);
  }

  /**
   * This method is called to check the health of the function.
   * It returns true to indicate that the function is healthy.
   *
   * @return true indicating the function is healthy
   */
  public boolean heartbeat() {
    return true;
  }

  /**
   * This method is called before the function is stopped.
   * It can be used to perform any necessary cleanup operations.
   */
  public void prestop() {
  }

  /**
   * This method is called to initialize the function.
   * It can be used to set up any necessary resources or configurations.
   *
   * @param context the runtime context providing access to logging and other
   *                services
   */
  public void initializer(Context context) {
  }

}
