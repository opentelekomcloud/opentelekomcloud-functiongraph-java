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

import lombok.Data;
import lombok.ToString;

/**
 * EventBody is a simple data class that represents an event body.
 * It contains a name field which can be used to pass data to the function.
 * This class is used in the Spring Boot application to return event body information.
 */
@Data
@ToString(includeFieldNames=true)
public class EventBody {

  @SerializedName("name")
  private String name;
  
}