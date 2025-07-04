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
   * Gets the request ID associated with the request.
	 * <p>
	 * This is the same ID returned to the client that called invoke(). This ID
	 * is reused for retries on the same request.
	 * </p>
   * @return request ID
   */
  String getRequestID();

  /**
   * Gets the time remaining for this execution in milliseconds
   * 
   * @return remaining running time in milliseconds.
   */
  int getRemainingTimeInMilliSeconds();

  /**
   * 
   * <b>If you use this method, you need to configure an agency for the
   * function.</b>*
   * @deprecated
   * <p>FunctionGraph has stopped maintaining the getAccessKey() API in the Runtime
   * SDK.
   * <p>You cannot use this API to obtain a temporary AK.
   * <p> Use {@link Context#getSecurityAccessKey()} instead.
   * 
   * @return AccessKey (valid for 24 hours) with an agency.
   * 
   */
  @Deprecated
  String getAccessKey();

  /**
   * <b>If you use this method, you need to configure an agency for the
   * function.</b>
   * @deprecated
   * <p>FunctionGraph has stopped maintaining the getSecretKey() API in the Runtime
   * SDK.
   * <p>You cannot use this API to obtain a temporary SK.
   * <p> Use {@link Context#getSecuritySecretKey()} instead
   *
   * @return SecretKey (valid for 24 hours) with an agency.
   * 
   */
  @Deprecated
  String getSecretKey();

  /**
   * <b>If you use this method, you need to configure an agency for the
   * function.</b>
   * 
   * @return SecurityAccessKey (valid for 24 hours) with an agency.
   * 
   */
  String getSecurityAccessKey();

  /**
   * <b>If you use this method, you need to configure an agency for the
   * function.</b>
   * 
   * @return SecuritySecretKey (valid for 24 hours) with an agency.
   */
  String getSecuritySecretKey();

  /**
    * 
   * @param envvarname name of environment variable to get
   * @return value of environment variable 
   */
  String getUserData(String envvarname);

  /**
   * Gets the name of the function being executed.
   * 
   * @return name of function
   */
  String getFunctionName();

  /**
   * 
   * @return timeout of function
   */
  int getRunningTimeInSeconds();

  /**
   * Gets the version of the function being executed.
   * 
   * @return version of the function
   */
  String getVersion();

  /**
   * 
   * @return allocated memory of function
   */
  int getMemorySize();

  /**
   * 
   * @return CPU usage of function
   */
  int getCPUNumber();


  /**
   * 
   * @return project id
   */
  String getProjectID();

  /**
   * 
   * @return function group, that is, an app
   */
  String getPackage();

  /**
    * <b>If you use this method, you need to configure an agency for the
   * function.</b>
      * 
   * @return token (valid for 24 hours) with an agency
   */
  String getToken();

  /**
  * <b>If you use this method, you need to configure an agency for the
   * function.</b>
   * 
   * @return SecurityToken (valid for 24 hours) with an agency
   */
  String getSecurityToken();

  /**
   * Gets the FunctionGraph logger instance associated with the context object
   * 
   * @return logger method provided by the context
   */
  RuntimeLogger getLogger();
  
  
  /**
   * 
   * @return state
   */
  Object getState();

  void setState(Object var1);

  /**
   * 
   * @return instance id
   */
  String getInstanceID();

  /**
   * 
   * @return invoke property
   */
  String getInvokeProperty();

  /**
   * @deprecated
   * <p>Not available on OpenTelekomCloud.
   * 
   * @return trace id
   */
  @Deprecated
  String getTraceID();

  /**
   * 
   * @return invoke id
   */
  String getInvokeID();

  /**
   * 
   * @return alias
   */
  String getAlias();

  /**
   * @deprecated
   * <p>Not available on OpenTelekomCloud.
   * 
   * @return workflow run id
   */
  @Deprecated
  String getWorkflowRunID();

  /**
   * @deprecated
   * <p>Not available on OpenTelekomCloud.
   * 
   * @return workflow state id
   */
  @Deprecated
  String getWorkflowStateID();

  /**
   * @deprecated
   * <p>Not available on OpenTelekomCloud.
   * 
   * @return workflow id
   */
  String getWorkflowID();
}
