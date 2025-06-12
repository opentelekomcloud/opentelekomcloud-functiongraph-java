package com.opentelekomcloud.services.functiongraph.runtime.events.apig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

public class APIGTriggerEventTest {

  @Test
  void testEvent() throws Exception {
    Path resourceDirectory = Paths.get("src", "test", "resources");
    String absolutePath = resourceDirectory.toFile().getAbsolutePath();

    try (Reader reader = new FileReader(absolutePath + "/apig_event.json")) {
      APIGTriggerEvent event = new Gson().fromJson(reader, APIGTriggerEvent.class);
      assertEquals("SGVsbG8gT3BlblRlbGVrb21Xb3JsZCE=", event.getBody());

    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
    assertTrue(true);

  }

}
