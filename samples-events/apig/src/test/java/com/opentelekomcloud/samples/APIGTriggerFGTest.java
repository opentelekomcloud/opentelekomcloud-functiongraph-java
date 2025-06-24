package com.opentelekomcloud.samples;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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

      assertTrue(getValidReturnValue(event).equals(r.getBody()));

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

      assertTrue(getValidReturnValue(event).equals(r.getBody()));

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
