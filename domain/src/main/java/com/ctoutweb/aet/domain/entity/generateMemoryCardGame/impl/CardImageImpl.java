package com.ctoutweb.aet.domain.entity.generateMemoryCardGame.impl;

import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.ICardImage;

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
