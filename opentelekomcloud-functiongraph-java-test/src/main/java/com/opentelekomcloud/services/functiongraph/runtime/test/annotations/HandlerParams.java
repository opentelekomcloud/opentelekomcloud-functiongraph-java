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
package com.opentelekomcloud.services.functiongraph.runtime.test.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.params.provider.ArgumentsSource;

import com.opentelekomcloud.services.functiongraph.runtime.test.HandlerParamsArgumentsProvider;

/**
 * HandlerParams is a custom annotation used to specify parameters for a function handler in tests.
 * It allows specifying an event, response, events, and responses to be used in the test method.
 * This annotation must be used in conjunction with
 * {@link org.junit.jupiter.params.ParameterizedTest}.<br/>
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(HandlerParamsArgumentsProvider.class)
public @interface HandlerParams {

  /**
   * Specifies the event to be used in the test.
   * 
   * @return the {@link Event} annotation containing the file name and type of the event
   */
  Event event() default @Event("");

  Response response() default @Response("");

  Events events() default @Events;

  Responses responses() default @Responses;

  Context context() default @Context("");

  Contexts contexts() default @Contexts;
}
