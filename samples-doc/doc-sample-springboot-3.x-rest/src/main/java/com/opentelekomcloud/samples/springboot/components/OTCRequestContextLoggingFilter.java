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

package com.opentelekomcloud.samples.springboot.components;

import java.io.IOException;
import java.util.Enumeration;

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

/**
 * OTCRequestContextLoggingFilter is a filter that logs request context information.
 * It captures specific headers from the HTTP request and stores them in the ThreadContext
 * for logging purposes. This allows for better tracking of requests in logs.
 */
@Component
@Order(1)
public class OTCRequestContextLoggingFilter implements Filter {
  private static final Logger logger = LoggerFactory.getLogger(OTCRequestContextLoggingFilter.class);

  /**
   * Default header fields of an HTTP function.
   * </p>
   * @see <a hred="https://docs.otc.t-systems.com/function-graph/umn/building_functions/creating_a_function_from_scratch/creating_an_http_function.html#id10">Creating a http function</a>
   */
  private static final String[] X_CFF_HEADERS = {
      "x-cff-request-id",
      "x-cff-memory",
      "x-cff-timeout",
      "x-cff-func-version",
      "x-cff-func-name",
      "x-cff-project-id",
      "x-cff-package",
      "x-cff-region" };

  /**
   * Store requestId in ThreadLocal to be accessible in all classes.
   */
  private static final ThreadLocal<String> requestId = new ThreadLocal<>();

  public static String getRequestId() {
    return requestId.get();
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) request;

    String cffRequestID = httpServletRequest.getHeader("x-cff-request-id");

    if (logger.isDebugEnabled()) {
      // Display all available headers
      Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
      while (headerNames.hasMoreElements()) {
        String value = headerNames.nextElement();
        logger.debug("{} = {}", value, httpServletRequest.getHeader(value));
      }
    }

    // Store values in ThreadLocal variables:
    requestId.set(cffRequestID);

    // Put X_CFF_HEADERS to Logging ThreadContext:
    for (String header : X_CFF_HEADERS) {
      ThreadContext.put(header, httpServletRequest.getHeader(header));
    }

    chain.doFilter(request, response);

    // Remove X_CFF_HEADERS from Logging ThreadContext:
    for (String header : X_CFF_HEADERS) {
      ThreadContext.remove(header);
    }

    // Remove values from ThreadLocal variables:
    requestId.remove();
  }

}
