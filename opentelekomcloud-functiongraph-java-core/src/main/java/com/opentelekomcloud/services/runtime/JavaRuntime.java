package com.opentelekomcloud.services.runtime;

import java.io.IOException;

public final class JavaRuntime {
  private static volatile RuntimeLogger logger = new RuntimeLogger() {

    public void log(String string) {
      System.out.print(string);
    }

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
