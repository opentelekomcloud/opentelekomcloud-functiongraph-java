package com.opentelekomcloud.services.functiongraph.runtime.events.timer;

import com.google.gson.annotations.SerializedName;
import com.opentelekomcloud.services.functiongraph.runtime.core.TriggerEvent;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
  private String time;

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
