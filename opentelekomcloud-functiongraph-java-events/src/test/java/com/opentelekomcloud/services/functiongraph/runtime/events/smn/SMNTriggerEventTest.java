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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

/**
 * SMNTriggerEventTest is used to test the SMNTriggerEvent class.
 * It reads a JSON file and verifies that the event is correctly parsed.
 */
public class SMNTriggerEventTest {

  @Test
  void testEvent() throws Exception {
    Path resourceDirectory = Paths.get("src", "test", "resources");
    String absolutePath = resourceDirectory.toFile().getAbsolutePath();

    try (Reader reader = new FileReader(absolutePath + "/smn_event.json")) {
      SMNTriggerEvent event = new Gson().fromJson(reader, SMNTriggerEvent.class);
      assertEquals("test", event.getFunctionName());

    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
    assertTrue(true);

  }

}
