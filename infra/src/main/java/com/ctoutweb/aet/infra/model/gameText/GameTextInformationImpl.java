package com.ctoutweb.aet.infra.model.gameText;

import com.ctoutweb.aet.infra.model.memoryCardGame.IGameTextInformation;

public record GameTextInformationImpl(
        String[] congratulationWords,
        String[] loosingWords,
        String gameLostText,
        String gameVictoryText,
        GamePresentation gamePresentation) implements IGameTextInformation {
  @Override
  public String[] getCongratulationWords() {
    return congratulationWords;
  }

  @Override
  public String[] getLoosingWords() {
    return loosingWords;
  }

  @Override
  public String getGameLostText() {
    return gameLostText;
  }

  @Override
  public String getGameVictoryText() {
    return gameVictoryText;
  }

  @Override
  public GamePresentation getGamePresentation() {
    return gamePresentation;
  }
}
