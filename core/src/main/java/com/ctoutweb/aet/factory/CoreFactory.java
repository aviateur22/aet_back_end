package com.ctoutweb.aet.factory;

import com.ctoutweb.aet.entity.memoryCardGame.ICardImage;
import com.ctoutweb.aet.entity.memoryCardGame.impl.BornRangeImpl;
import com.ctoutweb.aet.entity.memoryCardGame.MemoryCardDataImpl;
import com.ctoutweb.aet.entity.memoryCardGame.IBornRange;
import com.ctoutweb.aet.entity.memoryCardGame.impl.CardImageImpl;
import com.ctoutweb.aet.entity.memoryCardGame.rule.MemoryCardGameRules;
import com.ctoutweb.aet.usecase.memoryCardGame.port.IMemoryCardData;

public class CoreFactory {
  public IMemoryCardData getMemoryCardDataImpl(MemoryCardGameRules memoryCardGameRules) {
    return new MemoryCardDataImpl(memoryCardGameRules);
  }

  public IBornRange getBornRangeImpl(int min, int max) {
    return new BornRangeImpl(min, max);
  }
  public ICardImage getCardImageImpl(String cardFrontImagePath, String cardBackImagePath) {
    return new CardImageImpl(cardFrontImagePath, cardBackImagePath);
  }
}
