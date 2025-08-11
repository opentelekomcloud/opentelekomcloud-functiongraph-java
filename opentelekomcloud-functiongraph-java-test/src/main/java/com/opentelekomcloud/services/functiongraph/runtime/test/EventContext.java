package com.opentelekomcloud.services.functiongraph.runtime.test;

import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Event;

public class EventContext {

  private final Event event;
  private final TestContext testContext;

  public EventContext(Event event, TestContext testContext) {
    this.event = event;
    this.testContext = testContext;
  }

  public Event getEvent() {
    return event;
  }

  public TestContext getTestContext() {
    return testContext;
  }
}
