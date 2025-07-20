package com.ctoutweb.aet.infra.config;

import com.ctoutweb.aet.infra.config.apiPathDefinition.ApiPathConfig;
import com.ctoutweb.aet.infra.config.apiPathDefinition.ApiPathAvail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConf {
  private static final Logger LOGGER = LogManager.getLogger();
  private final ApiPathConfig apiPathConfig;
  private final CorsConfigurationSource corsConfigurationSource;
  private final AuthenticationEntryPoint authenticationEntryPoint;
  private final AccessDeniedHandler accessDeniedHandler;

  public WebSecurityConf(
          ApiPathConfig apiPathConfig, @Qualifier("customCorsConfiguration") CorsConfigurationSource corsConfigurationSource,
          AuthenticationEntryPoint authenticationEntryPoint,
          AccessDeniedHandler accessDeniedHandler) {
    this.apiPathConfig = apiPathConfig;
    this.corsConfigurationSource = corsConfigurationSource;
    this.authenticationEntryPoint = authenticationEntryPoint;
    this.accessDeniedHandler = accessDeniedHandler;
  }
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    LOGGER.info(()->String.format("[WebSecurityConf] - [securityFilterChain] API_VERSION: %s", ApiPathAvail.GAMES ));
    http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource))
            .exceptionHandling(exception -> exception
                    .accessDeniedHandler(accessDeniedHandler)
                    .authenticationEntryPoint(authenticationEntryPoint))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(
                    request -> request
                            .requestMatchers(
                                    apiPathConfig.getPath(ApiPathAvail.GAMES),
                                    apiPathConfig.getPath(ApiPathAvail.IMAGES)
                            ).permitAll()
                            .anyRequest().authenticated()
            );

    return http.build();
  }

}
