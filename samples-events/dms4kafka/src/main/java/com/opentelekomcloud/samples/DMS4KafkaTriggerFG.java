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

import com.opentelekomcloud.services.functiongraph.runtime.events.dms4kafka.DMS4KafkaTriggerEvent;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

/**
 * DMS4KafkaTriggerFG is a sample function that demonstrates how to handle events from DMS for Kafka.
 * It processes the incoming event and logs the event data.
 */
public class DMS4KafkaTriggerFG {

  /**
   * Handles the incoming DMS for Kafka event and logs the event data.
   * It retrieves the RuntimeLogger from the context and logs the event.
   *
   * @param event   the DMS for Kafka event data received by the function
   * @param context the runtime context providing access to logging and other services
   * @return a success message
   */
  public String handleRequest(final DMS4KafkaTriggerEvent event, final Context context) {
    
    RuntimeLogger log = context.getLogger();

    log.log(String.format("event: %s", event));
    
    return "ok";
  }

}
