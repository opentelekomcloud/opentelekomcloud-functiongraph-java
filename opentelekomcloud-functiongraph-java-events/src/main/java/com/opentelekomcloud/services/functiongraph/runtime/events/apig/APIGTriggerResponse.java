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
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
public class APIGTriggerResponse {

  /**
   * Body
   */
  @SerializedName("body")
  private String body;

  /**
   * Headers
   */
  @SerializedName("headers")
  private Map<String, String> headers;

  /**
   * http status code
   */
  @SerializedName("statusCode")
  private int statusCode;

  /**
   * Whether the body has been encoded using Base64.
   */
  @SerializedName("isBase64Encoded")
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
    this.body = Base64.getMimeEncoder().encodeToString(body.getBytes(StandardCharsets.UTF_8));
  }

  public void addHeader(String key, String value) {
    if (this.headers == null) {
      this.headers = new HashMap<>();
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
      this.headers = new HashMap<>();
    }

    this.headers.putAll(headersToAdd);
  }

}
