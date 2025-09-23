package com.opentelekomcloud.samples;

import java.util.UUID;

import org.apache.logging.log4j.ThreadContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Testlogging {


  
  public static void main(String[] args){

    String uuid = UUID.randomUUID().toString();
    ThreadContext.put("requestid", uuid);

    log.debug("Hello world");

    ThreadContext.remove("requestid");

  }
  


  
}
