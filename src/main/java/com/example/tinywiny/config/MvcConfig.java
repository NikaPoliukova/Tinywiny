package com.example.tinywiny.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.constraints.NotNull;

@Configuration

public class MvcConfig implements WebMvcConfigurer {

  private final String uiUrl;

  @Autowired
public MvcConfig(@Value("http://localhost:3000") final String uiUrl){
    this.uiUrl=uiUrl;
  }
  @Override
  public void addCorsMappings(@NotNull final CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins(uiUrl).allowedMethods("*").allowCredentials(true);
  }
}
