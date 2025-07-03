package com.opentelekomcloud.services.functiongraph.runtime.events.cts;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class Domain {

  /**
   * Account Name
   */
  @SerializedName("name")
  private String name;

  /**
   * Account ID
   */
  @SerializedName("id")
  private String id;


}
