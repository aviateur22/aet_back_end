package com.ctoutweb.aet.infra.config.apiPathDefinition;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class ApiPathConfig {
  @Value("${api.version.path}")
  private String apiVersionPath;

  public String getPath(ApiPathAvail apiPathEnum) {
    return this.apiVersionPath + apiPathEnum.getPath();
  }
}
