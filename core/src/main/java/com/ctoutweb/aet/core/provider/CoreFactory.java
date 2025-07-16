package com.ctoutweb.aet.core.provider;

import com.ctoutweb.aet.core.usecase.memoryCardGame.provider.IBusinessInstanceProvider;
import com.ctoutweb.aet.core.usecase.memoryCardGame.provider.IDomainModelInstanceProvider;
import com.ctoutweb.aet.core.usecase.memoryCardGame.provider.IMemoryGameInstanceProvider;
import com.ctoutweb.aet.core.usecase.memoryCardGame.provider.impl.MemoryCardInstanceProviderImpl;

public class CoreFactory {
  private static final IMemoryGameInstanceProvider MEMORY_CARD_INSTANCE_PROVIDER_HOLDER = new MemoryCardInstanceProviderImpl();

  public static final IBusinessInstanceProvider MEMORY_CARD_BUSINESS_INSTANCE_PROVIDER = MEMORY_CARD_INSTANCE_PROVIDER_HOLDER.getBusinessInstanceProvider();
  public static final IDomainModelInstanceProvider MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER = MEMORY_CARD_INSTANCE_PROVIDER_HOLDER.getDomainInstanceProvider();
}
