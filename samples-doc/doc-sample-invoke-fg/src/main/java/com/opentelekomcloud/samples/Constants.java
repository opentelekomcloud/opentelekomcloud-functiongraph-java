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

package com.opentelekomcloud.samples;

public class Constants {
  private static Constants instance;

  private String userName;
  private String userPassword;
  private String projectId;
  private String accountName;
  private String region;

  private String authUrl;

  private Constants() {
    userName = System.getenv("OTC_USER_NAME");
    userPassword = System.getenv("OTC_USER_PASSWORD");
    projectId = System.getenv("OTC_SDK_PROJECTID");
    accountName = System.getenv("OTC_DOMAIN_NAME");

    authUrl = System.getenv("OTC_AUTH_URL"); // e.g. https://iam.eu-de.otc.t-systems.com
    if (authUrl == null || authUrl.equals("")){
      authUrl="https://iam.eu-de.otc.t-systems.com";
    }

    region = System.getenv("OTC_TENANT_NAME");

  }

  public static Constants getInstance() {
    if (instance == null) {
      instance = new Constants();
    }
    return instance;
  }

  public String getRegion() {
    return region;
  }

  public String getUserName() {
    return userName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  /**
   * Specifies a subproject ID.
   * This parameter is mandatory only in multi-project scenarios.
   * @return
   */
  public String getProjectId() {
    return projectId;
  }

  /**
   * 
   * @return
   */
  public String getAccountName() {
    return accountName;
  }

  public String getTokenUri() {
    return String.format("%s/%s", authUrl, "v3/auth/tokens?nocatalog=true");
  }

  public String getFunctionGraphHost() {
    return String.format("functiongraph.%s.otc.t-systems.com", region);
  }

  public String getFunctionUrn(String functionName) {
    return String.format("urn:fss:%s:%s:function:default:%s:latest", //
        region, //
        projectId, //
        functionName);
  }

  public String getInvokeUri(String functionName) {
    return String.format("https://%s/v2/%s/fgs/functions/%s/invocations",
        Constants.getInstance().getFunctionGraphHost(), //
        Constants.getInstance().getProjectId(), //
        Constants.getInstance().getFunctionUrn(functionName));
  }

}
