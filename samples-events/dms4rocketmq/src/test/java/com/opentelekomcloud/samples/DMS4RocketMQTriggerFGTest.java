package com.opentelekomcloud.samples;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;

import com.opentelekomcloud.services.functiongraph.runtime.events.dms4rocketmq.DMS4RocketMQTriggerEvent;
import com.opentelekomcloud.services.functiongraph.runtime.test.TestContext;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Event;

public class DMS4RocketMQTriggerFGTest {

  @ParameterizedTest
  @Event(value = "dms4rocketmq_event.json", type = DMS4RocketMQTriggerEvent.class)
  void testEvent(DMS4RocketMQTriggerEvent event) throws Exception {

    TestContext context = new TestContext();

    DMS4RocketMQTriggerFG fg = new DMS4RocketMQTriggerFG();

    String ret = fg.handleRequest(event, context);

    assertTrue("ok".equals(ret));

  }

}
