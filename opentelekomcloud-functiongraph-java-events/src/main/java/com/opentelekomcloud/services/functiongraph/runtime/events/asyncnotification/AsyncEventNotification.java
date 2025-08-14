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

package com.opentelekomcloud.services.functiongraph.runtime.events.asyncnotification;

import java.time.Instant;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.opentelekomcloud.services.functiongraph.runtime.events.asyncnotification.utils.TimestampInstantAdapter;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString(includeFieldNames = true)
@NoArgsConstructor
public class AsyncEventNotification  {

  @SerializedName("timestamp")
  //"2025-07-28T11:47:56.893440057+02:00"
  @JsonAdapter(TimestampInstantAdapter.class)
  private Instant timestamp;

  @SerializedName("request_context")
  private RequestContext request_context;

  @SerializedName("request_payload")
  private String request_payload;

  @SerializedName("response_context")
  private ResponseContext response_context;

  @SerializedName("response_payload")
  private String response_payload;
  
}