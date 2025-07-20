package com.ctoutweb.aet.infra.config.apiPathDefinition;

public enum ApiPathAvail {
  GAMES("/games/**"),
  IMAGES("/images/**");
  private String path;
  private ApiPathAvail(String path) {
    this.path = path;
  }
  public String getPath() {
    return this.path;
  }
}
