package com.opentelekomcloud.samples;

import java.util.UUID;

import org.slf4j.MDC;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Testlogging {
  
  public static void main(String[] args){

    MDC.put("requestid", UUID.randomUUID().toString());
    log.debug("Hello world");

    MDC.remove("requestid");

  }
  
}
