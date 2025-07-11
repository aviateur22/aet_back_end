package com.ctoutweb.aet.core.entity.memoryCardGame;

import com.ctoutweb.aet.core.provider.CoreFactoryProvider;

public enum GameLevel {
  EASY(CoreFactoryProvider.getCoreFactory().getBornRangeImpl(2,5), CoreFactoryProvider.getCoreFactory().getBornRangeImpl(9, 13)),
  MEDIUM(CoreFactoryProvider.getCoreFactory().getBornRangeImpl(4,7), CoreFactoryProvider.getCoreFactory().getBornRangeImpl(12,18)),
  DIFFICULT(CoreFactoryProvider.getCoreFactory().getBornRangeImpl(6, 10), CoreFactoryProvider.getCoreFactory().getBornRangeImpl(15, 25));

  private IBornRange cardsQuantityToFindBorn;
  private IBornRange  cardsQuantityInGameBorn;

  private GameLevel(IBornRange cardsQuantityToFindBorn, IBornRange cardsQauntityInGameBorn) {
    this.cardsQuantityInGameBorn = cardsQauntityInGameBorn;
    this.cardsQuantityToFindBorn = cardsQuantityToFindBorn;
  }

  public IBornRange getCardsQuantityToFindBorn() {
    return this.cardsQuantityToFindBorn;
  }

  public IBornRange getCardsQuantityInGameBorn() {
    return this.cardsQuantityInGameBorn;
  }

}
