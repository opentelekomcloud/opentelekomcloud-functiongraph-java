package com.opentelekomcloud.samples;

import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

public class SampleFG {

  public String handleRequest(final SampleFG.EventData event, final Context context)  {
    
    RuntimeLogger log = context.getLogger();

    log.log(String.format("class name: %s", event.getClass().getName()));
    log.log(String.format("key: %s", event.getKey()));
    
    return "ok";
  }


  public class EventData {
    String key;

    public String getKey(){
      return this.key;
    }

    public void setKey(String value){
      this.key=value;
    }

  }
}