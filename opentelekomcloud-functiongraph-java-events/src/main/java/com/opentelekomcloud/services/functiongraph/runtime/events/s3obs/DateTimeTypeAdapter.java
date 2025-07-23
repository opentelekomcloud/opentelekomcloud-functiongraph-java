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
package com.opentelekomcloud.services.functiongraph.runtime.events.s3obs;

import java.lang.reflect.Type;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Custom Gson TypeAdapter for serializing and deserializing Joda DateTime objects.
 * This adapter formats DateTime objects to a specific string format and parses them back.
 */
public class DateTimeTypeAdapter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {

  private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
  /**
   * DateTime format used for serialization and deserialization.
   */
  private static final DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_TIME_PATTERN);

  /**
   * Serializes a DateTime object to a JSON element.
   *
   * @param dateTime                 the DateTime object to serialize
   * @param type                     the type of the object
   * @param jsonSerializationContext the context for serialization
   * @return a JSON element representing the DateTime
   */
  @Override
  public JsonElement serialize(DateTime dateTime,
      Type type,
      JsonSerializationContext jsonSerializationContext) {

    return new JsonPrimitive(dateTime.toString(DATE_TIME_PATTERN));

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
  public DateTime deserialize(JsonElement jsonElement,
      Type type,
      JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

    return new DateTime(formatter.parseDateTime(jsonElement.getAsString()));
  }

}