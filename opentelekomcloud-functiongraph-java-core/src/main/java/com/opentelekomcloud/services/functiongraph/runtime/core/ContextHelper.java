package com.opentelekomcloud.services.functiongraph.runtime.core;

import com.opentelekomcloud.services.runtime.Context;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

public class ContextHelper {

  public static String getLogLevel(Context context) {
    Gson gson = new Gson();
    String json = gson.toJson(context);
    String logLevel = JsonParser.parseString(json).getAsJsonObject().get("logger").getAsJsonObject().get("logLevel")
        .toString();
    return logLevel;

  }

}
