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

package com.opentelekomcloud.samples.springboot;

import lombok.Data;

/**
 * Greeting is a simple data class that represents a greeting message.
 * It contains an ID and the content of the greeting.
 * This class is used in the Spring Boot application to return greeting messages.
 */
@Data
public class Greeting {
  private long id;
  private String content;

  public Greeting(long id, String content){
    this.id=id;
    this.content=content;
  }
}