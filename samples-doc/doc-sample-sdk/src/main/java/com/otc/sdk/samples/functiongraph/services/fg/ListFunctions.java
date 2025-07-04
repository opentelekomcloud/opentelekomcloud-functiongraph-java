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

/*
 * FunctionGraph configuration: 
 * Handler name: 
 *  - com.otc.sdk.samples.functiongraph.services.fg.ListFunctions.handleRequest
 * 
 * Permissions:
 *  - agency with FunctionGraph ReadOnlyAccess access permissions
 * 
 * Environment Variables:
 *  - FG_ENDPOINT_URL ecs endpoint url, default: "https://functiongraph.eu-de.otc.t-systems.com"
 * 
 */
package com.otc.sdk.samples.functiongraph.services.fg;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;
import com.otc.sdk.core.util.Constant;
import com.otc.sdk.core.util.SSLCipherSuiteUtil;
import com.otc.sdk.service.Client;
import com.otc.sdk.service.Request;

public class ListFunctions {
  private static final String DEFAULT_FG_ENDPOINT_URL = "https://functiongraph.eu-de.otc.t-systems.com";

  public String handleRequest(final JsonObject event, final Context context) {

    RuntimeLogger log = context.getLogger();

    String fgEndPointURL = context.getUserData("FG_ENDPOINT_URL");
    if (fgEndPointURL == null || fgEndPointURL.trim().isEmpty()) {
      log.warn(
          String.format("Environment variable FG_ENDPOINT_URL not set. Using default: %s", DEFAULT_FG_ENDPOINT_URL));
          fgEndPointURL=DEFAULT_FG_ENDPOINT_URL;
    }

    Request httpClientRequest = new Request();
    CloseableHttpClient client = null;
    try {

      // Set the request parameters.
      String projectId = context.getProjectID();

      httpClientRequest.setKey(context.getSecurityAccessKey());
      httpClientRequest.setSecret(context.getSecuritySecretKey());
      httpClientRequest.addHeader("X-Security-Token", context.getSecurityToken());

      httpClientRequest.setMethod("GET");

      String url = String.format("%s/v2/%s/fgs/functions", fgEndPointURL, projectId);

      httpClientRequest.setUrl(url);
      httpClientRequest.addHeader("Content-type", "application/json;charset=utf8");

      httpClientRequest.addHeader("X-Project-Id", projectId);

      httpClientRequest.setBody(null);

      // Sign the request.
      HttpRequestBase signedRequest = Client.sign(httpClientRequest, Constant.SIGNATURE_ALGORITHM_SDK_HMAC_SHA256);

      // create httpClient and do not verify ssl certificate
      client = (CloseableHttpClient) SSLCipherSuiteUtil.createHttpClient(Constant.INTERNATIONAL_PROTOCOL);

      HttpResponse response = client.execute(signedRequest);

      int status_code = response.getStatusLine().getStatusCode();

      log.log("Status: " + response.getStatusLine().getStatusCode());

      HttpEntity resEntity = response.getEntity();
      if (resEntity != null) {
        String jsonString = EntityUtils.toString(resEntity, "UTF-8");

        JsonObject obj = JsonParser.parseString(jsonString).getAsJsonObject();

        JsonArray functions = obj.get("functions").getAsJsonArray();

        JsonArray funcNames = new JsonArray();

        for (JsonElement jsonElement : functions) {
          funcNames.add(jsonElement.getAsJsonObject().get("func_name").getAsString());
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", String.valueOf(status_code));
        jsonObject.add("func_names", funcNames);

        return jsonObject.toString();

      } else {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", String.valueOf(status_code));
        return jsonObject.toString();
      }

    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTrace(e));
      
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("status", "error");
      jsonObject.addProperty("error", ExceptionUtils.getMessage(e));

      return jsonObject.toString();

    } finally {
      if (client != null) {
        try {
          client.close();
        } catch (Exception e) {
        }

      }
    }

  }

}