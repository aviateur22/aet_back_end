package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText;

import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndText;

public record GameEndTextImpl(String endTitle, String endGameText) implements IGameEndText {
  @Override
  public String getEndTitle() {
    return endTitle;
  }

  @Override
  public String getEndText() {
    return endGameText;
  }
}
