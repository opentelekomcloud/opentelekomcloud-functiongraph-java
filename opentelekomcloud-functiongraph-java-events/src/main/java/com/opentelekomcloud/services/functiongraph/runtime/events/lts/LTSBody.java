package com.opentelekomcloud.services.functiongraph.runtime.events.lts;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
public class LTSBody {

  /**
   * LTS data
   */
  private String data;

  /**
   * 
   * @return get base64 decoded raw data
   * @throws UnsupportedEncodingException
   */
  public String getRawData() throws UnsupportedEncodingException {
    byte[] decoded = Base64.getMimeDecoder().decode(this.data);
    return new String(decoded, "UTF-8");
  }

}
