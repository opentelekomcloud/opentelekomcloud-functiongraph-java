package com.opentelekomcloud.services.functiongraph.runtime.events.smn;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class SMNRecord {
  /**
   * Event version
   */
  @SerializedName("event_version")
  private String eventVersion;

  /**
   * Event subscription URN
   */
  @SerializedName("event_subscription_urn")
  private String eventSubscriptionUrn;

  /**
   * Event source
   */
  @SerializedName("event_source")
  private String eventSource;

  /**
   * SMN body
   */
  @SerializedName("smn")
  private SMNBody smn;

}
