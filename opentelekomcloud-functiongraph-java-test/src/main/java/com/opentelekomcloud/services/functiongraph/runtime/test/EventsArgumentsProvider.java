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

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Events;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/** EventsArgumentsProvider is a JUnit 5 ArgumentsProvider that loads multiple events from JSON files
 * specified by the {@link Events} annotation and provides them as arguments to test methods.
 * It uses the {@link EventLoader} to read the JSON files and convert them into the specified type.
 */
public class EventsArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<Events> {

  private Events events;

  @Override
  public void accept(Events events) {
    this.events = events;
  }

  /** * Provides arguments for the test method by loading events from the specified JSON files or folder.
   *
   * @param context the context in which the extension is being used
   * @return a stream of arguments containing the loaded events
   * @throws Exception if an error occurs while loading the events
   */
  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
    if (ArrayUtils.isNotEmpty(events.events())) {
      return Arrays.stream(events.events())
          .map(event -> {
            Class<?> clazz = event.type() == Void.class ? events.type() : event.type();
            return Arguments.of(EventLoader.loadEvent(event.value(), clazz));
          });
    } else {
      URL folderUrl = getClass().getResource(events.folder());
      if (folderUrl == null) {
        folderUrl = getClass().getClassLoader().getResource(events.folder());
      }
      if (folderUrl == null) {
        throw new IllegalArgumentException("Path " + events.folder() + " cannot be found");
      }
      Stream<Path> files = Files.list(Paths.get(folderUrl.toURI())).sorted();
      return files
          .filter(Files::isRegularFile)
          .map(path -> Arguments.of(EventLoader.loadEvent(path.toString(), events.type())));
    }
  }
}