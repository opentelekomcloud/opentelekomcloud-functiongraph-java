package com.opentelekomcloud.services.functiongraph.runtime.core;

import com.opentelekomcloud.services.runtime.Context;
/**
 * 
 * FunctionGraph request handlers implement FunctionGraph application logic using plain old java objects 
 * as input and output.
 *
 * @param <Input> The input parameter type
 * @param <Output> The output parameter type
 */
public interface RequestHandler<Input, Output>  {
 /**
     * Handles a FunctionGraph request
     * @param input The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    Output handleRequest(Input input, Context context);
}