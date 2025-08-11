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

/**
 * ContextArgumentsProvider is a JUnit 5 ArgumentsProvider that loads an event
 * from a JSON file
 * specified by the {@link Context} annotation and provides it as an argument to
 * test methods.
 * 
 */
public class ContextArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<Context> {

  private Context context;

  /**
   * Provides arguments for the test method by loading the event from the
   * specified JSON file.
   *
   * @param extensionContext the context in which the extension is being used
   * @return a stream of arguments containing the loaded event
   */
  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
    Object o;
    if (context == null || context.value().isEmpty()) {
      o = new TestContext();
    } else {
      // Load the context from the specified JSON file
      // The ContextLoader will handle the loading and deserialization
      o = ContextLoader.loadContext(context.value());
    }
    return Stream.of(Arguments.of(o));

  }

  /**
   * Accepts the {@link Context} annotation and sets the context to be loaded.
   *
   * @param context the {@link Context} annotation containing the file name and
   *                type
   */
  @Override
  public void accept(Context context) {
    this.context = context;
  }
}