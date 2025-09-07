package com.ctoutweb.aet.domain.entity.generateMemoryCardGame;

import com.ctoutweb.aet.domain.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.domain.port.generateMemoryCardGame.IGenerateMemoryCardGameOutput;

/**
 * Utilisé pour mettre a jour les données lors de la création d'un jeu
 */
public interface IGameCardData extends IGenerateMemoryCardGameOutput {
  void setCardToFindQuantity(int quantity);
  void setGameTextInformation(IGameTextInformation gameTextInformation);
  void setCardToFindInGame(CardToFind cardToFind);
  void setCards(Card[] cards);
  void setGameLevel(GameLevel gameLevel);
  void setTimeToObserveBeforeStart(int timeToObserveBeforeStart);
  void setMaxErrorQuantity(int maxErrorQuantity);
  void setTimeInSecToFinish(int timeInSecToFinish);
}
