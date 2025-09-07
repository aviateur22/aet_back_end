package com.ctoutweb.aet.domain.port.generateMemoryCardGame;

import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.GameLevel;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.ParameterState;

public interface IGenerateMemoryCardGameInput {
  GameLevel getGameLevel();
  ParameterState getParameterState();
}
