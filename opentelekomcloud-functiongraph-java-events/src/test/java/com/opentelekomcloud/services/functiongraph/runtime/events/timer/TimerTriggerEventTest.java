/*
 * Copyright (c) 2025 T-Systems International GmbH.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.opentelekomcloud.services.functiongraph.runtime.events.timer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;

import com.google.gson.Gson;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Event;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Events;


/**
 * TimerTriggerEventTest is used to test the TimerTriggerEvent class.
 */
public class TimerTriggerEventTest {

  @ParameterizedTest
  @Event(value = "timer/timer_event.json", type = TimerTriggerEvent.class)
  void testEvent(TimerTriggerEvent event) {
    assertNotNull(event);
    assertEquals("Timer_001", event.getTriggerName());
  }
  
   @ParameterizedTest
    @Events(
            events = {
                    @Event("timer/timer_event.json"),
                    @Event("timer/timer2_event.json"),
            },
            type = TimerTriggerEvent.class
    )
    public void testInjectEvents(TimerTriggerEvent event) {
        assertNotNull(event);
    }

  @ParameterizedTest
  @Event(value = "timer/timer_event.json", type = TimerTriggerEvent.class)
  void testDate(TimerTriggerEvent event) {

    Gson gson = new Gson();

    String json = gson.toJson(event, TimerTriggerEvent.class);

    // as Instant has no timezone, UTZ time will be returned
    String expected = "\"time\":\"2023-06-01T00:30:00+00:00\"";

    assertTrue(json.indexOf(expected, 0) > 0);
  }

}

