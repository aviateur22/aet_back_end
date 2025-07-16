package com.ctoutweb.aet.core.entity.memoryCardGame.impl;

import com.ctoutweb.aet.core.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.core.entity.memoryCardGame.Card;
import com.ctoutweb.aet.core.entity.memoryCardGame.CardToFind;
import com.ctoutweb.aet.core.entity.memoryCardGame.GameLevel;
import com.ctoutweb.aet.core.entity.memoryCardGame.IGameCardData;

public class GameCardDataImpl implements IGameCardData {
  private IGameTextInformation gameTextInformation;
  private CardToFind cardToFindInGame;
  private Card[] cards;
  private GameLevel gameLevel;
  private short timeToObserveBeforeStart;
  private short cardToFindQuantity;
  private short errorQuantity;
  private int timeInSecToFinish;

  @Override
  public void setCardToFindQuantity(short quantity) {
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
  public void setTimeToObserveBeforeStart(short timeToObserveBeforeStart) {
    this.timeToObserveBeforeStart = timeToObserveBeforeStart;
  }

  @Override
  public void setMaxErrorQuantity(short maxErrorQuantity) {
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
  public short getTimeToObserveBeforeStart() {
    return this.timeToObserveBeforeStart;
  }

  @Override
  public short getCardToFindQuantity() {
    return this.cardToFindQuantity;
  }

  @Override
  public short getMaxErrorQuantity() {
    return this.errorQuantity;
  }

  @Override
  public int getTimeInSecToFinish() {
    return this.timeInSecToFinish;
  }
}
