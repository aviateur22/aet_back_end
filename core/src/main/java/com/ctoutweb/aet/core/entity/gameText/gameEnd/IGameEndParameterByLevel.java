package com.ctoutweb.aet.core.entity.gameText.gameEnd;

public interface IGameEndParameterByLevel {
  int getMinErrorLevel();
  int getMaxErrorLevel();
  EndErrorLevel getEndErrorLevel();
  IGameEndText getGameEndText();
}
