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

package com.opentelekomcloud.services.functiongraph.runtime.events.lts;


import com.google.gson.annotations.SerializedName;
import com.opentelekomcloud.services.functiongraph.runtime.core.TriggerEvent;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * LTSTriggerEvent is used to represent the event triggered by Log Tank Service (LTS).
 * It contains the LTS message body.
 */
@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class LTSTriggerEvent implements TriggerEvent {

  /**
   * LTS message body
   */
  @SerializedName("lts")
  private LTSBodyEntity lts;
  
}
