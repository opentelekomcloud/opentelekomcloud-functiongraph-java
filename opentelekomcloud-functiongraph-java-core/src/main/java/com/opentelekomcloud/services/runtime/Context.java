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

package com.opentelekomcloud.services.runtime;

/**
 * 
 * The context object allows you to access useful information available within
 * the FunctionGraph execution environment
 *
 */
public interface Context {
  /**
   * Returns the request ID associated with the request.
   * <p>
   * This is the same ID returned to the client that called invoke(). This ID
   * is reused for retries on the same request.
   * </p>
   * 
   * @return request ID
   */
  String getRequestID();

  /**
   * Returns the time remaining for this execution in milliseconds.
   * 
   * = max(runningTimeInSeconds * 1000 - (currentTimeMillis - functionStartTime), 0)
   * 
   * @return remaining running time in milliseconds.
   */
  int getRemainingTimeInMilliSeconds();

  /**
   * Returns the AccessKey from header: "x-access-key"
   * 
   * <b>If you use this method, you need to configure an agency for the
   * function.</b>*
   * 
   * @deprecated
   *             <p>
   *             FunctionGraph has stopped maintaining the getAccessKey() API in
   *             the Runtime
   *             SDK.
   *             <p>
   *             You cannot use this API to obtain a temporary AK.
   *             <p>
   *             Use {@link Context#getSecurityAccessKey()} instead.
   * 
   * @return AccessKey (valid for 24 hours) with an agency.
   * 
   */
  @Deprecated
  String getAccessKey();

  /**
   * Returns the SecretKey from header: "x-secret-key"
   * 
   * <b>If you use this method, you need to configure an agency for the
   * function.</b>
   * 
   * @deprecated
   *             <p>
   *             FunctionGraph has stopped maintaining the getSecretKey() API in
   *             the Runtime
   *             SDK.
   *             <p>
   *             You cannot use this API to obtain a temporary SK.
   *             <p>
   *             Use {@link Context#getSecuritySecretKey()} instead
   *
   * @return SecretKey (valid for 24 hours) with an agency.
   * 
   */
  @Deprecated
  String getSecretKey();

  /**
   * Returns the SecurityAccessKey from header: "x-security-access-key"
   * 
   * <b>If you use this method, you need to configure an agency for the
   * function.</b>
   * 
   * @return SecurityAccessKey (valid for 24 hours) with an agency.
   * 
   */
  String getSecurityAccessKey();

  /**
   * Returns the SecuritySecretKey from header: "x-security-secret-key"
   * 
   * <b>If you use this method, you need to configure an agency for the
   * function.</b>
   * 
   * @return SecuritySecretKey (valid for 24 hours) with an agency.
   */
  String getSecuritySecretKey();

  /**
   * Returns the value of the specified environment variable.
   * 
   * @param envvarname name of environment variable to get
   * @return value of environment variable
   */
  String getUserData(String envvarname);

  /**
   * Returns the name of the function being executed.
   * 
   * @return name of function
   */
  String getFunctionName();

  /**
   * Returns the timeout of function.
   * 
   * @return timeout of function
   */
  int getRunningTimeInSeconds();

  /**
   * Returns the version of the function being executed.
   * 
   * @return version of the function
   */
  String getVersion();

  /**
   * Returns the allocated memory of function.
   * 
   * @return allocated memory of function
   */
  int getMemorySize();

  /**
   * Returns the CPU usage of function.
   * 
   * @return CPU usage of function
   */
  int getCPUNumber();

  /**
   * Returns the project ID.
   * 
   * @return project id
   */
  String getProjectID();

  /**
   * Returns the function package.
   * 
   * @return function group, that is, an app
   */
  String getPackage();

  /**
   * Returns the token from header: "x-auth-token"
   * 
   * <b>If you use this method, you need to configure an agency for the
   * function.</b>
   * 
   * @return token (valid for 24 hours) with an agency
   */
  String getToken();

  /**
   * Returns the SecurityToken from header: "x-security-token"
   * 
   * <b>If you use this method, you need to configure an agency for the
   * function.</b>
   * 
   * @return SecurityToken (valid for 24 hours) with an agency
   */
  String getSecurityToken();

  /**
   * Returns the FunctionGraph logger instance associated with the context object.
   * 
   * @return logger method provided by the context
   */
  RuntimeLogger getLogger();

  /**
   * Returns the state of the function execution.
   * 
   * @return state
   */
  Object getState();

  void setState(Object var1);

  /**
   * Returns instance id where the function is running.
   * 
   * @return instance id
   */
  String getInstanceID();

  /**
   * Returns the invoke property.
   * 
   * @return invoke property
   */
  String getInvokeProperty();

  /**
   * Returns trace id.
   * 
   * @return trace id
   */
  String getTraceID();

  /**
   * Returns how often function has been called.
   * 
   * @return invoke id
   */
  String getInvokeID();

  /**
   * Returns function alias from header: "x-invoke-alias"
   * 
   * @return alias
   */
  String getAlias();

  /**
   * Returns the workflow execution id from header: "x-workflow-run-id"
   * 
   * @deprecated
   *             <p>
   *             Not available on OpenTelekomCloud.
   * 
   * @return workflow run id
   */
  @Deprecated
  String getWorkflowRunID();

  /**
   * Returns the workflow state id from header: "x-workflow-state-id"
   * 
   * @deprecated
   *             <p>
   *             Not available on OpenTelekomCloud.
   * 
   * @return workflow state id
   */
  @Deprecated
  String getWorkflowStateID();

  /**
   * Returns the workflow id from header: "x-workflow-id"
   * 
   * @deprecated
   *             <p>
   *             Not available on OpenTelekomCloud.
   * 
   * @return workflow id
   */
  String getWorkflowID();
}
