package com.ctoutweb.aet.infra.provider;


import com.ctoutweb.aet.infra.provider.memoryCardGame.IMemoryCardInstanceProvider;
import com.ctoutweb.aet.infra.provider.memoryCardGame.MemoryCardInstanceProviderImpl;
public class InfraFactory {

  private static final IMemoryCardInstanceProvider MEMORY_CARD_INSTANCE_PROVIDER_HOLDER = new MemoryCardInstanceProviderImpl();

  public static final IMemoryCardInstanceProvider INFRA_MEMORY_CARD_INSTANCE_PROVIDER = MEMORY_CARD_INSTANCE_PROVIDER_HOLDER;
}
