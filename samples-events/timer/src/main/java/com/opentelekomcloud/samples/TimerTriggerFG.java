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

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.opentelekomcloud.services.functiongraph.runtime.events.timer.TimerTriggerEvent;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

import lombok.Data;
import lombok.ToString;

/**
 * TimerTriggerFG is a sample function that demonstrates how to handle timer events.
 * It processes the incoming event and logs the trigger name, type, user event, and time.
 */
public class TimerTriggerFG {
  private final Gson gson = new Gson();

  /**
   * Handles the incoming timer event and logs the trigger name, type, user event, and time.
   * It retrieves the RuntimeLogger from the context and logs the event details.
   *
   * @param event   the timer event data received by the function
   * @param context the runtime context providing access to logging and other services
   * @return a success message
   */
  public String handleRequest(final TimerTriggerEvent event, final Context context) {

    RuntimeLogger log = context.getLogger();

    StringBuilder s = new StringBuilder();
    s.append(String.format("Trigger name: %s\n", event.getTriggerName()));
    s.append(String.format("Trigger type: %s\n", event.getTriggerType()));
    s.append(String.format("User event  : %s\n", event.getUserEvent()));
    s.append(String.format("Time        : %s\n", event.getTime()));

    UserEvent u = gson.fromJson(event.getUserEvent(), UserEvent.class);
    s.append(String.format("UserEvent: %s\n", u.toString()));

    log.log(s.toString());

    return "ok";
  }

  /**
   * UserEvent is a data class representing the user event structure.
   * It contains fields for the message and topic, which are serialized from the JSON string.
   */
  @Data
  @ToString
  private class UserEvent {

    @SerializedName("message")
    String message;
    
    @SerializedName("topic")
    String topic;
  }

}
