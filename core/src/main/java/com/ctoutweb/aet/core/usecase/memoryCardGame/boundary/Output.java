package com.ctoutweb.aet.core.usecase.memoryCardGame.boundary;

import com.ctoutweb.aet.core.usecase.IUseCase;

public class Output implements IUseCase.Output {
  private IOutputBoundary memoryCard;
  public Output(IOutputBoundary memoryCard) {
    this.memoryCard = memoryCard;
  }
  public IOutputBoundary getMemoryCard() {
    return memoryCard;
  }
}
