package com.ctoutweb.aet.core.usecase.memoryCardGame.provider.impl;

import com.ctoutweb.aet.core.usecase.memoryCardGame.provider.IBusinessInstanceProvider;
import com.ctoutweb.aet.core.usecase.memoryCardGame.provider.IDomainModelInstanceProvider;
import com.ctoutweb.aet.core.usecase.memoryCardGame.provider.IMemoryGameInstanceProvider;

public class MemoryCardInstanceProviderImpl implements IMemoryGameInstanceProvider {
  @Override
  public IBusinessInstanceProvider getBusinessInstanceProvider() {
    return new BusinessRuleInstanceProviderImpl();
  }

  @Override
  public IDomainModelInstanceProvider getDomainInstanceProvider() {
    return new DomainModelInstanceProviderImpl();
  }
}
