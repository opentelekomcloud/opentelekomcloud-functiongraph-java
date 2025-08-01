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

import com.opentelekomcloud.services.functiongraph.runtime.events.s3obs.S3TriggerEvent;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

/**
 * S3ObsTriggerFG is a sample function that demonstrates how to handle events from S3 OBS.
 * It processes the incoming event and logs the bucket name and event name.
 */
public class S3ObsTriggerFG {

  /**
   * Handles the incoming S3 OBS event and logs the bucket name and event name.
   * It retrieves the RuntimeLogger from the context and logs the event details.
   *
   * @param event   the S3 OBS event data received by the function
   * @param context the runtime context providing access to logging and other services
   * @return a success message
   */
  public String handleRequest(final S3TriggerEvent event, final Context context)  {
    
    RuntimeLogger log = context.getLogger();

      String s = String.format("BucketName: %s\n", event.getBucketName()) +
              String.format("EventName : %s\n", event.getEventName());
    
    log.log(s);
   

    return "ok";
  }
}
