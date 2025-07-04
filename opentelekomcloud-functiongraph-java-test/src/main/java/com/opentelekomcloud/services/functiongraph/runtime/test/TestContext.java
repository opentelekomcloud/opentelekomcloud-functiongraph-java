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
import java.util.UUID;

/**
 * TestContext useable in JUnit Test
 */
public class TestContext implements Context {

  private final String requestId = UUID.randomUUID().toString();

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
    return 100;
  }

  /**
   * Get AccessKey from environment variable "OTC_SDK_AK"
   */
  @Override
  public String getAccessKey() {
    return System.getenv("OTC_SDK_AK");
  }

  /**
   * Get SecretKey from environment variable "OTC_SDK_SK"
   */
  @Override
  public String getSecretKey() {
    return System.getenv("OTC_SDK_SK");
  }

  /**
   * returns null
   */
  @Override
  public String getSecurityAccessKey() {
    return null;
  }

  /**
   * returns null
   */
  @Override
  public String getSecuritySecretKey() {
    return null;
  }

  /**
   * Get UserData from environment variable with name envvarname
   */
  @Override
  public String getUserData(String envvarname) {
    return System.getenv(envvarname);
  }

  /**
   * Get function name, defaults to "function-name"
   */
  @Override
  public String getFunctionName() {
    return "function-name";
  }

  /**
   * Get running time in seconds, defaults to 100
   */
  @Override
  public int getRunningTimeInSeconds() {
    return 100;
  }

  /**
   * Get version, defaults to "latest"
   */
  @Override
  public String getVersion() {
    return "latest";
  }

  /**
   * Get memory size, defaults to 100
   */
  @Override
  public int getMemorySize() {
    return 100;
  }

  /**
   * Get cpu number, defaults to 1
   */
  @Override
  public int getCPUNumber() {
    return 1;
  }

  /**
   * Get projectId from environment variable "OTC_SDK_PROJECTID"
   */
  @Override
  public String getProjectID() {
    return System.getenv("OTC_SDK_PROJECTID");
  }

  /**
   * Get package name, defaults to "default"
   */
  @Override
  public String getPackage() {
    return "default";
  }

  /**
   * Get security token from environment variable "OTC_SDK_SECURITY_TOKEN"
   */
  @Override
  public String getToken() {
    return System.getenv("OTC_SDK_SECURITY_TOKEN");
  }

  /**
   * Get security token, defaults to "ST"
   */
  @Override
  public String getSecurityToken() {
    return "ST";
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
    return "succes";
  }

  /**
   * Set state, does nothing
   */
  @Override
  public void setState(Object state) {
    // nop
  }

  /**
   * Get instance id, defaults to "instanceid"
   */
  @Override
  public String getInstanceID() {
    return "instanceid";
  }

  /**
   * Get invoke property, defaults to "InvokeProperty"
   */
  @Override
  public String getInvokeProperty() {
    return "InvokeProperty";
  }

  /**
   * Get trace id, defaults to "traceid"
   */
  @Override
  public String getTraceID() {
    return "traceid";
  }

  /**
   * Get invoke id, defaults to "invokeid"
   */
  @Override
  public String getInvokeID() {
    return "invokeid";
  }

  /**
   * Get alias, defaults to "alias"
   */
  @Override
  public String getAlias() {
    return "alias";
  }

  /**
   * Get workflow run id alias, defaults to "workflowrunid"
   */
  @Override
  public String getWorkflowRunID() {
    return "workflowrunid";
  }

  /**
   * Get workflow state id alias, defaults to "workflowstateid"
   */
  @Override
  public String getWorkflowStateID() {
    return "workflowstateid";
  }

  /**
   * Get workflowid alias, defaults to "workflowid"
   */
  @Override
  public String getWorkflowID() {
    return "workflowid";
  }

};