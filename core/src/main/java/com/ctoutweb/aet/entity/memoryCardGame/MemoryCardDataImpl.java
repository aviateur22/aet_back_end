package com.ctoutweb.aet.entity.memoryCardGame;

import com.ctoutweb.aet.entity.GameTextInformation;
import com.ctoutweb.aet.entity.memoryCardGame.rule.MemoryCardGameRules;
import com.ctoutweb.aet.usecase.memoryCardGame.port.IMemoryCardData;

public record MemoryCardDataImpl(
   MemoryCardGameRules memoryCardGameRules

) implements IMemoryCardData {
  @Override
  public GameTextInformation getGameTextInformation() {
    return null;
  }

  @Override
  public CardToFind getCardToFindInGame() {
    return null;
  }

  @Override
  public Card[] getCards() {
    return new Card[0];
  }

  @Override
  public GameLevel getGameLevel() {
    return null;
  }

  @Override
  public short getTimeToObserveBeforeStart() {
    return 0;
  }

  @Override
  public short getCardToFindQuantity() {
    return 0;
  }

  @Override
  public short getMaxErrorQuantity() {
    return 0;
  }
}
