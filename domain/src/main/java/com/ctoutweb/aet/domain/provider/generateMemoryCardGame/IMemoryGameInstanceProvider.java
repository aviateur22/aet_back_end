package com.ctoutweb.aet.domain.provider.generateMemoryCardGame;

public interface IMemoryGameInstanceProvider {
  IDomainModelInstanceProvider getDomainInstanceProvider();

  IBusinessInstanceProvider getBusinessInstanceProvider();
}
