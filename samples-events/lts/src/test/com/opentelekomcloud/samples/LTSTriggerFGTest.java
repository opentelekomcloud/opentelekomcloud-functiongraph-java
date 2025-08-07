package com.opentelekomcloud.samples;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opentelekomcloud.services.functiongraph.runtime.events.lts.LTSTriggerEvent;
import com.opentelekomcloud.services.functiongraph.runtime.test.TestContext;

public class LTSTriggerFGTest {

  @ParameterizedTest
  @Event(value = "lts_event.json", type = LTSTriggerEvent.class)
  void testEvent(LTSTriggerEvent event) throws Exception {

    assertNotNull(event);
    TestContext context = new TestContext();

    LTSTriggerFG fg = new LTSTriggerFG();

    String ret = fg.handleRequest(event, context);

    assertTrue("ok".equals(ret));

  }

}
