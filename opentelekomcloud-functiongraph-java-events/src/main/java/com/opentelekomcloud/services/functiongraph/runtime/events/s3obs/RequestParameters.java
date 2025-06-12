package com.opentelekomcloud.services.functiongraph.runtime.events.s3obs;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(includeFieldNames=true)
public class RequestParameters {
  /**
   * Source IP address
   */
  @SerializedName("sourceIPAddress")
  private String sourceIPAddress;

  
  public RequestParameters(String sourceIPAddress) {
    this.sourceIPAddress = sourceIPAddress;
  }

}