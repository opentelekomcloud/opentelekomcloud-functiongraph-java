package com.opentelekomcloud.samples;

import com.opentelekomcloud.services.functiongraph.runtime.core.EventRequestHandler;
import com.opentelekomcloud.services.functiongraph.runtime.events.timer.TimerTriggerEvent;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

public class TimerTriggerFG implements EventRequestHandler<TimerTriggerEvent, String> {

  @Override
  public String handleRequest(TimerTriggerEvent event, Context context) {

    RuntimeLogger log = context.getLogger();

    StringBuffer s = new StringBuffer();
    s.append(String.format("Trigger name: %s\n", event.getTriggerName()));
    s.append(String.format("Trigger type: %s\n", event.getTriggerType()));
    s.append(String.format("User event  : %s\n", event.getUserEvent()));
    s.append(String.format("Time        : %s\n", event.getTime()));

    log.log(s.toString());

    return "ok";
  }

}
