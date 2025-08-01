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

import com.google.gson.annotations.SerializedName;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

/**
 * SampleFG is a sample function that demonstrates how to handle requests.
 * It logs the class name and key from the event data.
 */
public class SampleFG {

  public String handleRequest(final SampleFG.EventData event, final Context context)  {
    
    RuntimeLogger log = context.getLogger();

    log.log(String.format("class name: %s", event.getClass().getName()));
    log.log(String.format("key: %s", event.getKey()));
    
    return "ok";
  }


  public class EventData {
    public EventData() {
    }
    
    @SerializedName("key")
    String key;

    public String getKey(){
      return this.key;
    }

    public void setKey(String value){
      this.key=value;
    }

  }
}