package com.opentelekomcloud.services.functiongraph.runtime.events.s3obs;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString(includeFieldNames=true)
public class S3Body {
  /**
   * S3 schema version
   */
  @SerializedName("s3SchemaVersion")
  private String version;

  /**
   * Configuration ID
   */
  @SerializedName("configurationId")
  private String configurationId;

  /**
   * Bucket
   */
  @SerializedName("bucket")
  private S3Bucket bucket;

  /**
   * OBS Object
   */  
  @SerializedName("object")
  private S3Object obsobject;

  
  public S3Body(String version, String configurationId, S3Bucket bucket,
      S3Object obsobject) {
    this.version = version;
    this.configurationId = configurationId;
    this.bucket = bucket;
    this.obsobject = obsobject;
  }

}