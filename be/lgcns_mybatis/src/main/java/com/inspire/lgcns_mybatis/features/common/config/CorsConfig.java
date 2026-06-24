package com.inspire.lgcns_mybatis.features.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedHeaders("*")
      .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
      .allowedOriginPatterns("http://localhost:3000")
      .allowCredentials(true);
  }
  
}
