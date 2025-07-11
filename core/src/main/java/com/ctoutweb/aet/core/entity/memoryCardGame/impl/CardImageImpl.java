package com.ctoutweb.aet.core.entity.memoryCardGame.impl;

import com.ctoutweb.aet.core.entity.memoryCardGame.ICardImage;

public record CardImageImpl(String cardFrontImagePath, String cardBackImagePath) implements ICardImage {
  @Override
  public String getCardFrontImagePath() {
    return cardFrontImagePath;
  }

  @Override
  public String getCardBackImagePath() {
    return cardBackImagePath;
  }
}
