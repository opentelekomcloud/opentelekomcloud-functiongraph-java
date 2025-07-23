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

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.opentelekomcloud.samples.springboot.components.OTCRequestContextLoggingFilter;

/**
 * GreetingController is a Spring Boot REST controller that handles greeting requests.
 * It provides endpoints to return greeting messages based on the provided name.
 * The controller logs the request ID and name for each request.
 */
@RestController
public class GreetingController {

  private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

  private static final String templateGreeting = "Hello, %s!";
  private static final String templateByeBye = "Bye bye, %s!";
  private final AtomicLong counter = new AtomicLong();

  /**
   * Handles GET requests to the /greeting endpoint.
   * Returns a greeting message with the provided name or "World" if no name is provided.
   *
   * @param name the name to include in the greeting message
   * @return a Greeting object containing the ID and greeting message
   */
  @GetMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

    logger.info("RequestID: {} Name: {}",  OTCRequestContextLoggingFilter.getRequestId(), name);

    return new Greeting(counter.incrementAndGet(), String.format(templateGreeting, name));
  }

  /**
   * Handles GET requests to the /byebye endpoint.
   * Returns a goodbye message with the provided name or "World" if no name is provided.
   *
   * @param name the name to include in the goodbye message
   * @return a Greeting object containing the ID and goodbye message
   */
  @GetMapping("/byebye")
  public Greeting byebye(@RequestParam(value = "name", defaultValue = "World") String name) {

    logger.info("RequestID: {} Name: {}",  OTCRequestContextLoggingFilter.getRequestId(), name);

    return new Greeting(counter.incrementAndGet(), String.format(templateByeBye, name));
  }
}