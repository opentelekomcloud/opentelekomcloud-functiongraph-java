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
 * will output the content to the standard output in the format of "time-request
 * ID-output content". An example is as follows:
 * 2017-10-25T09:10:03.328Z 473d369d-101a-445e-a7a8-315cca788f86 test log output
 */
public interface RuntimeLogger {
  /**
   * Logs a string to LogTankService Logs
   * 
   * Logging will not be done:<br>
   * <ul>
   * <li>If the container is not configured to log to LogTankService.</li>
   * 
   * <li>If the role provided to the function does not have sufficient
   * permissions.</li>
   * </ul>
   * 
   * 
   * @param message A string containing the message to log.
   */
  void log(String message);

  /**
   * Logs a string to LogTankService with the DEBUG log level.
   *
   * @param message A string containing the message to log.
   */
  void debug(String message);

  /**
   * Logs a string to LogTankService with the INFO log level.
   *
   * @param message A string containing the message to log.
   */
  void info(String message);

  /**
   * Logs a string to LogTankService with the WARN log level.
   *
   * @param message A string containing the message to log.
   */

  void warn(String message);

  /**
   * Logs a string to LogTankService with the ERROR log level.
   *
   * @param message A string containing the event to log.
   */
  void error(String message);

  /**
   * Sets the log level for the logger.
   *
   * @param logLevel The log level to set (e.g., "DEBUG", "INFO", "WARN",
   *                 "ERROR").
   */
  void setLevel(String logLevel);
}
