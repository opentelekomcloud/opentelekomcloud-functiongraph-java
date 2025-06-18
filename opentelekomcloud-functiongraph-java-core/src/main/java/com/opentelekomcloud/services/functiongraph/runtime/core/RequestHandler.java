package com.opentelekomcloud.services.functiongraph.runtime.core;

import com.opentelekomcloud.services.runtime.Context;

/**
 * 
 * FunctionGraph request handlers implement FunctionGraph application logic
 * using plain old java objects
 * as input and output.
 * @deprecated
 * This interface is currently not working with gson on OpenTelekomCloud
 * 
 * @param <Input>  The input parameter type
 * @param <Output> The output parameter type
 */
@Deprecated
public interface RequestHandler<Input, Output> {
  /**
   * Handles a FunctionGraph request
   * 
   * @param input   The FunctionGraph Function input
   * @param context The FunctionGraph execution environment context object.
   * @return The FunctionGraph Function output
   */
  Output handleRequest(Input input, Context context);

  /**
   * Default implementation for optional initializer method
   * 
   * Function initialization refers to the process of initializing a 
   * function when it is called, including setting the initial state
   * of the function, allocating resources, or assigning initial 
   * values ​​to variables within the function.
   * 
   * FunctionGraph performs function initialization after the function 
   * instance is successfully started. Only after the initialization is
   * successfully executed can the function instance start processing
   * function call requests.
   * 
   * FunctionGraph ensures that a function instance only successfully 
   * executes function initialization once during its life cycle. 
   * The execution time of function initialization will also be measured, 
   * and users need to pay for it. 
   * 
   * The billing method is the same as the function call processing.
   * 
   * In general scenarios, business logic that can be shared by multiple
   * request processing is suitable for being placed in the initialization
   * function to reduce function latency. For example, loading a large-scale
   * model in a deep learning scenario or building a connection pool in a
   * database scenario.
   * 
   * @param context
   */
  default public void initializer(Context context) {

  }

  /**
   * default implementation for optional heartbeat method
   * 
   * The heartbeat function is used to detect abnormal scenarios 
   * when user functions are running, such as function deadlock, 
   * function memory overflow, function network abnormality, etc.
   *
   * After configuring the heartbeat function, when the function is running,
   * FunctionGraph sends a heartbeat request to the function instance 
   * every 5 seconds to trigger the heartbeat function.
   * If the heartbeat request returns an exception, 
   * FunctionGraph will consider the function instance abnormal and
   * terminate the function instance.
   * 
   * The timeout period for FunctionGraph heartbeat requests is 3 seconds.
   * If there is no response to six consecutive heartbeat requests,
   * the function instance will be terminated.
   * 
   * @return
   */
  default public boolean heartbeat() {
    return true;
  }

  /**
   * default implementation for Pre-Stop method
   * 
   * The Pre-stop function is a callback function FunctionGraph invokes
   * before the current function instance stops.
   */
  default public void prestop() {
    
  }
}