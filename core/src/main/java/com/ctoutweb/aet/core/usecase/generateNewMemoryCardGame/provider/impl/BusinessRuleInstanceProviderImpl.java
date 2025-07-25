package com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.provider.impl;

import com.ctoutweb.aet.core.entity.memoryCardGame.GameLevel;
import com.ctoutweb.aet.core.entity.memoryCardGame.ParameterState;
import com.ctoutweb.aet.core.usecase.IUseCase;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.GenerateNewMemoryCardGameUseCase;
import com.ctoutweb.aet.core.entity.memoryCardGame.businessRules.MemoryCardGameRules;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.port.IGenerateNewGameGateway;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.provider.IBusinessInstanceProvider;

public class BusinessRuleInstanceProviderImpl implements IBusinessInstanceProvider {
  @Override
  public MemoryCardGameRules provideMemoryCardGameRules(
          IGenerateNewGameGateway generateNewGameGateway,
          GameLevel gameLevel,
          ParameterState parameterState) {
    return new MemoryCardGameRules(generateNewGameGateway, gameLevel, parameterState);
  }

  @Override
  public IUseCase<GenerateNewMemoryCardGameUseCase.Input, GenerateNewMemoryCardGameUseCase.Output> provideUseCase(IGenerateNewGameGateway generateNewGameGateway) {
    return new GenerateNewMemoryCardGameUseCase(generateNewGameGateway);
  }
}
