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
package com.opentelekomcloud.services.functiongraph.runtime.test;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Context;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Event;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.HandlerEventContextParams;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.HandlerParams;

/**
 * HandlerParamsArgumentsProvider is a JUnit 5 ArgumentsProvider that loads
 * events and responses
 * from JSON files specified by the {@link HandlerParams} annotation and
 * provides them as arguments
 * to test methods.
 * It uses the {@link EventLoader} to read the JSON files and convert them into
 * the specified type.
 */
public class HandlerEventContextParamsArgumentsProvider
    implements ArgumentsProvider, AnnotationConsumer<HandlerEventContextParams> {

  private Event event;
  private Context context;

  @Override
  public void accept(HandlerEventContextParams handlerParams) {
    this.event = handlerParams.event();
    this.context = handlerParams.context();

  }

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext econtext) throws Exception {

    // deal with one element
    if (!event.value().isEmpty()) {
      return Stream.of(
          Arguments.of(
              EventLoader.loadEvent(event.value(), event.type()),
              ContextLoader.loadContext(context.value())));
    }
    return null;

  }

}