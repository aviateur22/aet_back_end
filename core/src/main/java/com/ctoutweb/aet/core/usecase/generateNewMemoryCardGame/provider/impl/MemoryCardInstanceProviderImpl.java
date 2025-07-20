package com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.provider.impl;

import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.provider.IBusinessInstanceProvider;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.provider.IDomainModelInstanceProvider;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.provider.IMemoryGameInstanceProvider;

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
