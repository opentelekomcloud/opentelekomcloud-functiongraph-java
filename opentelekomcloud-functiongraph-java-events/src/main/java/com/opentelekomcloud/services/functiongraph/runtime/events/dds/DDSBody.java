package com.opentelekomcloud.services.functiongraph.runtime.events.dds;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class DDSBody {

  @SerializedName("size_bytes")
  private String sizeBytes;

  @SerializedName("token")
  private String token;

  @SerializedName("full_document")
  private String fullDocument;

  @SerializedName("ns")
  private String ns;

  
}
