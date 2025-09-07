package com.ctoutweb.aet.domain.entity.gameText.gameEnd;

public interface IGameEndParameterByLevel {
  int getMinErrorLevel();
  int getMaxErrorLevel();
  EndErrorLevel getEndErrorLevel();
  IGameEndText getGameEndText();
}
