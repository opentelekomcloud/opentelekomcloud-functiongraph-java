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

package com.opentelekomcloud.services.functiongraph.runtime.test;

import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

import java.util.HashMap;
import java.util.UUID;

/**
 * TestContext is a mock implementation of the {@link Context} interface used
 * for testing purposes.
 * It provides default values for various context properties such as request ID,
 * function name, version,
 * memory size, and others. This class can be used in unit tests to simulate the
 * function execution context.
 */
public class TestContext implements Context {

  private String requestId = UUID.randomUUID().toString();
  private String funcName = "TEST_FUNCTION";
  private String funcVersion = "latest";
  private String funcPackage = "default";
  private String funcInstanceId = UUID.randomUUID().toString();
  private String funcAlias = "alias";
  private String funcInvokeId = "invokeid";
  private String funcTraceId = "traceid";
  private String funcInvokeProperty = "invokeproperty";
  private String funcWorkflowStateId = "workflowstateid";
  private String funcWorkflowRunId = "workflowrunid";
  private String funcWorkflowId = "workflowid";
  private Object funcState = "succes";

  private int funcMemorySize = 100;
  private int funcCPUNumber = 1;
  private int funcRemainingTimeInMilliSeconds = 100;

  private String funcAccessKey = System.getenv("OTC_SDK_AK");
  private String funcSecretKey = System.getenv("OTC_SDK_SK");
  private String funcToken = System.getenv("OTC_SDK_TOKEN");
  private String funcSecurityAccessKey = System.getenv("OTC_SDK_SECURITY_ACCESS_KEY");
  private String funcSecuritySecretKey = System.getenv("OTC_SDK_SECURITY_SECRET_KEY");
  private String funcSecurityToken = System.getenv("OTC_SDK_SECURITY_TOKEN");
  private String funcProjectId = System.getenv("OTC_SDK_PROJECTID");

  private HashMap<String, String> userData = new HashMap<>();

  /**
   * Get RequestId
   */
  @Override
  public String getRequestID() {
    return requestId;
  }

  /**
   * Get remaining time in milliseconds defaults to 100
   */
  @Override
  public int getRemainingTimeInMilliSeconds() {
    return this.funcRemainingTimeInMilliSeconds;
  }

  /**
   * Get AccessKey from environment variable "OTC_SDK_AK"
   */
  @Override
  public String getAccessKey() {
    return this.funcAccessKey;
  }

  /**
   * Get SecretKey from environment variable "OTC_SDK_SK"
   */
  @Override
  public String getSecretKey() {
    return this.funcSecretKey;
  }

  /**
   * returns null
   */
  @Override
  public String getSecurityAccessKey() {
    return this.funcSecurityAccessKey;
  }

  /**
   * returns null
   */
  @Override
  public String getSecuritySecretKey() {
    return this.funcSecuritySecretKey;
  }

  /**
   * Get user data by envvarname.
   * If the envvarname is not found in userData, it will return the value from the
   * environment variable with the same name.
   *
   * @param envvarname the name of the environment variable to retrieve
   * @return the value of the environment variable or null if not found
   */
  @Override
  public String getUserData(String envvarname) {

    this.userData.get(envvarname);
    if (this.userData.containsKey(envvarname)) {
      return this.userData.get(envvarname);
    } else {
      return System.getenv(envvarname);
    }
  }

  /**
   * Get function name, defaults to "function-name"
   */
  @Override
  public String getFunctionName() {
    return this.funcName;
  }

  /**
   * Get running time in seconds, defaults to 100
   */
  @Override
  public int getRunningTimeInSeconds() {
    return this.funcRemainingTimeInMilliSeconds / 1000;
  }

  /**
   * Get version, defaults to "latest"
   */
  @Override
  public String getVersion() {
    return this.funcVersion;
  }

  /**
   * Get memory size, defaults to 100
   */
  @Override
  public int getMemorySize() {
    return this.funcMemorySize;
  }

  /**
   * Get cpu number, defaults to 1
   */
  @Override
  public int getCPUNumber() {
    return this.funcCPUNumber;
  }

  /**
   * Get projectId from environment variable "OTC_SDK_PROJECTID"
   */
  @Override
  public String getProjectID() {
    return this.funcProjectId;
  }

  /**
   * Get package name, defaults to "default"
   */
  @Override
  public String getPackage() {
    return this.funcPackage;
  }

  /**
   * Get security token from environment variable "OTC_SDK_SECURITY_TOKEN"
   */
  @Override
  public String getToken() {
    return this.funcToken;
  }

  /**
   * Get security token, defaults to "ST"
   */
  @Override
  public String getSecurityToken() {
    return this.funcSecurityToken;
  }

  /**
   * Get RuntimeLogger to log to sysout
   */
  @Override
  public RuntimeLogger getLogger() {
    return new RuntimeLogger() {

      @Override
      public void log(String message) {
        System.out.println(message);
      }

      @Override
      public void debug(String var1) {
        System.out.println(var1);
      }

      @Override
      public void info(String var1) {
        System.out.println(var1);
      }

      @Override
      public void warn(String var1) {
        System.out.println(var1);
      }

      @Override
      public void error(String var1) {
        System.out.println(var1);
      }

      @Override
      public void setLevel(String var1) {
        System.out.println(var1);
      }

    };
  }

  /**
   * Get state, defaults to "success"
   */
  @Override
  public Object getState() {
    return this.funcState;
  }

  /**
   * Set state
   */
  @Override
  public void setState(Object funcState) {
    this.funcState = funcState;
  }

  /**
   * Get instance id, defaults to "instanceid"
   */
  @Override
  public String getInstanceID() {
    return this.funcInstanceId;
  }

  /**
   * Get invoke property, defaults to "InvokeProperty"
   */
  @Override
  public String getInvokeProperty() {
    return this.funcInvokeProperty;
  }

  /**
   * Get trace id, defaults to "traceid"
   */
  @Override
  public String getTraceID() {
    return this.funcTraceId;
  }

  /**
   * Get invoke id, defaults to "invokeid"
   */
  @Override
  public String getInvokeID() {
    return this.funcInvokeId;
  }

  /**
   * Get alias, defaults to "alias"
   */
  @Override
  public String getAlias() {
    return this.funcAlias;
  }

  /**
   * Get workflow run id alias, defaults to "workflowrunid"
   */
  @Override
  public String getWorkflowRunID() {
    return this.funcWorkflowRunId;
  }

  /**
   * Get workflow state id alias, defaults to "workflowstateid"
   */
  @Override
  public String getWorkflowStateID() {
    return this.funcWorkflowStateId;
  }

  /**
   * Get workflowid alias, defaults to "workflowid"
   */
  @Override
  public String getWorkflowID() {
    return this.funcWorkflowId;
  }

  public void setVersion(String funcVersion) {
    this.funcVersion = funcVersion;
  }

  public void setPackage(String funcPackage) {
    this.funcPackage = funcPackage;
  }

  public void setAlias(String funcAlias) {
    this.funcAlias = funcAlias;
  }

  public void setInvokeId(String funcInvokeId) {
    this.funcInvokeId = funcInvokeId;
  }

  public void setTraceId(String funcTraceId) {
    this.funcTraceId = funcTraceId;
  }

  public void setInvokeProperty(String funcInvokeProperty) {
    this.funcInvokeProperty = funcInvokeProperty;
  }

  public void setWorkflowStateId(String funcWorkflowStateId) {
    this.funcWorkflowStateId = funcWorkflowStateId;
  }

  public void setWorkflowRunId(String funcWorkflowRunId) {
    this.funcWorkflowRunId = funcWorkflowRunId;
  }

  public void setWorkflowId(String funcWorkflowId) {
    this.funcWorkflowId = funcWorkflowId;
  }

  public TestContext withFunctionName(String funcName) {
    this.funcName = funcName;
    return this;
  }

  public TestContext withVersion(String version) {
    this.funcVersion = version;
    return this;
  }

  public TestContext withPackage(String funcPackage) {
    this.funcPackage = funcPackage;
    return this;
  }

  public TestContext withAlias(String funcAlias) {
    this.funcAlias = funcAlias;
    return this;
  }

  public TestContext withInvokeId(String funcInvokeId) {
    this.funcInvokeId = funcInvokeId;
    return this;
  }

  public TestContext withTraceId(String funcTraceId) {
    this.funcTraceId = funcTraceId;
    return this;
  }

  public TestContext withInvokeProperty(String funcInvokeProperty) {
    this.funcInvokeProperty = funcInvokeProperty;
    return this;
  }

  public TestContext withWorkflowStateId(String funcWorkflowStateId) {
    this.funcWorkflowStateId = funcWorkflowStateId;
    return this;
  }

  public TestContext withWorkflowRunId(String funcWorkflowRunId) {
    this.funcWorkflowRunId = funcWorkflowRunId;
    return this;
  }

  public TestContext withWorkflowId(String funcWorkflowId) {
    this.funcWorkflowId = funcWorkflowId;
    return this;
  }

  public TestContext withState(Object funcState) {
    this.funcState = funcState;
    return this;
  }

  public TestContext withSecurityToken(String funcSecurityToken) {
    this.funcSecurityToken = funcSecurityToken;
    return this;
  }

  public TestContext withSecurityAccessKey(String funcSecurityAccessKey) {
    this.funcSecurityAccessKey = funcSecurityAccessKey;
    return this;
  }

  public TestContext withMemorySize(int funcMemorySize) {
    this.funcMemorySize = funcMemorySize;
    return this;
  }

  public TestContext withCPUNumber(int funcCPUNumber) {
    this.funcCPUNumber = funcCPUNumber;
    return this;
  }

  public TestContext withRemainingTimeInMilliSeconds(int funcRemainingTimeInMilliSeconds) {
    this.funcRemainingTimeInMilliSeconds = funcRemainingTimeInMilliSeconds;
    return this;
  }

  public TestContext withAccessKey(String funcAccessKey) {
    this.funcAccessKey = funcAccessKey;
    return this;
  }

  public TestContext withSecretKey(String funcSecretKey) {
    this.funcSecretKey = funcSecretKey;
    return this;
  }

  public TestContext withToken(String funcToken) {
    this.funcToken = funcToken;
    return this;
  }

  public TestContext withProjectId(String funcProjectId) {
    this.funcProjectId = funcProjectId;
    return this;
  }

  public TestContext withInstanceId(String funcInstanceId) {
    this.funcInstanceId = funcInstanceId;
    return this;
  }

  public TestContext withSecuritySecretKey(String funcSecuritySecretKey) {
    this.funcSecuritySecretKey = funcSecuritySecretKey;
    return this;
  }

  public TestContext withRequestId(String requestId) {
    this.requestId = requestId;
    return this;
  }

  public TestContext withUserData(String key, String value) {
    this.userData.put(key, value);
    return this;
  }

}