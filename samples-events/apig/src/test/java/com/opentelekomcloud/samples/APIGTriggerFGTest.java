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
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Context;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Contexts;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Event;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Events;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.HandlerParams;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Response;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Responses;

public class APIGTriggerFGTest {

  @ParameterizedTest
  @HandlerParams(//
      event = @Event(value = "apig_event.json", type = APIGTriggerEvent.class), //
      response = @Response(value = "apig_response.json", type = APIGTriggerResponseEntity.class), //
      context = @Context("context.json"))
  public void testMultipleEventsResponses1(APIGTriggerEvent event, APIGTriggerResponseEntity response,
      TestContext context) throws Exception {

    System.err.println(context.getRequestID());

  }

  @ParameterizedTest
  @HandlerParams(//
      events = @Events(events = {
          @Event("apig_event.json"),
          @Event("apig_base64_event.json"),
      }, type = APIGTriggerEvent.class), //
      responses = @Responses(responses = {
          @Response("apig_response.json"),
          @Response("apig_base64_response.json")
      }, type = APIGTriggerResponseEntity.class), //
      contexts = @Contexts(contexts = {
          @Context("context.json"),
          @Context("context.json")
      }))
  public void testMultipleEventsResponses(APIGTriggerEvent event, APIGTriggerResponseEntity response,
      TestContext context) throws Exception {

    System.err.println(context.getRequestID());

    assertNotNull(event);
    assertNotNull(response);

    assertEquals(event.getIsBase64Encoded(), response.getIsBase64Encoded());

    // Test the function
    assertNotNull(event.getBody());
    assertNotNull(event.getHeaders());
    assertNotNull(event.getPathParameters());

    // TestContext context = new TestContext();
    APIGTriggerFG fg = new APIGTriggerFG();
    APIGTriggerResponseEntity r = fg.handleRequest(event, context);

    assertNotNull(r);

    assertEquals(r.getBody(), response.getBody());
  }

  @ParameterizedTest
  @HandlerParams(//
      events = @Events(events = {
          @Event("apig_event.json") //
      }, //
          type = APIGTriggerEvent.class), //
      responses = @Responses(responses = {
          @Response("apig_response.json")
      }, //
          type = APIGTriggerResponseEntity.class), //

      context = @Context("context.json"))
  public void testMultipleEventsResponses2(APIGTriggerEvent event, APIGTriggerResponseEntity response,
      TestContext context) throws Exception {

    System.err.println(context.getRequestID());

  }


  @ParameterizedTest
  @HandlerParams(//
      events = @Events(events = {
          @Event("apig_event.json"),
          @Event("apig_base64_event.json"),
      }, type = APIGTriggerEvent.class), //
      responses = @Responses(responses = {
          @Response("apig_response.json"),
          @Response("apig_base64_response.json")
      }, type = APIGTriggerResponseEntity.class), //
     context = @Context("context.json"))
  public void testMultipleEventsResponses4(APIGTriggerEvent event, APIGTriggerResponseEntity response,
      TestContext context) throws Exception {

    System.err.println(context.getRequestID());

    assertNotNull(event);
    assertNotNull(response);

    assertEquals(event.getIsBase64Encoded(), response.getIsBase64Encoded());

    // Test the function
    assertNotNull(event.getBody());
    assertNotNull(event.getHeaders());
    assertNotNull(event.getPathParameters());

    // TestContext context = new TestContext();
    APIGTriggerFG fg = new APIGTriggerFG();
    APIGTriggerResponseEntity r = fg.handleRequest(event, context);

    assertNotNull(r);

    assertEquals(r.getBody(), response.getBody());
  }

}
