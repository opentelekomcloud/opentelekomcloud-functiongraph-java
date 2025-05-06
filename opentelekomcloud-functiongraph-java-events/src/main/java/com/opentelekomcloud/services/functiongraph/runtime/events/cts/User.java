package com.opentelekomcloud.services.functiongraph.runtime.events.cts;

import lombok.Data;
import lombok.ToString;


@Data
@ToString(includeFieldNames=true)
public class User {
  /**
   * Username (multiple sub-users can be created under the same account)
   */
  private String name;

  /**
   * User ID
   */
  private String id;

  /**
   * Account information
   */
  private Domain domain;

}
