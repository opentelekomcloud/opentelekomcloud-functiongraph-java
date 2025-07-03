package com.opentelekomcloud.services.functiongraph.runtime.events.cts;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class User {
  /**
   * Username (multiple sub-users can be created under the same account)
   */
  @SerializedName("name")
  private String name;

  /**
   * User ID
   */
  @SerializedName("id")
  private String id;

  /**
   * Account information
   */
  @SerializedName("domain")
  private Domain domain;

}
