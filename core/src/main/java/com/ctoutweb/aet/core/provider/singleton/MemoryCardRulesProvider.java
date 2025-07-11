package com.ctoutweb.aet.core.provider.singleton;

import com.ctoutweb.aet.core.usecase.memoryCardGame.businessRules.MemoryCardGameRules;
import com.ctoutweb.aet.core.usecase.memoryCardGame.port.IPortMemoryCardService;

public class MemoryCardRulesProvider implements IMemoryCardRulesProvider{
  private MemoryCardGameRules instance;
  public MemoryCardGameRules provideMemoryCardGameRules(IPortMemoryCardService portMemoryCardService) {
    if (instance == null) {
      instance = new MemoryCardGameRules(portMemoryCardService);
    }
    return instance;
  }
}
