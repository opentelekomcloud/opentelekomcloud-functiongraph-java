package com.opentelekomcloud.services.functiongraph.runtime.events.s3obs;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString(includeFieldNames=true)
public class S3Bucket {
  /**
   * Bucket name
   */
  @SerializedName("name")
  private String name;

  /**
   * ARN
   */
  @SerializedName("arn")
  private String arn;

  /**
   * Owner Identity
   */
  @SerializedName("ownerIdentity")
  private S3IdEntity idEntity;

  public S3Bucket(String name, String arn, S3IdEntity idEntity) {
    this.name = name;
    this.arn = arn;
    this.idEntity = idEntity;
  }

}
