package com.opentelekomcloud.services.functiongraph.runtime.events.lts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LTSTriggerEventTest {

  @Test
  void testEvent() throws Exception {
    Path resourceDirectory = Paths.get("src", "test", "resources");
    String absolutePath = resourceDirectory.toFile().getAbsolutePath();

    try (Reader reader = new FileReader(absolutePath + "/lts_event.json")) {
      LTSTriggerEvent event = new Gson().fromJson(reader, LTSTriggerEvent.class);

      String jsonData = event.getLts().getRawData();

      JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();

      assertTrue(jsonObject.isJsonObject());
      assertEquals("6280e170bd934f60a4d851cf5ca05129", jsonObject.get("owner").getAsString());

      JsonElement logs = jsonObject.get("logs");

      JsonArray logsObject = JsonParser.parseString(logs.getAsString()).getAsJsonArray();

      JsonObject log0 = logsObject.get(0).getAsJsonObject();

      assertEquals(1, log0.get("line_no").getAsInt());

    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
    assertTrue(true);

  }

}
