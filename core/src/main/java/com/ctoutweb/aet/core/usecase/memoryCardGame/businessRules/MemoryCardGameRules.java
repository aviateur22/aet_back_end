package com.ctoutweb.aet.core.usecase.memoryCardGame.businessRules;

import com.ctoutweb.aet.core.entity.memoryCardGame.*;
import com.ctoutweb.aet.core.provider.prototype.IMemoryCardGameProvider;
import com.ctoutweb.aet.core.usecase.memoryCardGame.port.IPortMemoryCardService;
import com.ctoutweb.aet.core.util.ArrayUtil;
import com.ctoutweb.aet.core.util.NumberUtil;
import com.ctoutweb.aet.core.provider.CoreFactory;
import com.ctoutweb.aet.core.provider.CoreFactoryProvider;
import com.ctoutweb.aet.core.usecase.base.RuleBase;
import com.ctoutweb.aet.core.usecase.memoryCardGame.boundary.IOutputBoundary;

import java.util.Arrays;

import static com.ctoutweb.aet.core.usecase.memoryCardGame.businessRules.MemoryCardGameInformation.CARD_TO_FIND_PRESENTATION;
import static com.ctoutweb.aet.core.usecase.memoryCardGame.businessRules.MemoryCardGameInformation.TIME_TO_OBSERVE_BEFORE_START;

public class MemoryCardGameRules extends RuleBase {
  CoreFactory factory = CoreFactoryProvider.getCoreFactory();
  private final IMemoryCardGameProvider provider = factory.getMemoryCardGameProviderImpl();
  private final IPortMemoryCardService portMemoryCardService;
  private String selectedBackImagePath;
  private String[] frontImagePathAvailableList;
  private IOutputBoundary memoryCardData;

  public MemoryCardGameRules(IPortMemoryCardService portMemoryCardService) {
    this.portMemoryCardService = portMemoryCardService;
  }

  @Override
  public MemoryCardGameRules initialize() {
    this.memoryCardData = factory.getMemoryCardDataImpl();

    return  this;
  }
  public MemoryCardGameRules getCardToFindQuantity(GameLevel level) {
    IBornRange cardToFindQuantityBorn = level.getCardsQuantityToFindBorn();
    short quantity = (short) NumberUtil.generateRandomNumberBetweenMinAndMax(
            cardToFindQuantityBorn.getMin(),
            cardToFindQuantityBorn.getMax());

    this.memoryCardData.setCardToFindQuantity(quantity);

    return this;
  }

  public MemoryCardGameRules getCardsQuantityInGame() {


    return this;
  }

  public MemoryCardGameRules getTimeToObserveBeforeStart() {
    this.memoryCardData.setTimeToObserveBeforeStart(TIME_TO_OBSERVE_BEFORE_START);
    return this;
  }
  public MemoryCardGameRules selectOneBackImagePath() {
    this.selectedBackImagePath = ArrayUtil.selectOneRandomItem(this.portMemoryCardService.getAllAvailableCardPaths(CardFace.BACK_FACE)).getItem();
    return this;
  }

  public MemoryCardGameRules getAllFrontImagePathForCards() {
     this.frontImagePathAvailableList = this.portMemoryCardService.getAllAvailableCardPaths(CardFace.FRONT_FACE);
    return this;
  }
  public MemoryCardGameRules selectCardToFindInGame() {
    // Melange les paths des images de la face avant des cartes
    ArrayUtil.shuffle(this.frontImagePathAvailableList);

    // Selection d'une face avant
    var frontImageTofind = ArrayUtil.selectOneRandomItem(this.frontImagePathAvailableList);

    // Carte a trouver dans le jeu
    ICardImage cardToFind = factory.getCardImageImpl(frontImageTofind.getItem(), this.selectedBackImagePath);

    // Text de presentation de la carte
    String cardToFindDescription = CARD_TO_FIND_PRESENTATION;

    // La carte à trouver
    CardToFind cardTofindInGame = this.provider.provideCardToFind(cardToFind, cardToFindDescription);

    memoryCardData.setCardToFindInGame(cardTofindInGame);

    return this;
  }

  public MemoryCardGameRules selectCardsForGame(GameLevel level) {
    this.selectedBackImagePath = ArrayUtil.selectOneRandomItem(this.portMemoryCardService.getAllAvailableCardPaths(CardFace.BACK_FACE)).getItem();
    this.frontImagePathAvailableList = this.portMemoryCardService.getAllAvailableCardPaths(CardFace.FRONT_FACE);

    // Melange les paths des images de la face avant des cartes
    ArrayUtil.shuffle(this.frontImagePathAvailableList);

    // Selection d'une face avant
    var frontImageTofind = ArrayUtil.selectOneRandomItem(this.frontImagePathAvailableList);

    // Carte a trouver dans le jeu
    ICardImage cardToFind = factory.getCardImageImpl(frontImageTofind.getItem(), this.selectedBackImagePath);
    int imageTofindIndex = frontImageTofind.getIndex();

    // Text de presentation de la carte
    String cardToFindDescription = CARD_TO_FIND_PRESENTATION;

    // La carte à trouver
    CardToFind cardTofindInGame = this.provider.provideCardToFind(cardToFind, cardToFindDescription);

    // Liste des cartes a trouvers dans le jeu
    Card[] cardToFindList = Arrays.stream(ArrayUtil.selectSameItemMultipleTime(
              this.frontImagePathAvailableList,
              imageTofindIndex,
              this.memoryCardData.getCardToFindQuantity()
            ))
            .map(path -> factory.getCardImageImpl(path, this.selectedBackImagePath))
            .map(image -> provider.provideCard(image, true))
            .toArray(Card[]::new);


    frontImagePathAvailableList = ArrayUtil.removeItem(this.frontImagePathAvailableList, imageTofindIndex);

    memoryCardData.setCardToFindInGame(cardTofindInGame);

    ArrayUtil.shuffle(this.frontImagePathAvailableList);

    IBornRange cardsQuantityBorn = level.getCardsQuantityInGameBorn();

    short quantity = NumberUtil.generateRandomNumberBetweenMinAndMax(
            cardsQuantityBorn.getMin(),
            cardsQuantityBorn.getMax());

    Card[] randomCardsList = Arrays.stream(ArrayUtil.selectMultipleRandomItem(
            this.frontImagePathAvailableList,
            quantity - this.memoryCardData.getCardToFindQuantity()))
            .map(path -> factory.getCardImageImpl(path, this.selectedBackImagePath))
            .map(image -> provider.provideCard(image, false))
            .toArray(Card[]::new);

    Card[] allCards = ArrayUtil.combineArrays(cardToFindList, randomCardsList);

    this.memoryCardData.setCards(allCards);

    return this;
  }

  public MemoryCardGameRules getMaxErrorInGame() {
    return null;
  }

  public IOutputBoundary getData() {
    return this.memoryCardData;
  }
}
