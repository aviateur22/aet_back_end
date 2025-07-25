package com.ctoutweb.aet.core.entity.memoryCardGame.businessRules;

import com.ctoutweb.aet.core.entity.IMinAndMax;
import com.ctoutweb.aet.core.entity.gameText.gameEnd.IGameEndParameterByLevel;
import com.ctoutweb.aet.core.entity.memoryCardGame.*;
import com.ctoutweb.aet.core.entity.memoryCardGame.impl.gameTextImpl.EndLevelParameterLoader;
import com.ctoutweb.aet.core.exception.ImageException;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.port.IGenerateNewGameGateway;
import com.ctoutweb.aet.core.util.ArrayUtil;
import com.ctoutweb.aet.core.util.NumberUtil;
import com.ctoutweb.aet.core.usecase.base.RuleBase;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.boundary.IGenerateNewGameResponse;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static com.ctoutweb.aet.core.paramter.memoryCardGameParameter.GameData.*;
import static com.ctoutweb.aet.core.provider.CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER;

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
    this.memoryCardSetter = MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideGameCardData();
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
    String[] backImagePathAvailableList = this.portMemoryCardService.getAvailableCardName(CardFace.BACK_FACE);
    if(backImagePathAvailableList == null || backImagePathAvailableList.length == 0)
      throw new ImageException("Aucune image de disponible pour créer la face arriere de la carte");

    this.selectedBackImagePath = ArrayUtil.selectOneRandomItem(backImagePathAvailableList).getItem();
    return this;
  }

  public MemoryCardGameRules loadAllFrontImagePaths() {
     this.frontImagePathAvailableList = this.portMemoryCardService.getAvailableCardName(CardFace.FRONT_FACE);
     if(this.frontImagePathAvailableList == null || this.frontImagePathAvailableList.length == 0)
       throw new ImageException("Aucune image de disponible pour créer la face avant de la carte");

    return this;
  }

  public MemoryCardGameRules generateCardsForGame() {
    // Carte a trouver
    String imageTofindPath = this.selectAndStoreCardToFind();

    // Liste des cartes a trouvers dans le jeu
    ImageSelect[] imageFrontPathToFindList = Arrays.stream(ArrayUtil.selectSameItemMultipleTimeByValue(
              this.frontImagePathAvailableList,
              imageTofindPath,
              this.memoryCardSetter.getCardToFindQuantity()
            )).map(image -> MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideImageSelect(image, true))
            .toArray(ImageSelect[]::new);

    frontImagePathAvailableList = ArrayUtil.removeItemByItemValue(this.frontImagePathAvailableList, imageTofindPath);

    ArrayUtil.shuffle(this.frontImagePathAvailableList);

    IMinAndMax<Integer> cardsQuantityBorn = this.gameLevel.getLevelParameter().getCardsQuantityInGameBorn(parameterState);

    var quantity = NumberUtil.generateRandomNumberBetweenMinAndMax(
            cardsQuantityBorn.getMin(),
            cardsQuantityBorn.getMax());
    AtomicInteger idCounter = new AtomicInteger(1);

    ImageSelect[] randomImageFrontPathList = Arrays.stream(ArrayUtil.selectMultipleRandomItem(
            this.frontImagePathAvailableList,
            quantity - this.memoryCardSetter.getCardToFindQuantity()))
            .map(image -> MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideImageSelect(image, false))
            .toArray(ImageSelect[]::new);


    ImageSelect[] allGameFrontImagePathCards = ArrayUtil.combineArrays(imageFrontPathToFindList, randomImageFrontPathList);

    Card[] allGameCards = Arrays.stream(allGameFrontImagePathCards)
            .map(image -> {
              int id = idCounter.getAndIncrement();
              var cardImage = MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideCardImageImpl(image.imagePath(), this.selectedBackImagePath);
              return MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideCard(id, cardImage, image.isToFind());
            })
            .toArray(Card[]::new);

    ArrayUtil.shuffle(allGameCards);
   this.memoryCardSetter.setCards(allGameCards);

    return this;
  }
  public IGenerateNewGameResponse getCardGameData() {
    IGenerateNewGameResponse memoryCardData = MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideMemoryCardDataImpl(memoryCardSetter);
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
    ICardImage cardToFind = MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideCardImageImpl(frontImageTofind.getItem(), this.selectedBackImagePath);
    String imageTofindPath = frontImageTofind.getItem();

    // Text de presentation de la carte
    String cardToFindDescription = CARD_TO_FIND_PRESENTATION;

    // La carte à trouver
    CardToFind cardTofindInGame = MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideCardToFind(cardToFind, cardToFindDescription);

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
    IMinAndMax<Integer> cardToFindQuantityBorn = this.gameLevel.getLevelParameter().getCardsQuantityToFindBorn(parameterState);
    var quantity = NumberUtil.generateRandomNumberBetweenMinAndMax(
            cardToFindQuantityBorn.getMin(),
            cardToFindQuantityBorn.getMax());

    this.memoryCardSetter.setCardToFindQuantity(quantity);
  }
  private void setTimeToObserveBeforeStart() {
    this.memoryCardSetter.setTimeToObserveBeforeStart(TIME_TO_OBSERVE_BEFORE_START);
  }

  @Override
  protected void loadTextOfTheGame() {
    IGameEndParameterByLevel[] gameEndParameterByLevels = new IGameEndParameterByLevel[] {
            EndLevelParameterLoader.EXCELLENT.getGameEndParameterByLevel(),
            EndLevelParameterLoader.VERY_GOOD.getGameEndParameterByLevel(),
            EndLevelParameterLoader.GOOD.getGameEndParameterByLevel(),
            EndLevelParameterLoader.MEDUIM.getGameEndParameterByLevel(),
            EndLevelParameterLoader.BAD.getGameEndParameterByLevel(),
            EndLevelParameterLoader.VER_BAD.getGameEndParameterByLevel(),
            EndLevelParameterLoader.LOOSE.getGameEndParameterByLevel()
    };

    var gameText = MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideGameTextInformation(
            CONGRATULATION_WORDS,
            LOOSING_WORDS,
            MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideGamePresentation(
                    GAME_TITLE,
                    GAME_TEXT_PRESENTATION
            ),
            gameEndParameterByLevels
    );

    this.memoryCardSetter.setGameTextInformation(gameText);
  }


}
