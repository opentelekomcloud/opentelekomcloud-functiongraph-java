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

package com.opentelekomcloud.services.runtime;

import java.io.IOException;

public final class JavaRuntime {
  private static final RuntimeLogger logger = new RuntimeLogger() {

    public void log(String string) {
      System.out.print(string);
    }

    @SuppressWarnings("unused")
    public void log(byte[] message) {
      try {
        System.out.write(message);
      } catch (IOException e) {
        // NOTE: When actually running on FunctionGraph, an IOException would never
        // happen
        e.printStackTrace();
      }
    }

    public void debug(String string) {
    }

    public void info(String string) {
    }

    public void warn(String string) {
    }

    public void error(String string) {
    }

    public void setLevel(String level) {
    }
  };

  private JavaRuntime() {
  }

  public static RuntimeLogger getLogger() {
    return logger;
  }
}
