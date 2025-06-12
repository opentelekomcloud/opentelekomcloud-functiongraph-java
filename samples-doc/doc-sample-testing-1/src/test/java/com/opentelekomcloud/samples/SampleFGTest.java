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
  private EnvironmentVariables environment = new EnvironmentVariables("OTC_SDK_AK", "MyAccessKey")//
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
