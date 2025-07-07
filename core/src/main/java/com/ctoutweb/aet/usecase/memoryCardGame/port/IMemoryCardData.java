package com.ctoutweb.aet.usecase.memoryCardGame.port;

import com.ctoutweb.aet.entity.memoryCardGame.GameLevel;
import com.ctoutweb.aet.entity.GameTextInformation;
import com.ctoutweb.aet.entity.memoryCardGame.Card;
import com.ctoutweb.aet.entity.memoryCardGame.CardToFind;

public interface IMemoryCardData {
  GameTextInformation getGameTextInformation();
  CardToFind getCardToFindInGame();
  Card[] getCards();
  GameLevel getGameLevel();
  short getTimeToObserveBeforeStart();
  short getCardToFindQuantity();
  short getMaxErrorQuantity();
}
