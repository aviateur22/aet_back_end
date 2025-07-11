package com.ctoutweb.aet.core.provider.prototype;

import com.ctoutweb.aet.core.entity.memoryCardGame.Card;
import com.ctoutweb.aet.core.entity.memoryCardGame.CardToFind;
import com.ctoutweb.aet.core.entity.memoryCardGame.ICardImage;

public interface IMemoryCardGameProvider {
  CardToFind provideCardToFind(ICardImage cardToFindInGame, String cardPresentation);
  Card provideCard(ICardImage cardImage, boolean isCardToFind);
}
