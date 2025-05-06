package com.opentelekomcloud.services.functiongraph.runtime.events.s3obs;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(includeFieldNames=true)
public class S3IdEntity {
  /**
   * Principal Id (e.g. 65b49ad6e14149e0b01abde940685697")
   */
  @SerializedName("PrincipalId")
  private String id;
  
  public S3IdEntity(String id) {
    this.id = id;
  }
  
}