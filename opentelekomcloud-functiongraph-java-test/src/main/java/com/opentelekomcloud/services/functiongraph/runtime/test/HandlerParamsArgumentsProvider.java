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

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Context;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Contexts;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Event;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Events;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.HandlerParams;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Response;
import com.opentelekomcloud.services.functiongraph.runtime.test.annotations.Responses;

/**
 * HandlerParamsArgumentsProvider is a JUnit 5 ArgumentsProvider that loads
 * events and responses
 * from JSON files specified by the {@link HandlerParams} annotation and
 * provides them as arguments
 * to test methods.
 * It uses the {@link EventLoader} to read the JSON files and convert them into
 * the specified type.
 */
public class HandlerParamsArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<HandlerParams> {

  private Event event;
  private Response response;

  private Events events;
  private Responses responses;

  private Context context;
  private Contexts contexts;

  /**
   * * Accepts the {@link HandlerParams} annotation and sets the event, response,
   * events, and responses
   * to be loaded.
   *
   * @param handlerParams the {@link HandlerParams} annotation containing the
   *                      event, response, events, and responses
   */

  @Override
  public void accept(HandlerParams handlerParams) {
    this.event = handlerParams.event();
    this.response = handlerParams.response();
    this.events = handlerParams.events();
    this.responses = handlerParams.responses();
    this.context = handlerParams.context();
    this.contexts = handlerParams.contexts();
  }

  /**
   * Provides arguments for the test method by loading the event and response
   * from the specified JSON files.
   *
   * @param extContext the context in which the extension is being used
   * @return a stream of arguments containing the loaded event and response
   * @throws Exception if an error occurs while loading the event or response
   */
  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extContext) throws Exception {

    if (!context.value().isEmpty() && contexts.contexts().length > 0) {
      throw new IllegalStateException(
          "You cannot use both Context and Contexts annotations at the same time, please choose one of them.");
    }

    if (event.value().isEmpty() && events.events().length == 0 && StringUtils.isEmpty(events.folder())) {
      throw new IllegalStateException("At least one event should be provided");
    }
    
    if (response.value().isEmpty() && responses.responses().length == 0 && StringUtils.isEmpty(responses.folder())) {
      throw new IllegalStateException("At least one response should be provided");
    }

    if ((!event.value().isEmpty() && response.value().isEmpty()) ||
        (event.value().isEmpty() && !response.value().isEmpty())) {
      throw new IllegalStateException(
          "You must use either Event & Response (singular) or Events & Responses (plural) annotations together, you cannot mix them");
    }

    if (((ArrayUtils.isEmpty(events.events()) && StringUtils.isEmpty(events.folder()))
        && (StringUtils.isNotEmpty(responses.folder()) || ArrayUtils.isNotEmpty(responses.responses())))
        ||
        ((ArrayUtils.isEmpty(responses.responses()) && StringUtils.isEmpty(responses.folder()))
            && (StringUtils.isNotEmpty(events.folder()) || ArrayUtils.isNotEmpty(events.events())))) {
      throw new IllegalStateException(
          "You must use either Event & Response (singular) or Events & Responses (plural) annotations together, you cannot mix them");
    }

    // deal with one element
    if (!event.value().isEmpty() && !response.value().isEmpty()) {
      return Stream.of(
          Arguments.of(
              EventLoader.loadEvent(event.value(), event.type()),
              EventLoader.loadEvent(response.value(), response.type()),
              ContextLoader.loadContext(context.value())));
    }

    // deal with many elements
    List<Object> eventList = getEvents();
    List<Object> responseList = getResponses();

    if (eventList == null || eventList.size() == 0 //
        || responseList == null || responseList.size() == 0 //
        || eventList.size() != responseList.size()) {
      throw new IllegalStateException(
          "At least one event and one response and one context should be provided, and you should have the exact same number of events, responses and contexts.");
    }

    List<Object> contextList = null;
    Object defaultContext = null;

    if (!context.value().isEmpty()) {
      defaultContext = ContextLoader.loadContext(context.value());
    } else {
      contextList = getContexts();
    }

    Stream.Builder<Arguments> streamBuilder = Stream.builder();
    for (int i = 0; i < eventList.size(); i++) {

      streamBuilder.add(Arguments.of(eventList.get(i), responseList.get(i),
          defaultContext == null ? contextList.get(i) : defaultContext));

    }
    return streamBuilder.build();
  }

  private List<Object> getResponses() throws IOException, URISyntaxException {
    List<Object> responseList;
    if (ArrayUtils.isNotEmpty(responses.responses())) {
      responseList = Arrays.stream(responses.responses()).map(
          response -> {
            Class<?> clazz = response.type() == Void.class ? responses.type() : response.type();
            return EventLoader.loadEvent(response.value(), clazz);
          }).collect(Collectors.toList());
    } else {
      Stream<Path> files = listFiles(responses.folder());

      responseList = files
          .filter(Files::isRegularFile)
          .map(path -> EventLoader.loadEvent(path.toString(), responses.type()))
          .collect(Collectors.toList());
    }
    return responseList;
  }

  private List<Object> getEvents() throws IOException, URISyntaxException {
    List<Object> eventList;
    if (ArrayUtils.isNotEmpty(events.events())) {
      eventList = Arrays.stream(events.events()).map(
          event -> {
            Class<?> clazz = event.type() == Void.class ? events.type() : event.type();
            return EventLoader.loadEvent(event.value(), clazz);
          }).collect(Collectors.toList());
    } else {
      Stream<Path> files = listFiles(events.folder());

      eventList = files
          .filter(Files::isRegularFile)
          .map(path -> EventLoader.loadEvent(path.toString(), events.type()))
          .collect(Collectors.toList());
    }
    return eventList;
  }

  private List<Object> getContexts() throws IOException, URISyntaxException {
    List<Object> contextList;

    contextList = Arrays.stream(contexts.contexts()).map(
        context -> {
          return ContextLoader.loadContext(context.value());
        }).collect(Collectors.toList());

    return contextList;
  }

  private Stream<Path> listFiles(String folder) throws IOException, URISyntaxException {
    URL folderUrl = getClass().getResource(folder);
    if (folderUrl == null) {
      folderUrl = getClass().getClassLoader().getResource(folder);
    }
    if (folderUrl == null) {
      throw new IllegalArgumentException("Path " + folder + " cannot be found");
    }
    return Files.list(Paths.get(folderUrl.toURI())).sorted();
  }
}