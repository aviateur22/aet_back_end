package com.ctoutweb.aet.usecase.memoryCardGame.boundary;

import com.ctoutweb.aet.usecase.memoryCardGame.port.IMemoryCardData;
import com.ctoutweb.aet.usecase.IUseCase;

public class Output implements IUseCase.Output {
  private IMemoryCardData memoryCard;
  public Output(IMemoryCardData memoryCard) {
    this.memoryCard = memoryCard;
  }
  public IMemoryCardData getMemoryCard() {
    return memoryCard;
  }
}
