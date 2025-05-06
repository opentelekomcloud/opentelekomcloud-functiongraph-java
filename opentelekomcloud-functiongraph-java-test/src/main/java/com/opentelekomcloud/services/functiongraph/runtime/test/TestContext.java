package com.opentelekomcloud.services.functiongraph.runtime.test;

import com.opentelekomcloud.services.runtime.Context;
import com.opentelekomcloud.services.runtime.RuntimeLogger;
import java.util.UUID;

public class TestContext implements Context {

  private final String requestId=UUID.randomUUID().toString();

  @Override
  public String getRequestID() {
    return requestId;
  }

  @Override
  public int getRemainingTimeInMilliSeconds() {
    return 100;

  }

  @Override
  public String getAccessKey() {
    return System.getenv("OTC_SDK_AK");
  }

  @Override
  public String getSecretKey() {
    return System.getenv("OTC_SDK_SK");
  }

  @Override
  public String getSecurityAccessKey() {
    return null;
  }

  @Override
  public String getSecuritySecretKey() {
    return null;
  }

  @Override
  public String getUserData(String envvarname) {
    return System.getenv(envvarname);
  }

  @Override
  public String getFunctionName() {
    return "function-name";
  }

  @Override
  public int getRunningTimeInSeconds() {
    return 100;
  }

  @Override
  public String getVersion() {
    return "latest";
  }

  @Override
  public int getMemorySize() {
    return 100;
  }

  @Override
  public int getCPUNumber() {
    return 1;
  }

  @Override
  public String getProjectID() {
    return System.getenv("OTC_SDK_PROJECTID");
  }

  @Override
  public String getPackage() {
    return "default";
  }

  @Override
  public String getToken() {
    return "token";
  }

  @Override
  public String getSecurityToken() {
    return "ST";
  }

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

  @Override
  public Object getState() {
    return "succes";
  }

  @Override
  public void setState(Object var1) {

  }

  @Override
  public String getInstanceID() {
    return "instanceid";
  }

  @Override
  public String getInvokeProperty() {
    return "InvokeProperty";
  }

  @Override
  public String getTraceID() {
    return "traceid";
  }

  @Override
  public String getInvokeID() {
    return "invokeid";
  }

  @Override
  public String getAlias() {
    return "alias";
  }

  @Override
  public String getWorkflowRunID() {
    return "workflowrunid";

  }

  @Override
  public String getWorkflowStateID() {
    return "workflowstateid";
  }

  @Override
  public String getWorkflowID() {
    return "workflowid";
  }

};