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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.opentelekomcloud.services.functiongraph.runtime.test.TestContext;

import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

@ExtendWith(SystemStubsExtension.class)
public class SampleFGTest {

  @SystemStub
  private final EnvironmentVariables environment = new EnvironmentVariables("OTC_SDK_AK", "MyAccessKey")//
      .set("OTC_SDK_SK", "MySecretKey") //
      .set("OTC_SDK_PROJECTID", "MyOrojectID") //
      .set("OTC_SDK_SECURITY_TOKEN", "MySecurityToken");

  @Test
  void testHandleRequest() {
    TestContext context = new TestContext();

    SampleFG sampleFG = new SampleFG();

    SampleFG.EventData data = sampleFG.new EventData();
    data.setKey("MyKey");

    String response = sampleFG.handleRequest(data, context);

    assertEquals("ok", response);

  }
}
