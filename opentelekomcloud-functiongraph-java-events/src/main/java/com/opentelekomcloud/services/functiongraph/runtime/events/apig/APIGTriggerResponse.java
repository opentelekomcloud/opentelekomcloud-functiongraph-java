package com.opentelekomcloud.services.functiongraph.runtime.events.apig;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
public class APIGTriggerResponse {

  /**
   * Body
   */
  private String body;

  /**
   * Headers
   */
  private Map<String, String> headers;

  /**
   * http status code
   */
  private int statusCode;

  /**
   * Whether the body has been encoded using Base64.
   */
  private boolean isBase64Encoded;

  public APIGTriggerResponse() {
    this.statusCode = 200;
    this.body = "";
    this.isBase64Encoded = false;
  }

  public APIGTriggerResponse(int statusCode, Map<String, String> headers, String body) {
    this.statusCode = statusCode;
    this.headers = headers;
    this.body = body;
    this.isBase64Encoded = false;
  }

  public APIGTriggerResponse(int statusCode, Map<String, String> headers, boolean isBase64Encoded, String body) {
    this.body = body;
    this.headers = headers;
    this.statusCode = statusCode;
    this.isBase64Encoded = isBase64Encoded;
  }

  public void setBase64EncodedBody(String body) throws UnsupportedEncodingException {
    this.body = Base64.getMimeEncoder().encodeToString(body.getBytes("UTF-8"));
  }

  public void addHeader(String key, String value) {
    if (this.headers == null) {
      this.headers = new HashMap<String, String>();
    }

    this.headers.put(key, value);
  }

  public void removeHeader(String key) {
    if (this.headers != null) {
      this.headers.remove(key);
    }
  }

  public void addHeaders(Map<String, String> headersToAdd) {
    if (this.headers == null) {
      this.headers = new HashMap<String, String>();
    }

    this.headers.putAll(headersToAdd);
  }

}
