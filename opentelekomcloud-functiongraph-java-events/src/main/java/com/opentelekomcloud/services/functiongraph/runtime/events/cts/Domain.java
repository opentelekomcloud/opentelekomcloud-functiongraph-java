package com.opentelekomcloud.services.functiongraph.runtime.events.cts;

import lombok.Data;
import lombok.ToString;


@Data
@ToString(includeFieldNames=true)
public class Domain {

  /**
   * Account Name
   */
  private String name;

  /**
   * Account ID
   */
  private String id;


}
