package com.opentelekomcloud.services.functiongraph.runtime.events.dds;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class DDSRecord {

  @SerializedName("event_source")
  private String eventSource;

  @SerializedName("event_name")
  private String eventName;

  @SerializedName("region")
  private String region;

  @SerializedName("event_version")
  private String eventVersion;

  @SerializedName("dds")
  private DDSBody dds;

  @SerializedName("event_source_id")
  private String eventSourceId;
  
}
