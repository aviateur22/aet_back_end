package com.ctoutweb.aet.domain.provider.generateMemoryCardGame;

import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.GameLevel;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.ParameterState;
import com.ctoutweb.aet.domain.usecase.GenerateMemoryCardGameUseCase;
import com.ctoutweb.aet.domain.usecase.base.IUseCase;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.businessRules.MemoryCardGameRules;
import com.ctoutweb.aet.domain.port.generateMemoryCardGame.IGenerateMemoryCardGameGateway;

public interface IBusinessInstanceProvider {
  MemoryCardGameRules provideMemoryCardGameRules(
          IGenerateMemoryCardGameGateway generateNewGameGateway,
          GameLevel gameLevel,
          ParameterState parameterState);
  IUseCase<GenerateMemoryCardGameUseCase.Input, GenerateMemoryCardGameUseCase.Output> provideUseCase(
          IGenerateMemoryCardGameGateway generateNewGameGateway
  );
}
