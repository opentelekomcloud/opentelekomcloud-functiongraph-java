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

import com.opentelekomcloud.services.functiongraph.runtime.test.EventsArgumentsProvider;

/**
 * Events is a custom annotation used to specify a folder containing multiple JSON files with event data for testing purposes.
 * It can be applied to test methods to automatically load all events from the specified folder.
 * The annotation allows specifying the type of the events and can also accept individual events.
 * This annotation must be used in conjunction with
 * {@link HandlerParams}.<br/>
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(EventsArgumentsProvider.class)
public @interface Events {

  /**
   * Folder where to find json files containing events
   * 
   * @return the folder name
   */
  String folder() default "";

  /**
   * Type of the events (for example, one of the <a href=
   * "https://github.com/opentelekomcloud/opentelekomcloud-functiongraph-java/tree/main/opentelekomcloud-functiongraph-java-events"></a>),
   * or your own type
   * 
   * @return the type of the events
   */
  Class<?> type() default Void.class;

  /**
   * Mutually exclusive with folder
   * 
   * @return the array of events
   */
  Event[] events() default {};
}