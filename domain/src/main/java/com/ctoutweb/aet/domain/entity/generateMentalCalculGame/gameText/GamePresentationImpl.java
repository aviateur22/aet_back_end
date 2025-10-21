package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText;

import com.ctoutweb.aet.domain.entity.gameText.IGameTextPresentation;

public record GamePresentationImpl(String presentationText, String title) implements IGameTextPresentation {

  @Override
  public String getGameTitle() {
    return title;
  }

  @Override
  public String getPresentationText() {
    return presentationText;
  }
}
