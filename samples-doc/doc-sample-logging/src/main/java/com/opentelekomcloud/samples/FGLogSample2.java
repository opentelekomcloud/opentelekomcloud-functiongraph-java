package com.opentelekomcloud.samples;

import com.google.gson.JsonObject;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;
import com.opentelekomcloud.services.functiongraph.runtime.core.ContextHelper;
import com.opentelekomcloud.services.functiongraph.runtime.core.RequestHandler;

public class FGLogSample2 implements RequestHandler<JsonObject, String> {

  @Override
  public String handleRequest(JsonObject event, Context context) {
    // get RuntimeLogger from context
    RuntimeLogger log = context.getLogger();

    // get current LogLevel from context
    ContextHelper ctxHelper = new ContextHelper(context);
    
    String logLevel = ctxHelper.getLogLevel();

    log.log("Current LogLevel:" + logLevel);

    // set Log Level to "INFO"
    log.setLevel("INFO");

    log.debug("Debug"); // will not be logged
    log.info("Info"); // will be logged
    log.warn("Warning"); // will be logged
    log.error("Error"); // will be logged

    return "Success";
  }
}
