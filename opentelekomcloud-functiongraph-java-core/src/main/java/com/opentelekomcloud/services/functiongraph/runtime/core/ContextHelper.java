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

package com.opentelekomcloud.services.functiongraph.runtime.core;

import com.opentelekomcloud.services.runtime.Context;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

/**
 * Helper class to get properties from Context that are currently not implemented.
 */
public class ContextHelper {

  private final Context context;

  /**
   * Constructor for ContextHelper.
   *
   * @param context the context to be used
   */
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
