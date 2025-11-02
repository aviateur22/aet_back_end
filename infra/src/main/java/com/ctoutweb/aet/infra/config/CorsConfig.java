package com.ctoutweb.aet.infra.config;

import com.ctoutweb.aet.infra.config.apiPathDefinition.ApiPathAvail;
import com.ctoutweb.aet.infra.config.apiPathDefinition.ApiPathConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {
  private static final Logger LOGGER = LogManager.getLogger();
  @Value("${cors.domains}")
  String corsDomains;
  private final ApiPathConfig apiPathConfig;

  public CorsConfig(ApiPathConfig apiPathConfig) {
    this.apiPathConfig = apiPathConfig;
  }

  @Bean(name = "customCorsConfiguration")
  public CorsConfigurationSource corsConfigurationSource() {
    UrlBasedCorsConfigurationSource source =new UrlBasedCorsConfigurationSource();

    LOGGER.info(()->String.format("[CorsConfig] - [corsConfigurationSource] API_VERSION: %s", apiPathConfig.getPath(ApiPathAvail.GAMES) ));
    LOGGER.info(()->String.format("[CorsConfig] - [corsConfigurationSource] CorsDomain: %s", Arrays.asList(corsDomains.split(","))));

    // Configuration Cors pour les jeux
    CorsConfiguration memoryGameCors = new CorsConfiguration();
    memoryGameCors.setAllowCredentials(true);
    memoryGameCors.setAllowedOrigins(Arrays.asList(corsDomains.split(",")));
    memoryGameCors.setAllowedMethods(Arrays.asList("GET", "POST", "PUT"));
    memoryGameCors.setAllowedHeaders(Arrays.asList("Content-Type"));
    source.registerCorsConfiguration(apiPathConfig.getPath(ApiPathAvail.GAMES), memoryGameCors);

    // Configuration Cors pour les jeux
    CorsConfiguration image = new CorsConfiguration();
    image.setAllowCredentials(true);
    image.setAllowedOrigins(Arrays.asList(corsDomains.split(",")));
    image.setAllowedMethods(Arrays.asList("GET", "POST", "PUT"));
    image.setAllowedHeaders(Arrays.asList("Content-Type"));
    source.registerCorsConfiguration(apiPathConfig.getPath(ApiPathAvail.IMAGES), image);

    return source;
  }
}
