package com.opentelekomcloud.samples.springboot.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;

public class OTCRequestContextFilter implements Filter {
  private static final String APIG_REQUEST_ID = "x-apig-request-id";
  private static final String APIG_API_ID="x-apig-api-id";
  private static final String APIG_SOURCE_STAGE="x-apig-source-stage";
  private static final String APIG_SOURCE_IP="x-apig-source-ip";
  
  private static final String CFF_REQUEST_ID = "x-cff-request-id";
  
  public static final ThreadLocal<String> apigRequestId = new ThreadLocal<>();

  public static final ThreadLocal<String> requestId = new ThreadLocal<>();


  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;

    String apigRequestID=httpServletRequest.getHeader(APIG_REQUEST_ID);

    String cffRequestID=httpServletRequest.getHeader(CFF_REQUEST_ID);


    Enumeration<String> h = httpServletRequest.getHeaderNames();
    while (h.hasMoreElements()){
      String value = h.nextElement();
      System.out.println(value + " = " + httpServletRequest.getHeader(value));

    }

    requestId.set(cffRequestID);
    apigRequestId.set(apigRequestID);
    
    MDC.put("requestId", cffRequestID);

    chain.doFilter(request, response);
    
    MDC.remove("requestId");

    requestId.remove();
    apigRequestId.remove();
  }
}
