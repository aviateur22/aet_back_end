package com.ctoutweb.aet.domain.entity.generateMemoryCardGame.impl;

import com.ctoutweb.aet.domain.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.Card;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.CardToFind;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.GameLevel;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.IGameCardData;

public class GameCardDataImpl implements IGameCardData {
  private IGameTextInformation gameTextInformation;
  private CardToFind cardToFindInGame;
  private Card[] cards;
  private GameLevel gameLevel;
  private int timeToObserveBeforeStart;
  private int cardToFindQuantity;
  private int errorQuantity;
  private int timeInSecToFinish;

  @Override
  public void setCardToFindQuantity(int quantity) {
    this.cardToFindQuantity = quantity;
  }

  @Override
  public void setGameTextInformation(IGameTextInformation gameTextInformation) {
    this.gameTextInformation = gameTextInformation;
  }

  @Override
  public void setCardToFindInGame(CardToFind cardToFind) {
    this.cardToFindInGame = cardToFind;
  }

  @Override
  public void setCards(Card[] cards) {
    this.cards = cards;
  }

  @Override
  public void setGameLevel(GameLevel gameLevel) {
    this.gameLevel = gameLevel;
  }

  @Override
  public void setTimeToObserveBeforeStart(int timeToObserveBeforeStart) {
    this.timeToObserveBeforeStart = timeToObserveBeforeStart;
  }

  @Override
  public void setMaxErrorQuantity(int maxErrorQuantity) {
    this.errorQuantity = maxErrorQuantity;
  }

  @Override
  public void setTimeInSecToFinish(int timeInSecToFinish) {
    this.timeInSecToFinish = timeInSecToFinish;
  }

  @Override
  public IGameTextInformation getGameTextInformation() {
    return this.gameTextInformation;
  }

  @Override
  public CardToFind getCardToFindInGame() {
    return this.cardToFindInGame;
  }

  @Override
  public Card[] getCards() {
    return this.cards;
  }

  @Override
  public GameLevel getGameLevel() {
    return this.gameLevel;
  }

  @Override
  public int getTimeToObserveBeforeStart() {
    return this.timeToObserveBeforeStart;
  }

  @Override
  public int getCardToFindQuantity() {
    return this.cardToFindQuantity;
  }

  @Override
  public int getMaxErrorQuantity() {
    return this.errorQuantity;
  }

  @Override
  public int getTimeInSecToFinish() {
    return this.timeInSecToFinish;
  }
}
