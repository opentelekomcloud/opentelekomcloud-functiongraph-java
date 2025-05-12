package com.opentelekomcloud.samples.springboot;

import lombok.Data;

@Data
public class Greeting {
  private long id;
  private String content;

  public Greeting(long id, String content){
    this.id=id;
    this.content=content;
  }
}