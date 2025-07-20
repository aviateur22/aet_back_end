package com.ctoutweb.aet.core.entity.memoryCardGame.impl;

import com.ctoutweb.aet.core.entity.memoryCardGame.ICardImage;

public record CardImageImpl(String imageFrontName, String imageBackName) implements ICardImage {
  @Override
  public String getImageFrontName() {
    return imageFrontName;
  }

  @Override
  public String getImageBackName() {
    return imageBackName;
  }
}
