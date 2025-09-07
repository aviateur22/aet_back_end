package com.ctoutweb.aet.infra.model.memoryCardGame.adapter;

import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.GameLevel;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.ParameterState;
import com.ctoutweb.aet.domain.port.generateMemoryCardGame.IGenerateMemoryCardGameInput;

public record GenerateNewGameRequestImpl(GameLevel level, ParameterState parameterState) implements IGenerateMemoryCardGameInput {
  @Override
  public GameLevel getGameLevel() {
    return level;
  }

  @Override
  public ParameterState getParameterState() {
    return parameterState;
  }
}
