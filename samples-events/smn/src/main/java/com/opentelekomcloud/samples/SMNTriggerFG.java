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

import com.opentelekomcloud.services.functiongraph.runtime.events.smn.SMNRecordEntity;
import com.opentelekomcloud.services.functiongraph.runtime.events.smn.SMNTriggerEvent;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

/**
 * SMNTriggerFG is a sample function that demonstrates how to handle events from SMN (Simple Message Notification).
 * It processes the incoming event and logs the subject of each SMN record.
 */
public class SMNTriggerFG {

  /**
   * Handles the incoming SMN event and logs the subject of each SMN record.
   * It retrieves the RuntimeLogger from the context and logs the event data.
   *
   * @param event   the SMN event data received by the function
   * @param context the runtime context providing access to logging and other services
   * @return a success message
   */
  public String handleRequest(final SMNTriggerEvent event, final Context context) {

    RuntimeLogger log = context.getLogger();

    StringBuilder s = new StringBuilder();

    SMNRecordEntity[] records = event.getRecord();
    for (SMNRecordEntity smnRecord : records) {
      s.append(String.format("Subject: %s\n", smnRecord.getSmn().getSubject()));  
    }
       
    log.log(s.toString());

    return "ok";
  }

}
