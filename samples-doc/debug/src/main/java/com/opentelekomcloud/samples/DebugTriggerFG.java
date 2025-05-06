package com.opentelekomcloud.samples;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.opentelekomcloud.services.functiongraph.runtime.core.RequestHandler;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

public class DebugTriggerFG implements RequestHandler<Object, String> {

  public String handleRequest(Object event, Context context) {

    RuntimeLogger log = context.getLogger();
    log.log("log Hello");
    log.debug("debug hello");
    log.info("info hello");
    log.warn("warn hello");
    log.error("error hello");

    try {
      // Gson gson = new Gson();
      // String json = gson.toJson(context);
      // System.out.println(json);

      File jarFile = new File(
          com.opentelekomcloud.services.runtime.Context.class.getProtectionDomain().getCodeSource().getLocation()
              .toURI().getPath());

      System.out.println(String.format("AbsolutePath: %s", jarFile.getAbsolutePath()));

      Set<String> filesRuntimeLogger = listFilesUsingJavaIO(jarFile.getParentFile().getAbsolutePath());

      for (String s : filesRuntimeLogger) {
        System.out.println(String.format("%s", s));
      }

      Method[] allMethodsContext = Context.class.getDeclaredMethods();
      System.out.println("Methods in Context");
      for (Method method : allMethodsContext) {
        // if (Modifier.isPublic(method.getModifiers())) {
        System.out.println(method);
        // }
      }

      Method[] allMethodsRuntimeLogger = RuntimeLogger.class.getDeclaredMethods();
      System.out.println("Methods in RuntimeLogger");
      for (Method method : allMethodsRuntimeLogger) {
        // if (Modifier.isPublic(method.getModifiers())) {
        System.out.println(method);
        // }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return "ok";
  }

  public Set<String> listFilesUsingJavaIO(String dir) {
    return Stream.of(new File(dir).listFiles())
        .filter(file -> !file.isDirectory())
        .map(File::getName)
        .collect(Collectors.toSet());
  }

}
