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

package com.opentelekomcloud.services.functiongraph.runtime.events.asyncnotification.utils;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Custom Gson TypeAdapter for serializing and deserializing Instant
 * objects.
 * This adapter formats Instant objects to a string format (UTC) used in
 * AsyncEventNotification and parses them back.
 */
public class TimestampInstantAdapter implements JsonSerializer<Instant>, JsonDeserializer<Instant> {

  // pattern used in AsyncEventNotification
  // "2025-07-28T11:47:56.893440057+02:00"
  private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSxxx";

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

  /**
   * Serializes a instant object to a JSON element.
   *
   * @param instant                  the instant object to serialize
   * @param type                     the type of the object
   * @param jsonSerializationContext the context for serialization
   * @return a JSON element representing the instant
   */
  @Override
  public JsonElement serialize(Instant instant,
      Type type,
      JsonSerializationContext jsonSerializationContext) {

    OffsetDateTime odt = instant.atOffset(ZoneOffset.of("+00:00"));

    String formatted = odt.format(formatter);

    return new JsonPrimitive(formatted);

  }

  /**
   * Deserializes a JSON element to a DateTime object.
   *
   * @param jsonElement                the JSON element to deserialize
   * @param type                       the type of the object
   * @param jsonDeserializationContext the context for deserialization
   * @return a DateTime object
   * @throws JsonParseException if the JSON element cannot be parsed
   */
  @Override
  public Instant deserialize(JsonElement jsonElement,
      Type type,
      JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

    return  OffsetDateTime.parse(jsonElement.getAsString()).toInstant();
    
  }
}
