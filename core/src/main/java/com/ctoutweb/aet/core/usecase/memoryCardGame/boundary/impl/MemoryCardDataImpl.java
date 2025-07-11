package com.ctoutweb.aet.core.usecase.memoryCardGame.boundary.impl;

import com.ctoutweb.aet.core.entity.GameTextInformation;
import com.ctoutweb.aet.core.entity.memoryCardGame.Card;
import com.ctoutweb.aet.core.entity.memoryCardGame.CardToFind;
import com.ctoutweb.aet.core.entity.memoryCardGame.GameLevel;
import com.ctoutweb.aet.core.usecase.memoryCardGame.boundary.IOutputBoundary;

public class MemoryCardDataImpl implements IOutputBoundary {
  private GameTextInformation gameTextInformation;
  private CardToFind cardToFindInGame;
  private Card[] cards;
  private GameLevel gameLevel;
  private short timeToObserveBeforeStart;
  private short cardToFindQuantity;
  private short errorQuantity;
  @Override
  public GameTextInformation getGameTextInformation() {
    return null;
  }

  @Override
  public CardToFind getCardToFindInGame() {
    return null;
  }

  @Override
  public Card[] getCards() {
    return new Card[0];
  }

  @Override
  public GameLevel getGameLevel() {
    return null;
  }

  @Override
  public short getTimeToObserveBeforeStart() {
    return 0;
  }

  @Override
  public short getCardToFindQuantity() {
    return 0;
  }

  @Override
  public short getMaxErrorQuantity() {
    return 0;
  }

  @Override
  public void setCardToFindQuantity(short quantity) {
    this.cardToFindQuantity = quantity;
  }

  @Override
  public void setGameTextInformation(GameTextInformation gameTextInformation) {
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
  public void setTimeToObserveBeforeStart(short timeToObserveBeforeStart) {
    this.timeToObserveBeforeStart = timeToObserveBeforeStart;
  }

  @Override
  public void setMaxErrorQuantity(short maxErrorQuantity) {
    this.errorQuantity = maxErrorQuantity;
  }
}
