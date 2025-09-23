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

/** * EventLoadingException is a custom exception that is thrown when there is an error loading an event.
 * It extends RuntimeException and provides constructors for creating an exception with a message
 * or with both a message and a cause.
 */
public class EventLoadingException extends RuntimeException {

    private static final long serialVersionUID = 1766526903372206270L;

    public EventLoadingException() {
    }

    public EventLoadingException(String message) {
        super(message);
    }

    public EventLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
