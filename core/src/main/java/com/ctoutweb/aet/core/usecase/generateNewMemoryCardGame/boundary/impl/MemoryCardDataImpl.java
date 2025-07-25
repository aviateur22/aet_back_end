package com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.boundary.impl;

import com.ctoutweb.aet.core.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.core.entity.memoryCardGame.Card;
import com.ctoutweb.aet.core.entity.memoryCardGame.CardToFind;
import com.ctoutweb.aet.core.entity.memoryCardGame.GameLevel;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.boundary.IGenerateNewGameResponse;

public class MemoryCardDataImpl implements IGenerateNewGameResponse {
  private IGameTextInformation gameTextInformation;
  private CardToFind cardToFindInGame;
  private Card[] cards;
  private GameLevel gameLevel;
  private int timeToObserveBeforeStart;
  private int cardToFindQuantity;
  private int errorQuantity;
  private int timeInSecToFinish;

  public MemoryCardDataImpl(
          IGameTextInformation gameTextInformation,
          CardToFind cardToFindInGame, Card[] cards,
          GameLevel gameLevel,
          int timeToObserveBeforeStart,
          int cardToFindQuantity,
          int errorQuantity,
          int timeInSecToFinish) {
    this.gameTextInformation = gameTextInformation;
    this.cardToFindInGame = cardToFindInGame;
    this.cards = cards;
    this.gameLevel = gameLevel;
    this.timeToObserveBeforeStart = timeToObserveBeforeStart;
    this.cardToFindQuantity = cardToFindQuantity;
    this.errorQuantity = errorQuantity;
    this.timeInSecToFinish = timeInSecToFinish;
  }

  @Override
  public IGameTextInformation getGameTextInformation() {
    return gameTextInformation;
  }

  @Override
  public CardToFind getCardToFindInGame() {
    return cardToFindInGame;
  }

  @Override
  public Card[] getCards() {
    return cards;
  }

  @Override
  public GameLevel getGameLevel() {
    return gameLevel;
  }

  @Override
  public int getTimeToObserveBeforeStart() {
    return timeToObserveBeforeStart;
  }

  @Override
  public int getCardToFindQuantity() {
    return cardToFindQuantity;
  }

  @Override
  public int getMaxErrorQuantity() {
    return errorQuantity;
  }

  @Override
  public int getTimeInSecToFinish() {
    return timeInSecToFinish;
  }
}
