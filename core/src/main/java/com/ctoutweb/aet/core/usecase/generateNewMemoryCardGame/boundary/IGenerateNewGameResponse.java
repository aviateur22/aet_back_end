package com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.boundary;

import com.ctoutweb.aet.core.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.core.entity.memoryCardGame.Card;
import com.ctoutweb.aet.core.entity.memoryCardGame.CardToFind;
import com.ctoutweb.aet.core.entity.memoryCardGame.GameLevel;

public interface IGenerateNewGameResponse {
  IGameTextInformation getGameTextInformation();
  CardToFind getCardToFindInGame();
  Card[] getCards();
  GameLevel getGameLevel();
  short getTimeToObserveBeforeStart();
  short getCardToFindQuantity();
  short getMaxErrorQuantity();
  int getTimeInSecToFinish();
}
