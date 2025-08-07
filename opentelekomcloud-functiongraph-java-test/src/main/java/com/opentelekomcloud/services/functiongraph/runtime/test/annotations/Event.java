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

import com.opentelekomcloud.services.functiongraph.runtime.test.EventArgumentsProvider;

/**
 * Event is a custom annotation used to specify a JSON file containing event data for testing purposes.
 * It can be applied to test methods to automatically load the specified event data.
 * The annotation requires the path and file name of the JSON file and the type of the event.
 * 
 * This annotation must be used in conjunction with
 * {@link HandlerParams}.<br/>

 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(EventArgumentsProvider.class)
public @interface Event {

  /**
   * Path and file name of the json event
   * 
   * @return the file name (including the path)
   */
  String value();

  /**
   * Type of the event (for example, one of the <a href=
   * "https://github.com/opentelekomcloud/opentelekomcloud-functiongraph-java/tree/main/opentelekomcloud-functiongraph-java-events">opentelekomcloud-functiongraph-java-events</a>),
   * or your own type
   * 
   * @return the type of the event
   */
  Class<?> type() default Void.class;
}