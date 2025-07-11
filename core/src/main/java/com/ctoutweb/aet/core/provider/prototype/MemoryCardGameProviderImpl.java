package com.ctoutweb.aet.core.provider.prototype;

import com.ctoutweb.aet.core.entity.memoryCardGame.Card;
import com.ctoutweb.aet.core.entity.memoryCardGame.CardToFind;
import com.ctoutweb.aet.core.entity.memoryCardGame.ICardImage;

public class MemoryCardGameProviderImpl implements IMemoryCardGameProvider {
  @Override
  public CardToFind provideCardToFind(ICardImage cardToFindInGame, String cardPresentation) {
    return new CardToFind(cardPresentation, cardToFindInGame);
  }

  @Override
  public Card provideCard(ICardImage cardImage, boolean isCardToFind) {
    return new Card(cardImage, isCardToFind);
  }


}
