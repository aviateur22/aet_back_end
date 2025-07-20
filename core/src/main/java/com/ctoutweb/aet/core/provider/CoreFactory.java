package com.ctoutweb.aet.core.provider;

import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.provider.IBusinessInstanceProvider;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.provider.IDomainModelInstanceProvider;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.provider.IMemoryGameInstanceProvider;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.provider.impl.MemoryCardInstanceProviderImpl;

public class CoreFactory {
  private static final IMemoryGameInstanceProvider MEMORY_CARD_INSTANCE_PROVIDER_HOLDER = new MemoryCardInstanceProviderImpl();

  public static final IBusinessInstanceProvider MEMORY_CARD_BUSINESS_INSTANCE_PROVIDER = MEMORY_CARD_INSTANCE_PROVIDER_HOLDER.getBusinessInstanceProvider();
  public static final IDomainModelInstanceProvider MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER = MEMORY_CARD_INSTANCE_PROVIDER_HOLDER.getDomainInstanceProvider();
}
