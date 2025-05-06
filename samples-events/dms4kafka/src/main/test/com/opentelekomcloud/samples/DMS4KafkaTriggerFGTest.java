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
import com.opentelekomcloud.services.functiongraph.runtime.events.dms4kafka.DMS4KafkaTriggerEvent;
import com.opentelekomcloud.services.functiongraph.runtime.test.TestContext;

public class DMS4KafkaTriggerFGTest {
  @Test
  void testEvent() throws Exception {
    Path resourceDirectory = Paths.get("src", "test", "resources");
    String absolutePath = resourceDirectory.toFile().getAbsolutePath();

    try {
      JsonObject eventJson = convertFileToJSON(absolutePath + "/dms4kafka_event.json");

      String eventSting = new Gson().toJson(eventJson);

      DMS4KafkaTriggerEvent event = new Gson().fromJson(eventSting, DMS4KafkaTriggerEvent.class);

      TestContext context = new TestContext();

      DMS4KafkaTriggerFG fg = new DMS4KafkaTriggerFG();

      String ret = fg.handleRequest(event, context);

      assertTrue("ok".equals(ret));

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
