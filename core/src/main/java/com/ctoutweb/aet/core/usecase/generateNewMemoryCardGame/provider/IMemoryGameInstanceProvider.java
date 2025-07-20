package com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.provider;

public interface IMemoryGameInstanceProvider {
  IDomainModelInstanceProvider getDomainInstanceProvider();

  IBusinessInstanceProvider getBusinessInstanceProvider();
}
