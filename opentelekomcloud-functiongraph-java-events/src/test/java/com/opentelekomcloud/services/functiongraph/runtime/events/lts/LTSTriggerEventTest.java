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

package com.opentelekomcloud.services.functiongraph.runtime.events.lts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Event;

/**
 * LTSTriggerEventTest is used to test the LTSTriggerEvent class.
 * It reads a JSON file and verifies that the event is correctly parsed.
 */
public class LTSTriggerEventTest {

@ParameterizedTest
  @Event(value = "lts_event.json", type = LTSTriggerEvent.class)
  void testEvent(LTSTriggerEvent event) throws Exception {

      String jsonData = event.getLts().getRawData();

      JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();

      assertTrue(jsonObject.isJsonObject());
      assertEquals("6280e170bd934f60a4d851cf5ca05129", jsonObject.get("owner").getAsString());

      JsonElement logs = jsonObject.get("logs");

      JsonArray logsObject = JsonParser.parseString(logs.getAsString()).getAsJsonArray();

      JsonObject log0 = logsObject.get(0).getAsJsonObject();

      assertEquals(1, log0.get("line_no").getAsInt());
  }

}
