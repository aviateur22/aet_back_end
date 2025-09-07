package com.ctoutweb.aet.domain.provider.generateMemoryCardGame.impl;

import com.ctoutweb.aet.domain.provider.generateMemoryCardGame.IBusinessInstanceProvider;
import com.ctoutweb.aet.domain.provider.generateMemoryCardGame.IDomainModelInstanceProvider;
import com.ctoutweb.aet.domain.provider.generateMemoryCardGame.IMemoryGameInstanceProvider;

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
