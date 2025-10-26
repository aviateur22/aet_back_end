package com.ctoutweb.aet.infra.model.gameText;

public record GameTextInformationImpl(
        String[] congratulationWords,
        String[] loosingWords,
        GamePresentation gamePresentation,
        GameEndParameterByLevel[] gameEndParameterByLevels) implements IGameTextInformation {
  @Override
  public String[] getCongratulationWords() {
    return congratulationWords;
  }

  @Override
  public String[] getLoosingWords() {
    return loosingWords;
  }

  @Override
  public GamePresentation getGamePresentation() {
    return gamePresentation;
  }

  @Override
  public GameEndParameterByLevel[] getGameEndParameterByLevels() {
    return gameEndParameterByLevels;
  }
}
