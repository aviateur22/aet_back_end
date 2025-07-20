package com.ctoutweb.aet.infra.model.memoryCardGame.adapter;

import com.ctoutweb.aet.core.entity.memoryCardGame.GameLevel;
import com.ctoutweb.aet.core.entity.memoryCardGame.ParameterState;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.boundary.IGenerateNewGameRequest;

public record GenerateNewGameRequestImpl(GameLevel level, ParameterState parameterState) implements IGenerateNewGameRequest {
  @Override
  public GameLevel getGameLevel() {
    return level;
  }

  @Override
  public ParameterState getParameterState() {
    return parameterState;
  }
}
