package com.opentelekomcloud.services.functiongraph.runtime.events.apig;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class APIGRequestContext {
  /**
   * API Id
   */
  @SerializedName("apiId")
  private String apiId;
  /**
   * Request Id
   */
  @SerializedName("requestId")
  private String requestId;
  /**
   * name of the environment in which an API has been published
   */
  @SerializedName("stage")
  private String stage;
  /**
   * Source IP of request
   */
  @SerializedName("sourceIp")
  private String sourceIp;
  
}
