package com.ctoutweb.aet.core.usecase.memoryCardGame.boundary;

import com.ctoutweb.aet.core.entity.GameTextInformation;
import com.ctoutweb.aet.core.entity.memoryCardGame.Card;
import com.ctoutweb.aet.core.entity.memoryCardGame.CardToFind;
import com.ctoutweb.aet.core.entity.memoryCardGame.GameLevel;

public interface IOutputBoundary {
  GameTextInformation getGameTextInformation();
  CardToFind getCardToFindInGame();
  Card[] getCards();
  GameLevel getGameLevel();
  short getTimeToObserveBeforeStart();
  short getCardToFindQuantity();
  short getMaxErrorQuantity();
  void setCardToFindQuantity(short quantity);
  void setGameTextInformation(GameTextInformation gameTextInformation);
  void setCardToFindInGame(CardToFind cardToFind);
  void setCards(Card[] cards);
  void setGameLevel(GameLevel gameLevel);
  void setTimeToObserveBeforeStart(short timeToObserveBeforeStart);
  void setMaxErrorQuantity(short maxErrorQuantity);

}
