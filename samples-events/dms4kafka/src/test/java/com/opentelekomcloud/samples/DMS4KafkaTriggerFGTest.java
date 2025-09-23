package com.opentelekomcloud.samples;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;

import com.opentelekomcloud.services.functiongraph.runtime.events.dms4kafka.DMS4KafkaTriggerEvent;
import com.opentelekomcloud.services.functiongraph.runtime.test.TestContext;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Event;

public class DMS4KafkaTriggerFGTest {

  @ParameterizedTest
  @Event(value = "dms4kafka_event.json", type = DMS4KafkaTriggerEvent.class)
  void testEvent(DMS4KafkaTriggerEvent event) throws Exception {

    assertNotNull(event);

    TestContext context = new TestContext();
    DMS4KafkaTriggerFG fg = new DMS4KafkaTriggerFG();

    String ret = fg.handleRequest(event, context);

    assertTrue("ok".equals(ret));

  }

}
