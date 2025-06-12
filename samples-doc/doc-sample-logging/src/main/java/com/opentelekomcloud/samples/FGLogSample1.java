package com.opentelekomcloud.samples;

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;
import com.opentelekomcloud.services.functiongraph.runtime.core.RequestHandler;

public class FGLogSample1 implements RequestHandler<JsonObject, String> {
  Gson gsonPrettyPrint = new GsonBuilder().setPrettyPrinting().create();

  @Override
  public String handleRequest(JsonObject event, Context context) {
    // get RuntimeLogger from context
    RuntimeLogger log = context.getLogger();

    JsonElement jsonElement = JsonParser.parseString(event.toString());

    // log event
    log.log("Event:" + gsonPrettyPrint.toJson(jsonElement));

    return "Success";
  }
}
