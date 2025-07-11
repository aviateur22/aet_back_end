package com.ctoutweb.aet.core.provider.singleton;

import com.ctoutweb.aet.core.usecase.memoryCardGame.businessRules.MemoryCardGameRules;
import com.ctoutweb.aet.core.usecase.memoryCardGame.port.IPortMemoryCardService;

public interface IMemoryCardRulesProvider {
  MemoryCardGameRules provideMemoryCardGameRules(IPortMemoryCardService portMemoryCardService);
}
