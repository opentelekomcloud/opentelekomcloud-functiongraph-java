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
package com.opentelekomcloud.services.functiongraph.runtime.test.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** * Response is a custom annotation used to specify a JSON file containing response data for testing purposes.
 * It can be applied to test methods to automatically load the specified response data.
 * The annotation requires the path and file name of the JSON file and the type of the response.
 * 
 * This annotation must be used in conjunction with
 * {@link HandlerParams}.<br/>
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Response {

    /**
     * Path and file name of the json response
     * @return the file name (including the path)
     */
    String value();

    /**
     * Type of the response
     * @return the type of the response
     */
    Class<?> type() default Void.class;
}
