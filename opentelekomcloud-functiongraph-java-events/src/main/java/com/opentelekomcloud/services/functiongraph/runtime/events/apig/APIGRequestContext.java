package com.opentelekomcloud.services.functiongraph.runtime.events.apig;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
public class APIGRequestContext {
  /**
   * API Id
   */
  private String apiId;
  /**
   * Request Id
   */
  private String requestId;
  /**
   * name of the environment in which an API has been published
   */
  private String stage;
  /**
   * Source IP of request
   */
  private String sourceIp;
  
}
