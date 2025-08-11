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

import java.util.HashMap;
import java.util.UUID;

import com.google.gson.annotations.SerializedName;
import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;

/**
 * TestContext is a mock implementation of the {@link Context} interface used
 * for testing purposes.
 * It provides default values for various context properties such as request ID,
 * function name, version,
 * memory size, and others. This class can be used in unit tests to simulate the
 * function execution context.
 */
public class TestContext implements Context {

  @SerializedName("requestID")
  private String requestID = UUID.randomUUID().toString();

  @SerializedName("name")
  private String funcName = "TEST_FUNCTION";

  @SerializedName("version")
  private String funcVersion = "latest";

  @SerializedName("package")
  private String funcPackage = "default";

  @SerializedName("instanceID")
  private String funcInstanceID = UUID.randomUUID().toString();

  @SerializedName("alias")
  private String funcAlias = "alias";

  @SerializedName("invokeID")
  private String funcInvokeID = "invokeID";

  @SerializedName("traceID")
  private String funcTraceID = "traceID";

  @SerializedName("invokeProperty")
  private String funcInvokeProperty = "invokeProperty";

  @SerializedName("workflowStateID")
  private String funcWorkflowStateID = UUID.randomUUID().toString();

  @SerializedName("workflowRunID")
  private String funcWorkflowRunID = UUID.randomUUID().toString();

  @SerializedName("workflowID")
  private String funcWorkflowID = UUID.randomUUID().toString();

  @SerializedName("state")
  private Object funcState = "succes";

  @SerializedName("memorySize")
  private int funcMemorySize = 100;

  @SerializedName("cpuNumber")
  private int funcCPUNumber = 1;

  @SerializedName("remainingTimeInMilliSeconds")
  private int funcRemainingTimeInMilliSeconds = 100;

  @SerializedName("runningTimeInSeconds")

  private int funcRunningTimeInSeconds = 100;

  @SerializedName("securityAccessKey")
  private String funcSecurityAccessKey = System.getenv("OTC_SDK_SECURITY_ACCESS_KEY");

  @SerializedName("securitySecretKey")
  private String funcSecuritySecretKey = System.getenv("OTC_SDK_SECURITY_SECRET_KEY");

  @SerializedName("ak")
  private String funcAccessKey = System.getenv("OTC_SDK_AK");

  @SerializedName("sk")
  private String funcSecretKey = System.getenv("OTC_SDK_SK");

  @SerializedName("token")
  private String funcToken = System.getenv("OTC_SDK_TOKEN");

  @SerializedName("securityToken")
  private String funcSecurityToken = System.getenv("OTC_SDK_SECURITY_TOKEN");

  @SerializedName("projectID")
  private String funcProjectID = System.getenv("OTC_SDK_PROJECTID");

  private HashMap<String, String> userData = new HashMap<>();

  /**
   * Get RequestId
   */
  @Override
  public String getRequestID() {
    return requestID;
  }

  public void setRequestID(String value) {
    this.requestID = value;
  }

  /**
   * Get remaining time in milliseconds defaults to 100
   */
  @Override
  public int getRemainingTimeInMilliSeconds() {
    return this.funcRemainingTimeInMilliSeconds;
  }

  public void setRemainingTimeInMilliSeconds(int value) {
    this.funcRemainingTimeInMilliSeconds = value;
  }

  /**
   * Get AccessKey from environment variable "OTC_SDK_AK"
   */
  @Override
  public String getAccessKey() {
    return this.funcAccessKey;
  }

  public void setAccessKey(String value) {
    this.funcAccessKey = value;
  }

  /**
   * Get SecretKey from environment variable "OTC_SDK_SK"
   */
  @Override
  public String getSecretKey() {
    return this.funcSecretKey;
  }

  public void setSecretKey(String value) {
    this.funcSecretKey = value;
  }

  /**
   * returns null
   */
  @Override
  public String getSecurityAccessKey() {
    return this.funcSecurityAccessKey;
  }

  public void setSecurityAccessKey(String value) {
    this.funcSecurityAccessKey = value;
  }

  /**
   * returns null
   */
  @Override
  public String getSecuritySecretKey() {
    return this.funcSecuritySecretKey;
  }

  public void setSecuritySecretKey(String value) {
    this.funcSecuritySecretKey = value;
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

  public void setFunctionName(String funcName) {
    this.funcName = funcName;
  }

  /**
   * Get running time in seconds, defaults to 100
   */
  @Override
  public int getRunningTimeInSeconds() {
    return this.funcRunningTimeInSeconds;
  }

  public void setRunningTimeInSeconds(int funcRunningTimeInSeconds) {
    this.funcRunningTimeInSeconds = funcRunningTimeInSeconds;
  }

  /**
   * Get version, defaults to "latest"
   */
  @Override
  public String getVersion() {
    return this.funcVersion;
  }

  public void setVersion(String funcVersion) {
    this.funcVersion = funcVersion;
  }

  /**
   * Get memory size, defaults to 100
   */
  @Override
  public int getMemorySize() {
    return this.funcMemorySize;
  }

  public void setMemorySize(int funcMemorySize) {
    this.funcMemorySize = funcMemorySize;
  }

  /**
   * Get cpu number, defaults to 1
   */
  @Override
  public int getCPUNumber() {
    return this.funcCPUNumber;
  }

  public void setCPUNumber(int funcCPUNumber) {
    this.funcCPUNumber = funcCPUNumber;
  }

  /**
   * Get projectId from environment variable "OTC_SDK_PROJECTID"
   */
  @Override
  public String getProjectID() {
    return this.funcProjectID;
  }

  public void setProjectID(String funcProjectID) {
    this.funcProjectID = funcProjectID;
  }

  /**
   * Get package name, defaults to "default"
   */
  @Override
  public String getPackage() {
    return this.funcPackage;
  }

  public void setPackage(String funcPackage) {
    this.funcPackage = funcPackage;
  }

  /**
   * Get security token from environment variable "OTC_SDK_SECURITY_TOKEN"
   */
  @Override
  public String getToken() {
    return this.funcToken;
  }

  public void setToken(String funcToken) {
    this.funcToken = funcToken;
  }

  /**
   * Get security token, defaults to "ST"
   */
  @Override
  public String getSecurityToken() {
    return this.funcSecurityToken;
  }

  public void setSecurityToken(String funcSecurityToken) {
    this.funcSecurityToken = funcSecurityToken;
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
    return this.funcInstanceID;
  }

  public void setInstanceID(String funcInstanceID) {
    this.funcInstanceID = funcInstanceID;
  }

  /**
   * Get invoke property, defaults to "InvokeProperty"
   */
  @Override
  public String getInvokeProperty() {
    return this.funcInvokeProperty;
  }

  public void setInvokeProperty(String funcInvokeProperty) {
    this.funcInvokeProperty = funcInvokeProperty;
  }

  /**
   * Get trace id, defaults to "traceid"
   */
  @Override
  public String getTraceID() {
    return this.funcTraceID;
  }

  public void setTraceID(String funcTraceID) {
    this.funcTraceID = funcTraceID;
  }

  /**
   * Get invoke id, defaults to "invokeid"
   */
  @Override
  public String getInvokeID() {
    return this.funcInvokeID;
  }

  public void setInvokeID(String funcInvokeID) {
    this.funcInvokeID = funcInvokeID;
  }

  /**
   * Get alias, defaults to "alias"
   */
  @Override
  public String getAlias() {
    return this.funcAlias;
  }

  public void setAlias(String funcAlias) {
    this.funcAlias = funcAlias;
  }

  /**
   * Get workflow run id alias, defaults to "workflowrunid"
   */
  @Override
  public String getWorkflowRunID() {
    return this.funcWorkflowRunID;
  }

  public void setWorkflowRunID(String funcWorkflowRunID) {
    this.funcWorkflowRunID = funcWorkflowRunID;
  }

  /**
   * Get workflow state id alias, defaults to "workflowstateid"
   */
  @Override
  public String getWorkflowStateID() {
    return this.funcWorkflowStateID;
  }

  public void setWorkflowStateID(String funcWorkflowStateID) {
    this.funcWorkflowStateID = funcWorkflowStateID;
  }

  /**
   * Get workflowid alias, defaults to "workflowid"
   */
  @Override
  public String getWorkflowID() {
    return this.funcWorkflowID;
  }

  public void setWorkflowID(String funcWorkflowID) {
    this.funcWorkflowID = funcWorkflowID;
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

  public TestContext withInvokeID(String funcInvokeID) {
    this.funcInvokeID = funcInvokeID;
    return this;
  }

  public TestContext withTraceID(String funcTraceID) {
    this.funcTraceID = funcTraceID;
    return this;
  }

  public TestContext withInvokeProperty(String funcInvokeProperty) {
    this.funcInvokeProperty = funcInvokeProperty;
    return this;
  }

  public TestContext withWorkflowStateID(String funcWorkflowStateID) {
    this.funcWorkflowStateID = funcWorkflowStateID;
    return this;
  }

  public TestContext withWorkflowRunID(String funcWorkflowRunID) {
    this.funcWorkflowRunID = funcWorkflowRunID;
    return this;
  }

  public TestContext withWorkflowID(String funcWorkflowID) {
    this.funcWorkflowID = funcWorkflowID;
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

  public TestContext withProjectID(String funcProjectID) {
    this.funcProjectID = funcProjectID;
    return this;
  }

  public TestContext withInstanceID(String funcInstanceID) {
    this.funcInstanceID = funcInstanceID;
    return this;
  }

  public TestContext withSecuritySecretKey(String funcSecuritySecretKey) {
    this.funcSecuritySecretKey = funcSecuritySecretKey;
    return this;
  }

  public TestContext withRequestID(String requestID) {
    this.requestID = requestID;
    return this;
  }

  public TestContext withUserData(String key, String value) {
    this.userData.put(key, value);
    return this;
  }

}