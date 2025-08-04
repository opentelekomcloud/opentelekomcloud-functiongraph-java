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
 * Custom Gson TypeAdapter for serializing and deserializing Joda DateTime
 * objects.
 * This adapter formats DateTime objects to a string format used in S3 and
 * parses
 * them back.
 */
public class S3DateTimeAdapter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {

  // pattern used in S3 Event Record
  private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

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

    return new JsonPrimitive(dateTime.toString(formatter));

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
