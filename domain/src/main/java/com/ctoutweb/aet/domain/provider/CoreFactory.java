package com.ctoutweb.aet.domain.provider;

import com.ctoutweb.aet.domain.provider.generateMemoryCardGame.IBusinessInstanceProvider;
import com.ctoutweb.aet.domain.provider.generateMemoryCardGame.IDomainModelInstanceProvider;
import com.ctoutweb.aet.domain.provider.generateMemoryCardGame.IMemoryGameInstanceProvider;
import com.ctoutweb.aet.domain.provider.generateMemoryCardGame.impl.MemoryCardInstanceProviderImpl;

public class CoreFactory {
  private static final IMemoryGameInstanceProvider MEMORY_CARD_INSTANCE_PROVIDER_HOLDER = new MemoryCardInstanceProviderImpl();

  public static final IBusinessInstanceProvider MEMORY_CARD_BUSINESS_INSTANCE_PROVIDER = MEMORY_CARD_INSTANCE_PROVIDER_HOLDER.getBusinessInstanceProvider();
  public static final IDomainModelInstanceProvider MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER = MEMORY_CARD_INSTANCE_PROVIDER_HOLDER.getDomainInstanceProvider();
}
