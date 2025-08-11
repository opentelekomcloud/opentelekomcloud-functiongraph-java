package com.opentelekomcloud.services.functiongraph.runtime.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;

public class TestContextJsonTest {

  @Test
  public void testConversion() {
    Gson gson = new GsonBuilder().setPrettyPrinting().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();

    TestContext c = new TestContext();
    String json = gson.toJson(c);
    System.out.println(json);

    TestContext c2 = gson.fromJson(json, TestContext.class);

    String json2 = gson.toJson(c2);

    assertEquals(json, json2);
  }

}
