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
 * Example of FuctionGraph on how to stop ECS instances
 * 
 * FunctionGraph configuration:
 * Handler name:
 * -
 * com.otc.sdk.samples.functiongraph.services.ecs.StartECSInstance.handleRequest
 * 
 * Permissions:
 * - agency with ECS User access permissions
 * 
 * Environment variables:
 * - INSTANCE_ID instance id or comma separated list of ECS instance ids
 * - ECS_ENDPOINT_URL ecs endpoint url, e.g. https://ecs.eu-de.otc.t-systems.com
 * 
 * see: https://docs.otc.t-systems.com/elastic-cloud-server/api-ref/apis_recommended/batch_operations/starting_ecss_in_a_batch.html
 */
package com.otc.sdk.samples.functiongraph.services.ecs;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;
import com.otc.sdk.core.util.Constant;
import com.otc.sdk.core.util.SSLCipherSuiteUtil;
import com.otc.sdk.service.Client;
import com.otc.sdk.service.Request;

/**
 * Start ECS Instance(s) example.
 */
public class StartECSInstance {

  private static final String DEFAULT_ECS_ENDPOINT_URL = "https://ecs.eu-de.otc.t-systems.com";

  /**
   * Convert comma separated string of instanceids to json string
   * 
   * @param str comma separated list of instance ids
   * @return string with instanceids in json format
   */
  private String getServerIds(String str) {
    List<String> ids = new ArrayList<>();
    StringTokenizer tokenizer = new StringTokenizer(str, ",");
    while (tokenizer.hasMoreElements()) {
      ids.add(String.format("{\"id\":\"%s\"}", tokenizer.nextToken().trim()));
    }
    return String.join(",", ids);
  }

  public String handleRequest(final JsonObject event, final Context context) {

    RuntimeLogger log = context.getLogger();

    log.setLevel("DEBUG");

    String instanceId = context.getUserData("INSTANCE_ID");
    if (instanceId == null || instanceId.trim().isEmpty()) {
      log.error("Environment variable INSTANCE_ID not set.");
      return "INSTANCE_ID not set";
    }

    String ecsEndPointURL = context.getUserData("ECS_ENDPOINT_URL");
    if (ecsEndPointURL == null || ecsEndPointURL.trim().isEmpty()) {
      log.warn(
          String.format("Environment variable ECS_ENDPOINT_URL not set. Using default: %s", DEFAULT_ECS_ENDPOINT_URL));
      ecsEndPointURL = DEFAULT_ECS_ENDPOINT_URL;
    }

    Request httpClientRequest = new Request();
    CloseableHttpClient client = null;
    try {

      String projectId = context.getProjectID();

      // use temporary credentials from agency
      httpClientRequest.setKey(context.getSecurityAccessKey());
      httpClientRequest.setSecret(context.getSecuritySecretKey());
      httpClientRequest.addHeader("X-Security-Token", context.getSecurityToken());

      httpClientRequest.setMethod("POST");

      String url = String.format("%s/v1/%s/cloudservers/action", ecsEndPointURL, projectId);

      httpClientRequest.setUrl(url);
      httpClientRequest.addHeader("Content-type", "application/json;charset=utf8");

      httpClientRequest.addHeader("X-Project-Id", projectId);

      String body = String.format("{\"os-start\":{\"servers\":[%s]}}", getServerIds(instanceId));

      log.debug(body);

      httpClientRequest.setBody(body);

      // Sign the request.
      HttpRequestBase signedRequest = Client.sign(httpClientRequest, Constant.SIGNATURE_ALGORITHM_SDK_HMAC_SHA256);

      client = (CloseableHttpClient) SSLCipherSuiteUtil.createHttpClient(Constant.INTERNATIONAL_PROTOCOL);

      HttpResponse response = client.execute(signedRequest);

      String status_code = String.valueOf(response.getStatusLine().getStatusCode());

      String jobID = "";

      HttpEntity resEntity = response.getEntity();
      if (resEntity != null) {
        String entityString = EntityUtils.toString(resEntity, "UTF-8");
        JsonObject obj = JsonParser.parseString(entityString).getAsJsonObject();
        jobID = obj.get("job_id").getAsString();
      }

      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("status", status_code);
      jsonObject.addProperty("job_id", jobID);

      return jsonObject.toString();

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