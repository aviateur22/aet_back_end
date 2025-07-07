package com.ctoutweb.aet.usecase.memoryCardGame;

import com.ctoutweb.aet.usecase.IUseCase;
import com.ctoutweb.aet.usecase.memoryCardGame.boundary.Output;
import com.ctoutweb.aet.usecase.memoryCardGame.port.IMemoryCardService;

public class MemoryCardGameUseCase implements IUseCase<MemoryCardGameUseCase.Input, Output> {
  private final IMemoryCardService memoryCardService;

  public MemoryCardGameUseCase(IMemoryCardService memoryCardService) {
    this.memoryCardService = memoryCardService;
  }

  @Override
  public com.ctoutweb.aet.usecase.memoryCardGame.boundary.Output execute(Input input) {
    return null;
  }
}
