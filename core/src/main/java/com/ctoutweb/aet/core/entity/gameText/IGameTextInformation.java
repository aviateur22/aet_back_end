package com.ctoutweb.aet.core.entity.gameText;

public interface IGameTextInformation {
  String[] getCongratulationWords();
  String[] getLoosingWords();
  String getGameLostText();
  String getGameVictoryText();
  GamePresentation getGamePresentation();
}
