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

import com.google.gson.JsonObject;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;
import com.opentelekomcloud.services.functiongraph.runtime.core.ContextHelper;

/**
 * FGLogSample2 is a sample function that demonstrates how to log messages
 * at different log levels using RuntimeLogger.
 * It retrieves the current log level and sets it to "INFO".
 */
public class FGLogSample2 {

  public String handleRequest(final JsonObject event, final Context context) {
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
