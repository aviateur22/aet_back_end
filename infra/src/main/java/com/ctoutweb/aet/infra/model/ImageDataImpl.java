package com.ctoutweb.aet.infra.model;


public record ImageDataImpl(String imagePath, String imageName) implements IImageData {
  @Override
  public String getImagePath() {
    return imagePath;
  }

  @Override
  public String getImageName() {
    return imageName;
  }
}
