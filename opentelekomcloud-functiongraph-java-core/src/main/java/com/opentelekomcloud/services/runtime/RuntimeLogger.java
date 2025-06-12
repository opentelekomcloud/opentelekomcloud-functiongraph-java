package com.opentelekomcloud.services.runtime;

/**
 * will output the content to the standard output in the format of "time-request
 * ID-output content". An example is as follows:
 * 2017-10-25T09:10:03.328Z 473d369d-101a-445e-a7a8-315cca788f86 test log output
 */
public interface RuntimeLogger {
  /**
   * Logs a string to LogTankService Logs
   
   * Logging will not be done:<br>
   * <ul>
   * <li>If the container is not configured to log to LogTankService.</li>
   
   * <li>If the role provided to the function does not have sufficient permissions.</li>
   * </ul>
   
   
   * @param message A string containing the event to log.
   */
  void log(String message);

  void debug(String var1);

  void info(String var1);

  void warn(String var1);

  void error(String var1);

  void setLevel(String var1);
}
