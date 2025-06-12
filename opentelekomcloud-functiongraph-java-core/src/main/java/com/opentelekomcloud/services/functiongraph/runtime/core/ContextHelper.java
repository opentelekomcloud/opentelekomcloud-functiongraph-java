package com.opentelekomcloud.services.functiongraph.runtime.core;

import com.opentelekomcloud.services.runtime.Context;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

/**
 * Helper class to get properties from Context that are currently not implemented.
 */
public class ContextHelper {

  private Context context;

  public ContextHelper(Context context) {
    this.context=context;
  }

  /**
   * Get LogLevel from context.

   * @return current LogLevel
   */
  public String getLogLevel() {
    Gson gson = new Gson();
    String json = gson.toJson(this.context);
    String logLevel = JsonParser.parseString(json).getAsJsonObject().get("logger").getAsJsonObject().get("logLevel")
        .toString();
    return logLevel;

  }

}
