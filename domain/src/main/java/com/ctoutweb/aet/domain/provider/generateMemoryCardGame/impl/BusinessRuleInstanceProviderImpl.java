package com.ctoutweb.aet.domain.provider.generateMemoryCardGame.impl;

import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.GameLevel;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.ParameterState;
import com.ctoutweb.aet.domain.usecase.base.IUseCase;
import com.ctoutweb.aet.domain.usecase.GenerateMemoryCardGameUseCase;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.businessRules.MemoryCardGameRules;
import com.ctoutweb.aet.domain.port.generateMemoryCardGame.IGenerateMemoryCardGameGateway;
import com.ctoutweb.aet.domain.provider.generateMemoryCardGame.IBusinessInstanceProvider;

public class BusinessRuleInstanceProviderImpl implements IBusinessInstanceProvider {
  @Override
  public MemoryCardGameRules provideMemoryCardGameRules(
          IGenerateMemoryCardGameGateway generateNewGameGateway,
          GameLevel gameLevel,
          ParameterState parameterState) {
    return new MemoryCardGameRules(generateNewGameGateway, gameLevel, parameterState);
  }

  @Override
  public IUseCase<GenerateMemoryCardGameUseCase.Input, GenerateMemoryCardGameUseCase.Output> provideUseCase(IGenerateMemoryCardGameGateway generateNewGameGateway) {
    return new GenerateMemoryCardGameUseCase(generateNewGameGateway);
  }
}
