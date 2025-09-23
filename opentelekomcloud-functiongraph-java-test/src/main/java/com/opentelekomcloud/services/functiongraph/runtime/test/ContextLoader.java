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

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;

/**
 * * ContextLoader is a utility class that provides methods to load context from
 * JSON files.
 * It reads a JSON file specified by its filename, deserializes it into an
 * instance of {@link TestContext},
 */
public class ContextLoader {

  /**
   * * Loads an Context from a JSON file.
   *
   * @param filename the name of the JSON file to load
   * @return an instance of the specified type containing the data from the JSON
   *         file
   * @throws IllegalArgumentException if the filename does not end with ".json"
   */
  public static TestContext loadContext(String filename) {
    if (filename == null || filename.isEmpty()) {
      return new TestContext();
    }

    if (!filename.endsWith("json")) {
      throw new IllegalArgumentException("File " + filename + " must have json extension");
    }

    Path resourceDirectory = Paths.get("src", "test", "resources");
    String absolutePath = resourceDirectory.toFile().getAbsolutePath();

    try (Reader reader = new FileReader(absolutePath + "/" + filename)) {
      return new Gson().fromJson(reader, TestContext.class);

    } catch (Exception e) {
      throw new EventLoadingException("Failed to load context from file: " + filename, e);
    }

  }
}
