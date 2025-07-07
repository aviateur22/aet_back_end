package com.ctoutweb.aet.entity.memoryCardGame;

import com.ctoutweb.aet.factory.InstanceLoader;

public enum GameLevel {
  EASY(InstanceLoader.getCoreFactory().getBornRangeImpl(2,5), InstanceLoader.getCoreFactory().getBornRangeImpl(9, 13)),
  MEDIUM(InstanceLoader.getCoreFactory().getBornRangeImpl(4,7),InstanceLoader.getCoreFactory().getBornRangeImpl(12,18)),
  DIFFICULT(InstanceLoader.getCoreFactory().getBornRangeImpl(6, 10), InstanceLoader.getCoreFactory().getBornRangeImpl(15, 25));

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
