package com.ctoutweb.aet.core.provider;

import com.ctoutweb.aet.core.annotation.CoreService;
import com.ctoutweb.aet.core.entity.memoryCardGame.IBornRange;
import com.ctoutweb.aet.core.provider.prototype.IMemoryCardGameProvider;
import com.ctoutweb.aet.core.provider.prototype.MemoryCardGameProviderImpl;
import com.ctoutweb.aet.core.provider.singleton.IMemoryCardRulesProvider;
import com.ctoutweb.aet.core.provider.singleton.MemoryCardRulesProvider;
import com.ctoutweb.aet.core.usecase.memoryCardGame.boundary.impl.MemoryCardDataImpl;
import com.ctoutweb.aet.core.entity.memoryCardGame.impl.BornRangeImpl;
import com.ctoutweb.aet.core.entity.memoryCardGame.impl.CardImageImpl;
import com.ctoutweb.aet.core.entity.memoryCardGame.ICardImage;
import com.ctoutweb.aet.core.usecase.memoryCardGame.boundary.IOutputBoundary;

@CoreService
public class CoreFactory {
  public IMemoryCardGameProvider getMemoryCardGameProviderImpl() {
    return new MemoryCardGameProviderImpl();
  }
  public IMemoryCardRulesProvider getMemoryCardRulesProvider() {
    return new MemoryCardRulesProvider();
  }
  public IOutputBoundary getMemoryCardDataImpl() {
    return new MemoryCardDataImpl();
  }
  public IBornRange getBornRangeImpl(int min, int max) {
    return new BornRangeImpl(min, max);
  }
  public ICardImage getCardImageImpl(String cardFrontImagePath, String cardBackImagePath) {
    return new CardImageImpl(cardFrontImagePath, cardBackImagePath);
  }
}
