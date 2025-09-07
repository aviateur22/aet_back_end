package com.ctoutweb.aet.domain.entity;

import com.ctoutweb.aet.domain.usecase.base.IUseCase;

public abstract class JobGame < T extends IUseCase.Input, U extends IUseCase.Output> {

  public abstract void generateGame(T inputData);

  public abstract U getGeneratedGameData();
}
