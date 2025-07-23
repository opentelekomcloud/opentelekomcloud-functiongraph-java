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

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.util.LoaderUtil;
import org.slf4j.MDC;

import com.google.gson.JsonObject;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

import lombok.extern.slf4j.Slf4j;

/*
 * 
 * Sample on how to use Slf4j logging
 * 
 * FunctionGraph configuration:
 * Handler name: com.opentelekomcloud.samples.FGLoggingSLF4j.handleRequest
 * 
 * Initialization: ON
 * Function Initializer: com.opentelekomcloud.samples.FGLoggingSLF4j.initializer
 * 
 * Class Isolation: ON
 * 
 * Environment Variables
 * - LOG_LEVEL = DEBUG
 * 
 */
@Slf4j
public class FGLoggingSLF4j {
  /**
   * Setup logging in initializer
   */
  public void initializer(Context context) {
    RuntimeLogger rlog = context.getLogger();
    rlog.log("Start initializing...");
    try {
      // put requestId into MDC
      MDC.put("requestid", context.getRequestID());
      // Initialize Log4J
      try {
        Configurator.reconfigure(
            Objects.requireNonNull(LoaderUtil.getThreadContextClassLoader().getResource("log4j2-custom.xml")).toURI());
      } catch (URISyntaxException e) {
        rlog.error("An error occurred while configuring Log4J:" + e.getMessage());
        throw new RuntimeException(e);
      }

      // set log level
      String log_level = context.getUserData("LOG_LEVEL");

      List<String> allowed = Arrays.asList("DEBUG", "INFO", "WARN", "ERROR");
      if (!allowed.contains(log_level)) {
        log_level = "DEBUG";
      }

      Level level = Level.getLevel(log_level);
      Configurator.setRootLevel(level);

    } finally {
      // remove requestId from MDC
      MDC.remove("requestid");
    }

    rlog.log("Finished initializing...");
  }

  /**
   * FunctionGraph Handler
   */
  public String handleRequest(final JsonObject event, final Context context) {
    try {
      // put requestId into MDC
      MDC.put("requestid", context.getRequestID());

      log.debug("debug log");
      log.info("info log");
      log.warn("warn log");
      log.error("error log");

    } finally {
      // remove requestId from MDC
      MDC.remove("requestid");
    }
    return "ok";
  }

}