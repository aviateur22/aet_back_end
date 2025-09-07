package com.ctoutweb.aet.domain.entity.generateMemoryCardGame.impl.gameTextImpl;

import com.ctoutweb.aet.domain.entity.gameText.IGameTextPresentation;

public record GamePresentationImpl(String gameTitle, String presentationText) implements IGameTextPresentation {
  @Override
  public String getGameTitle() {
    return gameTitle;
  }

  @Override
  public String getPresentationText() {
    return presentationText;
  }
}
