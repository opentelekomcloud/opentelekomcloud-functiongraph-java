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

package com.opentelekomcloud.samples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.params.ParameterizedTest;

import com.opentelekomcloud.services.functiongraph.runtime.events.dds.DDSTriggerEvent;
import com.opentelekomcloud.services.functiongraph.runtime.test.TestContext;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Event;

public class DDSTriggerFGTest {

  @ParameterizedTest
  @Event(value = "dds_event.json", type = DDSTriggerEvent.class)
  void testEvent(DDSTriggerEvent event) throws Exception {

    assertNotNull(event);
    TestContext context = new TestContext();
    DDSTriggerFG fg = new DDSTriggerFG();

    String ret = fg.handleRequest(event, context);

    assertEquals("ok", ret);

  }

}
