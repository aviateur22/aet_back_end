package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText;

import com.ctoutweb.aet.domain.entity.gameText.IGameTextPresentation;

import static com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.CalculMentalText.GAME_TEXT_PRESENTATION;

public record TextPresentationImpl(String presentationText, String title) implements IGameTextPresentation {

  @Override
  public String getGameTitle() {
    return title;
  }

  @Override
  public String getPresentationText() {
    return presentationText;
  }
}
