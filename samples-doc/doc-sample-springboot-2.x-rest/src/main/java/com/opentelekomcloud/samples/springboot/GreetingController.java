package com.opentelekomcloud.samples.springboot;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.opentelekomcloud.samples.springboot.components.OTCRequestContextLoggingFilter;

@RestController
public class GreetingController {

  private static Logger logger = LoggerFactory.getLogger(GreetingController.class);

  private static final String templateGreeting = "Hello, %s!";
  private static final String templateByeBye = "Bye bye, %s!";
  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

    logger.info("RequestID: {} Name: {}",  OTCRequestContextLoggingFilter.getRequestId(), name);

    return new Greeting(counter.incrementAndGet(), String.format(templateGreeting, name));
  }

  @GetMapping("/byebye")
  public Greeting byebye(@RequestParam(value = "name", defaultValue = "World") String name) {

    logger.info("RequestID: {} Name: {}",  OTCRequestContextLoggingFilter.getRequestId(), name);

    return new Greeting(counter.incrementAndGet(), String.format(templateByeBye, name));
  }
}