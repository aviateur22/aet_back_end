package com.ctoutweb.aet.core.usecase.memoryCardGame.provider;

public interface IMemoryGameInstanceProvider {
  IDomainModelInstanceProvider getDomainInstanceProvider();

  IBusinessInstanceProvider getBusinessInstanceProvider();
}
