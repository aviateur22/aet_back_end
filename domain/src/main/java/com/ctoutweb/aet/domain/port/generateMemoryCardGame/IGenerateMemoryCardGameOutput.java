package com.ctoutweb.aet.domain.port.generateMemoryCardGame;

import com.ctoutweb.aet.domain.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.Card;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.CardToFind;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.GameLevel;

public interface IGenerateMemoryCardGameOutput {
  IGameTextInformation getGameTextInformation();
  CardToFind getCardToFindInGame();
  Card[] getCards();
  GameLevel getGameLevel();
  int getTimeToObserveBeforeStart();
  int getCardToFindQuantity();
  int getMaxErrorQuantity();
  int getTimeInSecToFinish();
}
