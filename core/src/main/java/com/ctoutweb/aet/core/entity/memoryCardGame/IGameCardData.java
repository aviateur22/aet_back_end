package com.ctoutweb.aet.core.entity.memoryCardGame;

import com.ctoutweb.aet.core.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.boundary.IGenerateNewGameResponse;

/**
 * Utilisé pour mettre a jour les données lors de la création d'un jeu
 */
public interface IGameCardData extends IGenerateNewGameResponse {
  void setCardToFindQuantity(int quantity);
  void setGameTextInformation(IGameTextInformation gameTextInformation);
  void setCardToFindInGame(CardToFind cardToFind);
  void setCards(Card[] cards);
  void setGameLevel(GameLevel gameLevel);
  void setTimeToObserveBeforeStart(int timeToObserveBeforeStart);
  void setMaxErrorQuantity(int maxErrorQuantity);
  void setTimeInSecToFinish(int timeInSecToFinish);
}
