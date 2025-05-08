package com.opentelekomcloud.samples.springboot.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OTCRequestContextConfig {
  @Bean
  public FilterRegistrationBean<OTCRequestContextFilter> otcRequestContextFilter() {
    FilterRegistrationBean<OTCRequestContextFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new OTCRequestContextFilter());
    registrationBean.addUrlPatterns("/*");
    return registrationBean;
  }
}
