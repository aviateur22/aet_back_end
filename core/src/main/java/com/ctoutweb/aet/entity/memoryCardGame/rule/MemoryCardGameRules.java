package com.ctoutweb.aet.entity.memoryCardGame.rule;

import com.ctoutweb.aet.entity.memoryCardGame.GameLevel;
import com.ctoutweb.aet.entity.GameTextInformation;
import com.ctoutweb.aet.entity.memoryCardGame.Card;
import com.ctoutweb.aet.entity.memoryCardGame.CardToFind;
import com.ctoutweb.aet.entity.memoryCardGame.IBornRange;
import com.ctoutweb.aet.factory.CoreFactory;
import com.ctoutweb.aet.factory.InstanceLoader;
import com.ctoutweb.aet.entity.base.RuleBase;
import com.ctoutweb.aet.usecase.memoryCardGame.port.IMemoryCardData;
import com.ctoutweb.aet.util.NumberUtil;

public class MemoryCardGameRules extends RuleBase {
  CoreFactory factory = InstanceLoader.getCoreFactory();

  private static final short TIME_TO_OBSERVE_BEFORE_START = 9;
  private short cardQuantity;
  private Card[] cards;
  private GameTextInformation gameTextInformation;
  private CardToFind cardToFindInGame;
  private GameLevel gameLevel;
  private short timeToObserveBeforeStart;
  private short maxErrorQuantity;
  private short cardToFindQuantity;

  @Override
  public MemoryCardGameRules initialize() {
    this.cardQuantity = 0;
    this.cards = null;
    this.gameTextInformation = null;
    this.cardToFindInGame = null;
    this.gameLevel = null;
    this.timeToObserveBeforeStart = 0;
    this.maxErrorQuantity = 0;
    this.cardToFindQuantity = 0;

    return  this;
  }
  public MemoryCardGameRules getCardToFindQuantityInGame(GameLevel level) {
    IBornRange cardToFindQuantityBorn = level.getCardsQuantityToFindBorn();
    this.cardToFindQuantity =(short) NumberUtil.generateRandomNumberBetweenMinAndMax(
            cardToFindQuantityBorn.getMin(),
            cardToFindQuantityBorn.getMax());
    return this;
  }

  public MemoryCardGameRules getTimeToObserveBeforeStart() {
    this.timeToObserveBeforeStart = TIME_TO_OBSERVE_BEFORE_START;
    return this;
  }
  public MemoryCardGameRules getBackOfCardPath() {
    return null;
  }
  public MemoryCardGameRules getCardToFindInGame() {

    return null;
  }

  public MemoryCardGameRules generateRandomCardsForGame(GameLevel level) {
    return null;
  }

  public MemoryCardGameRules getMaxErrorInGame() {
    return null;
  }

  public IMemoryCardData getData() {
    return this.factory.getMemoryCardDataImpl(this);
  }

  ///////////////////////////////////////////////////


  public CoreFactory getFactory() {
    return factory;
  }

  public short getCardQuantity() {
    return cardQuantity;
  }

  public Card[] getCards() {
    return cards;
  }

  public GameTextInformation getGameTextInformation() {
    return gameTextInformation;
  }

  public GameLevel getGameLevel() {
    return gameLevel;
  }

  public short getMaxErrorQuantity() {
    return maxErrorQuantity;
  }

  public short getCardToFindQuantity() {
    return cardToFindQuantity;
  }
}
