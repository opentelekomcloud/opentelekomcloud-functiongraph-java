package com.opentelekomcloud.samples;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

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

      assertTrue("SGVsbG8gT3BlblRlbGVrb21Xb3JsZCE=".equals(r.getBody()));

    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  public static JsonObject convertFileToJSON(String fileName) throws Exception {

    JsonObject jsonObject = new JsonObject();

    JsonElement jsonElement = JsonParser.parseReader(new FileReader(fileName));
    jsonObject = jsonElement.getAsJsonObject();

    return jsonObject;
  }

}
