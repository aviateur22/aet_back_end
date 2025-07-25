package com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.provider;

import com.ctoutweb.aet.core.entity.memoryCardGame.GameLevel;
import com.ctoutweb.aet.core.entity.memoryCardGame.ParameterState;
import com.ctoutweb.aet.core.usecase.IUseCase;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.GenerateNewMemoryCardGameUseCase;
import com.ctoutweb.aet.core.entity.memoryCardGame.businessRules.MemoryCardGameRules;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.port.IGenerateNewGameGateway;

public interface IBusinessInstanceProvider {
  MemoryCardGameRules provideMemoryCardGameRules(
          IGenerateNewGameGateway generateNewGameGateway,
          GameLevel gameLevel,
          ParameterState parameterState);
  IUseCase<GenerateNewMemoryCardGameUseCase.Input, GenerateNewMemoryCardGameUseCase.Output> provideUseCase(
          IGenerateNewGameGateway generateNewGameGateway
  );
}
