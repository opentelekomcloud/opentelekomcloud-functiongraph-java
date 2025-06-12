package com.opentelekomcloud.services.functiongraph.runtime.events.dds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

public class DDSTriggerEventTest {

  @Test
  void testEvent() throws Exception {
    Path resourceDirectory = Paths.get("src", "test", "resources");
    String absolutePath = resourceDirectory.toFile().getAbsolutePath();

    try (Reader reader = new FileReader(absolutePath + "/dds_event.json")) {
      DDSTriggerEvent event = new Gson().fromJson(reader, DDSTriggerEvent.class);
      assertEquals("insert",event.getRecords().get(0).getEventName());

    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
    assertTrue(true);

  }

}
