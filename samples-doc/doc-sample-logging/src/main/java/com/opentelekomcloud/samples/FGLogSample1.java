package com.opentelekomcloud.samples;

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

public class FGLogSample1 {
  Gson gsonPrettyPrint = new GsonBuilder().setPrettyPrinting().create();

  public String handleRequest(final JsonObject event, final Context context) {
    // get RuntimeLogger from context
    RuntimeLogger log = context.getLogger();

    JsonElement jsonElement = JsonParser.parseString(event.toString());

    // log event
    log.log("Event:" + gsonPrettyPrint.toJson(jsonElement));

    return "Success";
  }
}
