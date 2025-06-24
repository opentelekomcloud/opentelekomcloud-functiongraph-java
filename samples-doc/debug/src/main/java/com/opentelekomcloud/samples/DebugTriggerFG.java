package com.opentelekomcloud.samples;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

public class DebugTriggerFG {

  public String handleRequest(final JsonObject event, final Context context) {

    // Show RuntimeLogger
    RuntimeLogger log = context.getLogger();
    log.log("log Hello");
    log.debug("debug hello");
    log.info("info hello");
    log.warn("warn hello");
    log.error("error hello");

    try {
      // Show conext data
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String json = gson.toJson(context);
      log.log(json);

      // Show files 
      File jarFile = new File(
          com.opentelekomcloud.services.runtime.Context.class.getProtectionDomain().getCodeSource().getLocation()
              .toURI().getPath());

      log.log(String.format("AbsolutePath: %s", jarFile.getAbsolutePath()));

      Set<String> files = listFilesUsingJavaIO(jarFile.getParentFile().getAbsolutePath());

      for (String s : files) {
        log.log(String.format("%s", s));
      }

      // show public methods on Context
      printMethods(log,Context.class);

      // show public methods on RuntimLogger
      printMethods(log, RuntimeLogger.class);
      

    } catch (Exception e) {
      e.printStackTrace();
    }

    return "ok";
  }

  private <T> void printMethods(RuntimeLogger log, Class<T> clazz) {
    Method[] allMethodsRuntimeLogger = clazz.getDeclaredMethods();
    log.log(String.format("Methods in class: %s", clazz.getName()));
    for (Method method : allMethodsRuntimeLogger) {
      if (Modifier.isPublic(method.getModifiers())) {
        log.log(method.toString());
      }
    }
  }

  private Set<String> listFilesUsingJavaIO(String dir) {
    return Stream.of(new File(dir).listFiles())
        .filter(file -> !file.isDirectory())
        .map(File::getName)
        .collect(Collectors.toSet());
  }

}
