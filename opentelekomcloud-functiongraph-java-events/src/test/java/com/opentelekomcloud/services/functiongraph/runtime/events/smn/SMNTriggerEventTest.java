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

package com.opentelekomcloud.services.functiongraph.runtime.events.smn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;

import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Event;

/**
 * SMNTriggerEventTest is used to test the SMNTriggerEvent class.
 * It reads a JSON file and verifies that the event is correctly parsed.
 */
public class SMNTriggerEventTest {

  @ParameterizedTest
  @Event(value = "smn_event.json", type = SMNTriggerEvent.class)
  void testEvent(SMNTriggerEvent event) throws Exception {
    assertEquals("test", event.getFunctionName());
  }

}
