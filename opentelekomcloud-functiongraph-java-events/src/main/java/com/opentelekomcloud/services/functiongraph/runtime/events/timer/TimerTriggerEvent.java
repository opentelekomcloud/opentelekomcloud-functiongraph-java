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

package com.opentelekomcloud.services.functiongraph.runtime.events.timer;

import java.time.Instant;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.opentelekomcloud.services.functiongraph.runtime.core.TriggerEvent;
import com.opentelekomcloud.services.functiongraph.runtime.events.timer.utils.TimeInstantAdapter;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * TimerTriggerEvent is used to represent the event triggered by a timer.
 * It contains information such as version, time, trigger name, trigger type, and user event.
 */
@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class TimerTriggerEvent implements TriggerEvent {

  /**
   * Version. (Currently, the version is v1.0.)
   */
  @SerializedName("version")
  private String version;

  /**
   * Current time.
   */
  @SerializedName("time")
  @JsonAdapter(TimeInstantAdapter.class)
  private Instant time;

  /**
   * trigger name
   */
  @SerializedName("trigger_name")
  private String triggerName;

  /**
   * trigger type
   */
  @SerializedName("trigger_type")
  private String triggerType;

  /**
   * Additional information about the trigger.
   */
  @SerializedName("user_event")
  private String userEvent;

}
