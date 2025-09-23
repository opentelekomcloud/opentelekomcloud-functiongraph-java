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

package com.opentelekomcloud.services.functiongraph.runtime.events.s3obs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;

import com.google.gson.Gson;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Event;

/**
 * S3TriggerEventTest is used to test the S3TriggerEvent class.
 * It reads a JSON file and verifies that the event is correctly parsed.
 */
public class S3TriggerEventTest {

  private static final String TEST_FILE = "s3obs_event.json";

  @ParameterizedTest
  @Event(value = TEST_FILE, type = S3TriggerEvent.class)
  void testEvent(S3TriggerEvent event) {

    assertNotNull(event);
    assertEquals("functionstorage-template", event.getBucketName());
  }

  @ParameterizedTest
  @Event(value = TEST_FILE, type = S3TriggerEvent.class)
  void testDate(S3TriggerEvent event) {

    Gson gson = new Gson();

    String json = gson.toJson(event, S3TriggerEvent.class);

    String expected = "\"eventTime\":\"2018-01-09T07:50:50.028Z\"";

    // "eventTime": "2018-01-09T07:50:50.028Z"
    assertTrue(json.indexOf(expected, 0) > 0);
  }

}
