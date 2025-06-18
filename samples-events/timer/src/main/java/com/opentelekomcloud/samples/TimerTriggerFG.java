package com.opentelekomcloud.samples;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.opentelekomcloud.services.functiongraph.runtime.events.timer.TimerTriggerEvent;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

import lombok.Data;
import lombok.ToString;

public class TimerTriggerFG {
  private Gson gson = new Gson();

  public String handleRequest(final TimerTriggerEvent event, final Context context) {

    RuntimeLogger log = context.getLogger();

    StringBuffer s = new StringBuffer();
    s.append(String.format("Trigger name: %s\n", event.getTriggerName()));
    s.append(String.format("Trigger type: %s\n", event.getTriggerType()));
    s.append(String.format("User event  : %s\n", event.getUserEvent()));
    s.append(String.format("Time        : %s\n", event.getTime()));

    UserEvent u = gson.fromJson(event.getUserEvent(), UserEvent.class);
    s.append(String.format("UserEvent: %s\n", u.toString()));

    log.log(s.toString());

    return "ok";
  }

  @Data
  @ToString
  private class UserEvent {

    @SerializedName("message")
    String message;
    
    @SerializedName("topic")
    String topic;
  }

}
