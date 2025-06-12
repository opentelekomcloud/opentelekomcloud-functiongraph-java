package com.opentelekomcloud.services.functiongraph.runtime.events.apig;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.opentelekomcloud.services.functiongraph.runtime.core.TriggerEvent;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
public class APIGTriggerEvent implements TriggerEvent {

  /**
   * Is body of an event encoded using Base64.
   */
  private boolean isBase64Encoded;

  /**
   * HTTP request method
   */
  private String httpMethod;

  /**
   * HTTP request path.
   */
  private String path;

  /**
   * HTTP request body
   */
  private String body;

  /**
   * HTTP path parameters
   */
  private Map<String, String> pathParameters;

  /**
   * API Gateway configurations
   */
  private APIGRequestContext requestContext;

  /**
   * HTTP request headers
   */
  private Map<String, String> headers;

  /**
   * HTTP query parameters
   */
  private Map<String, Object> queryStringParameters;

  /**
   * User data set in the APIG custom authorizer.
   */
  @SerializedName("user_data")
  private String userData;

  /**
   * @return obtain the body decoded using Base64
   * @throws UnsupportedEncodingException
   */
  public String getRawBody() throws UnsupportedEncodingException {
    byte[] decoded = Base64.getMimeDecoder().decode(this.body);
    return new String(decoded, "UTF-8");
  }

}
