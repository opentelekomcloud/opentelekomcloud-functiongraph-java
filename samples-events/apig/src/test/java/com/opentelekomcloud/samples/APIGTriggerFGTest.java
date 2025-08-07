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

import com.opentelekomcloud.services.functiongraph.runtime.events.apig.APIGTriggerEvent;
import com.opentelekomcloud.services.functiongraph.runtime.events.apig.APIGTriggerResponseEntity;
import com.opentelekomcloud.services.functiongraph.runtime.test.TestContext;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Event;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.HandlerParams;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Response;

public class APIGTriggerFGTest {

  @ParameterizedTest
  @HandlerParams(//
      event = @Event(value = "apig_event.json", type = APIGTriggerEvent.class), //
      response = @Response(value = "apig_response.json", type = APIGTriggerResponseEntity.class))
  void testApig(APIGTriggerEvent event, APIGTriggerResponseEntity response) throws Exception {

    assertNotNull(event);
    assertNotNull(response);

    TestContext context = new TestContext();

    APIGTriggerFG fg = new APIGTriggerFG();
    APIGTriggerResponseEntity r = fg.handleRequest(event, context);

    assertNotNull(r);

    assertEquals(r.getBody(), response.getBody());

  }

  @ParameterizedTest
  @HandlerParams(//
      event = @Event(value = "apig_base64_event.json", type = APIGTriggerEvent.class), //
      response = @Response(value = "apig_base64_response.json", type = APIGTriggerResponseEntity.class))
  void testApigBase64(APIGTriggerEvent event, APIGTriggerResponseEntity response) throws Exception {

    assertNotNull(event);
    assertNotNull(response);
    assertEquals(event.getIsBase64Encoded(), true);

    // Test the function
    assertNotNull(event.getBody());
    assertNotNull(event.getHeaders());
    assertNotNull(event.getPathParameters());

    TestContext context = new TestContext();
    APIGTriggerFG fg = new APIGTriggerFG();
    APIGTriggerResponseEntity r = fg.handleRequest(event, context);

    assertNotNull(r);

    assertEquals(r.getBody(), response.getBody());

  }

}
