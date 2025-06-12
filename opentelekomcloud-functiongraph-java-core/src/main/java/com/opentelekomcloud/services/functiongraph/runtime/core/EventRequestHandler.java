package com.opentelekomcloud.services.functiongraph.runtime.core;

import com.opentelekomcloud.services.runtime.Context;

/**
 * 
 * FunctionGraph request handlers implement FunctionGraph application logic
 * using TriggerEvent objects as input and plain old java objects
 * as output.
 *
 * @param <Input>  The input parameter of type TriggerEvent
 * @param <Output> The output parameter type
 */
public interface EventRequestHandler<Input extends TriggerEvent, Output> extends RequestHandler<Input, Output> {
  /**
   * Handles a FunctionGraph request
   * 
   * @param input   The FunctionGraph event input of type TriggerEvent
   * @param context The FunctionGraph execution environment context object.
   * @return The FunctionGraph Function output
   */
  Output handleRequest(Input input, Context context);

}