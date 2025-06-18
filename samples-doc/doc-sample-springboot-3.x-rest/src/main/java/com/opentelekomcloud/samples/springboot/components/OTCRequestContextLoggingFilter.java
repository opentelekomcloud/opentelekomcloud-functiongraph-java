package com.opentelekomcloud.samples.springboot.components;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class OTCRequestContextLoggingFilter implements Filter {
  private static Logger logger = LoggerFactory.getLogger(OTCRequestContextLoggingFilter.class);

  /**
   * Default header fields of an HTTP function.
   * 
   * @see: https://docs.otc.t-systems.com/function-graph/umn/building_functions/creating_a_function_from_scratch/creating_an_http_function.html#id10
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

    // Put X_CFF_HEADERS to Logging MDC:
    for (String header : X_CFF_HEADERS) {
      MDC.put(header, httpServletRequest.getHeader(header));
    }

    chain.doFilter(request, response);

    // Remove X_CFF_HEADERS from Logging MDC:
    for (String header : X_CFF_HEADERS) {
      MDC.remove(header);
    }

    // Remove values from ThreadLocal variables:
    requestId.remove();
  }

}
