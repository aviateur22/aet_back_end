package com.ctoutweb.aet.core.usecase.memoryCardGame.businessRules;

import com.ctoutweb.aet.core.entity.memoryCardGame.*;
import com.ctoutweb.aet.core.exception.ImageException;
import com.ctoutweb.aet.core.provider.CoreFactory;
import com.ctoutweb.aet.core.usecase.memoryCardGame.port.IGenerateNewGameGateway;
import com.ctoutweb.aet.core.util.ArrayUtil;
import com.ctoutweb.aet.core.util.NumberUtil;
import com.ctoutweb.aet.core.usecase.base.RuleBase;
import com.ctoutweb.aet.core.usecase.memoryCardGame.boundary.IGenerateNewGameResponse;

import java.util.Arrays;

import static com.ctoutweb.aet.core.usecase.memoryCardGame.gameParameter.GameData.*;

public class MemoryCardGameRules extends RuleBase {
  private final IGenerateNewGameGateway portMemoryCardService;
  private String selectedBackImagePath;
  private String[] frontImagePathAvailableList;
  private final GameLevel gameLevel;
  private final ParameterState parameterState;
  private IGameCardData memoryCardSetter;

  public MemoryCardGameRules(
          IGenerateNewGameGateway portMemoryCardService,
          GameLevel gameLevel,
          ParameterState parameterState) {
    this.portMemoryCardService = portMemoryCardService;
    this.gameLevel = gameLevel;
    this.parameterState = parameterState;
  }

  @Override
  public MemoryCardGameRules initialize() {
    this.memoryCardSetter = CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideGameCardData();
    this.selectedBackImagePath = "";

    if(this.frontImagePathAvailableList != null)
      Arrays.fill(this.frontImagePathAvailableList, null);

    this.loadTimeToFinishTheGame();
    this.setGameLevel();
    this.calculateMaxAllowedWrongReturnCard();
    this.loadTimeToFinishTheGame();
    this.calculateCardToFindQuantity();
    this.setTimeToObserveBeforeStart();
    this.loadTextOfTheGame();

    return  this;
  }
  public MemoryCardGameRules selectOneBackImagePath() {
    String[] backImagePathAvailableList = this.portMemoryCardService.getAllAvailableCardPaths(CardFace.BACK_FACE);
    if(backImagePathAvailableList == null || backImagePathAvailableList.length == 0)
      throw new ImageException("Aucune image de disponible pour créer la face arriere de la carte");

    this.selectedBackImagePath = ArrayUtil.selectOneRandomItem(backImagePathAvailableList).getItem();
    return this;
  }

  public MemoryCardGameRules loadAllFrontImagePaths() {
     this.frontImagePathAvailableList = this.portMemoryCardService.getAllAvailableCardPaths(CardFace.FRONT_FACE);
     if(this.frontImagePathAvailableList == null || this.frontImagePathAvailableList.length == 0)
       throw new ImageException("Aucune image de disponible pour créer la face avant de la carte");

    return this;
  }

  public MemoryCardGameRules generateCardsForGame() {
    // Carte a trouver
    String imageTofindPath = this.selectAndStoreCardToFind();

    // Liste des cartes a trouvers dans le jeu
    Card[] cardToFindList = Arrays.stream(ArrayUtil.selectSameItemMultipleTimeByValue(
              this.frontImagePathAvailableList,
              imageTofindPath,
              this.memoryCardSetter.getCardToFindQuantity()
            ))
            .map(path -> CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideCardImageImpl(path, this.selectedBackImagePath))
            .map(image -> CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideCard(image, true))
            .toArray(Card[]::new);

    frontImagePathAvailableList = ArrayUtil.removeItemByItemValue(this.frontImagePathAvailableList, imageTofindPath);

    ArrayUtil.shuffle(this.frontImagePathAvailableList);

    IBornRange cardsQuantityBorn = this.gameLevel.getLevelParameter().getCardsQuantityInGameBorn(parameterState);

    short quantity = NumberUtil.generateRandomNumberBetweenMinAndMax(
            cardsQuantityBorn.getMin(),
            cardsQuantityBorn.getMax());

    Card[] randomCardsList = Arrays.stream(ArrayUtil.selectMultipleRandomItem(
            this.frontImagePathAvailableList,
            quantity - this.memoryCardSetter.getCardToFindQuantity()))
            .map(path -> CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideCardImageImpl(path, this.selectedBackImagePath))
            .map(image -> CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideCard(image, false))
            .toArray(Card[]::new);

    Card[] allCards = ArrayUtil.combineArrays(cardToFindList, randomCardsList);

    ArrayUtil.shuffle(allCards);

   this.memoryCardSetter.setCards(allCards);

    return this;
  }
  public IGenerateNewGameResponse getData() {
    IGenerateNewGameResponse memoryCardData = CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideMemoryCardDataImpl(memoryCardSetter);
    return memoryCardData;
  }

  /**
   * Selectionne une carte a trouver
   * @return String - path de l'image
   */
  private String selectAndStoreCardToFind() {

    // Melange les paths des images de la face avant des cartes
    ArrayUtil.shuffle(this.frontImagePathAvailableList);

    // Selection d'une face avant
    var frontImageTofind = ArrayUtil.selectOneRandomItem(this.frontImagePathAvailableList);

    // Carte a trouver dans le jeu
    ICardImage cardToFind = CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideCardImageImpl(frontImageTofind.getItem(), this.selectedBackImagePath);
    String imageTofindPath = frontImageTofind.getItem();

    // Text de presentation de la carte
    String cardToFindDescription = CARD_TO_FIND_PRESENTATION;

    // La carte à trouver
    CardToFind cardTofindInGame = CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideCardToFind(cardToFind, cardToFindDescription);

    memoryCardSetter.setCardToFindInGame(cardTofindInGame);

    return imageTofindPath;

  }

  private void loadTimeToFinishTheGame() {
    var timeMin = this.gameLevel.getLevelParameter().getTimeInSecToFinishBorn(parameterState).getMin();
    var timeMax = this.gameLevel.getLevelParameter().getTimeInSecToFinishBorn(parameterState).getMax();

    var timeToFinish = NumberUtil.generateRandomNumberBetweenMinAndMax(timeMin, timeMax);

    this.memoryCardSetter.setTimeInSecToFinish(timeToFinish);
  }

  private void calculateMaxAllowedWrongReturnCard() {
    var errorMin = this.gameLevel.getLevelParameter().getMaxWrongReturnCardBorn(parameterState).getMin();
    var errorMax = this.gameLevel.getLevelParameter().getMaxWrongReturnCardBorn(parameterState).getMax();

    var maxWrongReturnCard = NumberUtil.generateRandomNumberBetweenMinAndMax(errorMin, errorMax);

    this.memoryCardSetter.setMaxErrorQuantity(maxWrongReturnCard);
  }

  private void setGameLevel() {
    this.memoryCardSetter.setGameLevel(this.gameLevel);
  }

  private void calculateCardToFindQuantity() {
    IBornRange cardToFindQuantityBorn = this.gameLevel.getLevelParameter().getCardsQuantityToFindBorn(parameterState);
    short quantity = NumberUtil.generateRandomNumberBetweenMinAndMax(
            cardToFindQuantityBorn.getMin(),
            cardToFindQuantityBorn.getMax());

    this.memoryCardSetter.setCardToFindQuantity(quantity);
  }
  private void setTimeToObserveBeforeStart() {
    this.memoryCardSetter.setTimeToObserveBeforeStart(TIME_TO_OBSERVE_BEFORE_START);
  }
  private void loadTextOfTheGame() {
    var gameText = CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideGameTextInformation(
            CONGRATULATION_WORDS,
            LOOSING_WORDS,
            GAME_LOOSE_TEXT,
            GAME_VICTORY_GAME,
            CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideGamePresentation(
                    GAME_TITLE,
                    GAME_TEXT_PRESENTATION
            )
    );
    this.memoryCardSetter.setGameTextInformation(gameText);
  }


}
