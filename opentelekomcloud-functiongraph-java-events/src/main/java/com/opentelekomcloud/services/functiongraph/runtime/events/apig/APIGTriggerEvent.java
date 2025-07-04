/*
 * Copyright (c) 2025 T-Systems International GmbH.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.opentelekomcloud.services.functiongraph.runtime.events.apig;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.opentelekomcloud.services.functiongraph.runtime.core.TriggerEvent;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@NoArgsConstructor
public class APIGTriggerEvent implements TriggerEvent {

  /**
   * Is body of an event encoded using Base64.
   */
  @SerializedName("isBase64Encoded")
  private boolean isBase64Encoded;

  /**
   * HTTP request method
   */
  @SerializedName("httpMethod")
  private String httpMethod;

  /**
   * HTTP request path.
   */
  @SerializedName("path")
  private String path;

  /**
   * HTTP request body
   */
  @SerializedName("body")
  private String body;

  /**
   * HTTP path parameters
   */
  @SerializedName("pathParameters")
  private Map<String, String> pathParameters;

  /**
   * API Gateway configurations
   */
  @SerializedName("requestContext")
  private APIGRequestContext requestContext;

  /**
   * HTTP request headers
   */
  @SerializedName("headers")
  private Map<String, String> headers;

  /**
   * HTTP query parameters
   */
  @SerializedName("queryStringParameters")
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
