package com.ctoutweb.aet.core.usecase.memoryCardGame;

import com.ctoutweb.aet.core.provider.CoreFactoryProvider;
import com.ctoutweb.aet.core.provider.singleton.IMemoryCardRulesProvider;
import com.ctoutweb.aet.core.usecase.IUseCase;
import com.ctoutweb.aet.core.usecase.memoryCardGame.businessRules.MemoryCardGameRules;
import com.ctoutweb.aet.core.usecase.memoryCardGame.port.IPortMemoryCardService;

public class MemoryCardGameUseCase implements IUseCase<MemoryCardGameUseCase.Input, MemoryCardGameUseCase.Output> {
  private final IPortMemoryCardService memoryCardService;
  private final MemoryCardGameRules memoryCardGameRules;

  public MemoryCardGameUseCase(IPortMemoryCardService memoryCardService) {
    this.memoryCardService = memoryCardService;
    IMemoryCardRulesProvider memoryCardRulesProvider = CoreFactoryProvider.getCoreFactory().getMemoryCardRulesProvider();
    this.memoryCardGameRules = memoryCardRulesProvider.provideMemoryCardGameRules(memoryCardService);
  }

  @Override
  public Output execute(Input input) {
    return null;
  }
}
