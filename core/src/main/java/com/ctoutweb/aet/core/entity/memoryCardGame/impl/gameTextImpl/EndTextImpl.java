package com.ctoutweb.aet.core.entity.memoryCardGame.impl.gameTextImpl;

import com.ctoutweb.aet.core.entity.gameText.gameEnd.IGameEndText;

/**
 * Composition text de fin
 */
public record EndTextImpl(String endTitle, String endText) implements IGameEndText {
  @Override
  public String getEndTitle() {
    return endTitle;
  }

  @Override
  public String getEndText() {
    return endText;
  }
}
