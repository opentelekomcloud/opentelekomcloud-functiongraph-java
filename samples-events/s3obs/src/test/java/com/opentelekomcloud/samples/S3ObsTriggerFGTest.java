package com.opentelekomcloud.samples;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;

import com.opentelekomcloud.services.functiongraph.runtime.events.s3obs.S3TriggerEvent;
import com.opentelekomcloud.services.functiongraph.runtime.test.TestContext;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Event;

public class S3ObsTriggerFGTest {

  @ParameterizedTest
  @Event(value = "s3obs_event.json", type = S3TriggerEvent.class)
  void testEvent(S3TriggerEvent event) throws Exception {

    TestContext context = new TestContext();

    S3ObsTriggerFG fg = new S3ObsTriggerFG();

    String ret = fg.handleRequest(event, context);

    assertTrue("ok".equals(ret));

  }

}
