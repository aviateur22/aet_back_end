package com.ctoutweb.aet.core.entity.gameText;

public record GameTextInformation (
        String[] congratulationWords,
        String[] loosingWords,
        String gameLostText,
        String gameVictoryText,
        GamePresentation presentationText
) implements  IGameTextInformation {
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
    return presentationText;
  }
}
