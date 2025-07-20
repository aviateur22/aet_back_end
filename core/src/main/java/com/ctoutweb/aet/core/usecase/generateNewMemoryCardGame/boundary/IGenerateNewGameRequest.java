package com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.boundary;

import com.ctoutweb.aet.core.entity.memoryCardGame.GameLevel;
import com.ctoutweb.aet.core.entity.memoryCardGame.ParameterState;

public interface IGenerateNewGameRequest {
  GameLevel getGameLevel();
  ParameterState getParameterState();
}
