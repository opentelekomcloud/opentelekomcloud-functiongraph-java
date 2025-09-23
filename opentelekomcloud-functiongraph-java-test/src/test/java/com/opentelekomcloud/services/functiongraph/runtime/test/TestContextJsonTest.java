package com.opentelekomcloud.services.functiongraph.runtime.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestContextJsonTest {

  @Test
  public void testConversion() throws Exception{
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    TestContext c = new TestContext();
    // Serialize the TestContext object to JSON
    String json = gson.toJson(c);
    System.out.println(json);

    // Deserialize the JSON back to a TestContext object
    TestContext c2 = gson.fromJson(json, TestContext.class);

    String json2 = gson.toJson(c2);

    // Verify that the original JSON and the re-serialized JSON are the same
    assertEquals(json, json2);
  }

}
