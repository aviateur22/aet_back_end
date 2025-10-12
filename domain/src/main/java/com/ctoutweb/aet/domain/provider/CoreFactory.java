package com.ctoutweb.aet.domain.provider;

import com.ctoutweb.aet.domain.provider.common.CommonProvider;
import com.ctoutweb.aet.domain.provider.common.ICommonInstanceProvider;
import com.ctoutweb.aet.domain.provider.generateMemoryCardGame.IBusinessInstanceProvider;
import com.ctoutweb.aet.domain.provider.generateMemoryCardGame.IDomainModelInstanceProvider;
import com.ctoutweb.aet.domain.provider.generateMemoryCardGame.IMemoryGameInstanceProvider;
import com.ctoutweb.aet.domain.provider.generateMemoryCardGame.impl.MemoryCardInstanceProviderImpl;
import com.ctoutweb.aet.domain.provider.generateMentalCalculGame.IMentalCalculDomainInstanceProvider;
import com.ctoutweb.aet.domain.provider.generateMentalCalculGame.impl.MentalCalculDomainInstanceProviderImpl;

public class CoreFactory {
  private static final IMemoryGameInstanceProvider MEMORY_CARD_INSTANCE_PROVIDER_HOLDER = new MemoryCardInstanceProviderImpl();

  public static final IBusinessInstanceProvider MEMORY_CARD_BUSINESS_INSTANCE_PROVIDER = MEMORY_CARD_INSTANCE_PROVIDER_HOLDER.getBusinessInstanceProvider();
  public static final IDomainModelInstanceProvider MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER = MEMORY_CARD_INSTANCE_PROVIDER_HOLDER.getDomainInstanceProvider();

  public static final IMentalCalculDomainInstanceProvider MENTAL_CALCUL_INSTANCE_PROVIDER = new MentalCalculDomainInstanceProviderImpl();
  public static final ICommonInstanceProvider COMMON_INSTANCE_PROVIDER = new CommonProvider();
}
