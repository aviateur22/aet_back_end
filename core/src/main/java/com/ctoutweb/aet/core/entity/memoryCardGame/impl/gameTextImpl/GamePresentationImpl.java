package com.ctoutweb.aet.core.entity.memoryCardGame.impl.gameTextImpl;

import com.ctoutweb.aet.core.entity.gameText.IGameTextPresentation;

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
