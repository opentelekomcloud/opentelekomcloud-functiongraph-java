package com.opentelekomcloud.samples;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;

import com.opentelekomcloud.services.functiongraph.runtime.events.kafka.KAFKATriggerEvent;
import com.opentelekomcloud.services.functiongraph.runtime.test.TestContext;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Event;

public class KafkaTriggerFGTest {

  @ParameterizedTest
  @Event(value = "kafka_event.json", type = KAFKATriggerEvent.class)
  void testEvent(KAFKATriggerEvent event) throws Exception {

    assertNotNull(event);
    TestContext context = new TestContext();

    KafkaTriggerFG fg = new KafkaTriggerFG();

    String ret = fg.handleRequest(event, context);

    assertTrue("ok".equals(ret));
  }

}
