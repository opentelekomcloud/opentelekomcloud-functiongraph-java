package com.opentelekomcloud.services.functiongraph.runtime.events.asyncnotification;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;

import com.google.gson.Gson;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Event;

public class AsyncEventNotificationTest {

  @ParameterizedTest
  @Event(value = "asyncnotification/asyncnotification.json", type = AsyncEventNotification.class)
  void testDate(AsyncEventNotification event) {

    Gson gson = new Gson();

    String json = gson.toJson(event, AsyncEventNotification.class);

    // as Instant has no timezone, UTZ time will be returned
    String expected = "\"timestamp\":\"2025-07-28T09:47:56.893440057+00:00\"";

    assertTrue(json.indexOf(expected, 0) > 0);
  }
}

