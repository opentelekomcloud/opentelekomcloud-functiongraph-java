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

import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opentelekomcloud.services.functiongraph.runtime.events.apig.APIGTriggerEvent;
import com.opentelekomcloud.services.functiongraph.runtime.events.apig.APIGTriggerResponse;
import com.opentelekomcloud.services.functiongraph.runtime.test.TestContext;

import static org.junit.jupiter.api.Assertions.*;

public class APIGTriggerFGTest {
  @Test
  void testApig() throws Exception {
    Path resourceDirectory = Paths.get("src", "test", "resources");
    String absolutePath = resourceDirectory.toFile().getAbsolutePath();

    try {
      JsonObject eventJson = convertFileToJSON(absolutePath + "/apig_event.json");

      String eventSting = new Gson().toJson(eventJson);

      APIGTriggerEvent event = new Gson().fromJson(eventSting, APIGTriggerEvent.class);

      TestContext context = new TestContext();

      APIGTriggerFG fg = new APIGTriggerFG();

      APIGTriggerResponse r = fg.handleRequest(event, context);

        assertEquals(getValidReturnValue(event), r.getBody());

    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  void testApigBase64() throws Exception {
    Path resourceDirectory = Paths.get("src", "test", "resources");
    String absolutePath = resourceDirectory.toFile().getAbsolutePath();

    try {
      JsonObject eventJson = convertFileToJSON(absolutePath + "/apig_event_base64.json");

      String eventString = new Gson().toJson(eventJson);

      APIGTriggerEvent event = new Gson().fromJson(eventString, APIGTriggerEvent.class);
      
      TestContext context = new TestContext();

      APIGTriggerFG fg = new APIGTriggerFG();

      APIGTriggerResponse r = fg.handleRequest(event, context);

        assertEquals(getValidReturnValue(event), r.getBody());

    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  private String getValidReturnValue(APIGTriggerEvent event){
      String body = "";
      if (event.getIsBase64Encoded())
        body = new String(Base64.getDecoder().decode(event.getBody()), StandardCharsets.UTF_8);
      else
        body = event.getBody();

      Gson gson = new Gson();
      EventBody eventBody = gson.fromJson(body, EventBody.class);  
      
      return String.format("Hello %s", eventBody.getName());
  }

  public static JsonObject convertFileToJSON(String fileName) throws Exception {

    JsonObject jsonObject = new JsonObject();

    JsonElement jsonElement = JsonParser.parseReader(new FileReader(fileName));
    jsonObject = jsonElement.getAsJsonObject();

    return jsonObject;
  }

}
