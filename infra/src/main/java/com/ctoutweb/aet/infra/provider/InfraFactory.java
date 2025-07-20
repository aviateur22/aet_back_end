package com.ctoutweb.aet.infra.provider;


import com.ctoutweb.aet.infra.provider.image.ImageInstanceProvider;
import com.ctoutweb.aet.infra.provider.image.ImageInstanceProviderImpl;
import com.ctoutweb.aet.infra.provider.memoryCardGame.IMemoryCardInstanceProvider;
import com.ctoutweb.aet.infra.provider.memoryCardGame.MemoryCardInstanceProviderImpl;
public class InfraFactory {

  /**
   * MemoryCard Instance provider
   */

  private static final IMemoryCardInstanceProvider MEMORY_CARD_INSTANCE_PROVIDER_HOLDER = new MemoryCardInstanceProviderImpl();

  public static final IMemoryCardInstanceProvider INFRA_MEMORY_CARD_INSTANCE_PROVIDER = MEMORY_CARD_INSTANCE_PROVIDER_HOLDER;

  /**
   * Image Instance Provider
   */
  private static final ImageInstanceProvider IMAGE_INSTANCE_PROVIDER_HOLDER = new ImageInstanceProviderImpl();
  public static final ImageInstanceProvider IMAGE_INSTANCE_PROVIDER = IMAGE_INSTANCE_PROVIDER_HOLDER;
}
