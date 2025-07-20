package com.ctoutweb.aet.infra.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@Configuration
public class CustomAuthentificationEntryPoint implements AuthenticationEntryPoint {
  private static final Logger LOGGER = LogManager.getLogger();

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    LOGGER.error(()->String.format("[CustomAuthentificationEntryPoint] - [commence] exception: %s", authException.getMessage()));
  }
}
