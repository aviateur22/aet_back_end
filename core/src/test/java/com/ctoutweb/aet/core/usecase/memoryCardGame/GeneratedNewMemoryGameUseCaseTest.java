package com.ctoutweb.aet.core.usecase.memoryCardGame;

import com.ctoutweb.aet.core.entity.memoryCardGame.CardFace;
import com.ctoutweb.aet.core.entity.memoryCardGame.GameLevel;
import com.ctoutweb.aet.core.entity.memoryCardGame.ParameterState;
import com.ctoutweb.aet.core.entity.memoryCardGame.impl.BornRangeImpl;
import com.ctoutweb.aet.core.usecase.memoryCardGame.boundary.IGenerateNewGameRequest;
import com.ctoutweb.aet.core.usecase.memoryCardGame.port.IGenerateNewGameGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.ctoutweb.aet.core.usecase.memoryCardGame.gameParameter.GameData.*;

public class GeneratedNewMemoryGameUseCaseTest {
  @Mock
  IGenerateNewGameGateway portMemoryCardService;

  GenerateNewMemoryCardGameUseCase useCase;

  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);

    useCase = new GenerateNewMemoryCardGameUseCase(
            portMemoryCardService
    );

  }

  @Test
  void GeneratedNewMemoryGameUseCase_level_easy_and_parameter_random() {

    /**
     * given
     */
    IGenerateNewGameRequest gameLevelRequest = generateNewGameRequest(GameLevel.EASY, ParameterState.RANDOM);
    GenerateNewMemoryCardGameUseCase.Input input = new GenerateNewMemoryCardGameUseCase.Input(gameLevelRequest);
    Mockito.when(portMemoryCardService.getAllAvailableCardPaths(CardFace.BACK_FACE)).thenReturn(new String[] {"bb", "aa", "cc", "dd"});
    Mockito.when(portMemoryCardService.getAllAvailableCardPaths(CardFace.FRONT_FACE)).thenReturn(new String[] {"a", "b", "c", "d", "e", "f", "g"});

    /**
     * when
     */
    var output = useCase.execute(input);

    /**
     * expect
     */
    BornRangeImpl expectedCardsQuantityToFindBorn = new BornRangeImpl(2,5);
    BornRangeImpl expectedCardsQuantityInGameBorn = new BornRangeImpl(9, 13);
    BornRangeImpl expectedMaxWrongReturnCardBorn = new BornRangeImpl(3,5);
    BornRangeImpl expectedTimeInSecToFinishBorn = new BornRangeImpl(40,55);

    Assertions.assertNotNull(output);

    var data = output.getMemoryGameCardData();

    Assertions.assertTrue(data.getCardToFindQuantity() >=  expectedCardsQuantityToFindBorn.getMin());
    Assertions.assertTrue(data.getCardToFindQuantity() <=  expectedCardsQuantityToFindBorn.getMax());

    Assertions.assertTrue(data.getCards().length >=  expectedCardsQuantityInGameBorn.getMin());
    Assertions.assertTrue(data.getCards().length <=  expectedCardsQuantityInGameBorn.getMax());

    Assertions.assertTrue(data.getTimeInSecToFinish() >=  expectedTimeInSecToFinishBorn.getMin());
    Assertions.assertTrue(data.getTimeInSecToFinish() <=  expectedTimeInSecToFinishBorn.getMax());

    Assertions.assertTrue(data.getMaxErrorQuantity() >=  expectedMaxWrongReturnCardBorn.getMin());
    Assertions.assertTrue(data.getMaxErrorQuantity()<=  expectedMaxWrongReturnCardBorn.getMax());

    Assertions.assertEquals(TIME_TO_OBSERVE_BEFORE_START, data.getTimeToObserveBeforeStart());

    Assertions.assertEquals(CARD_TO_FIND_PRESENTATION, data.getCardToFindInGame().cardTextExplanation());

    // Vérification que toute les cartes à trouver sont présente dans le jeu
    var cardToFindIngGameList = Arrays.stream(data.getCards()).filter(card -> card.isCardToFind()).collect(Collectors.toList());
    var expectedCardToFindQuantity = data.getCardToFindQuantity();
    Assertions.assertTrue(cardToFindIngGameList.size() == expectedCardToFindQuantity);

    // Vérification que toute les cartes a trouvers ont la bonne image de face
    var expectedImage = data.getCardToFindInGame().cardImage().getCardFrontImagePath();
    cardToFindIngGameList.forEach(card-> Assertions.assertTrue(card.cardImages().getCardFrontImagePath().equalsIgnoreCase(expectedImage)));

    // Vérification du text du jeu
    var gameText = data.getGameTextInformation();
    Assertions.assertEquals(GAME_TEXT_PRESENTATION, gameText.getGamePresentation());
    Assertions.assertEquals(GAME_LOOSE_TEXT, gameText.getGameLostText());
    Assertions.assertEquals(GAME_VICTORY_GAME, gameText.getGameVictoryText());
    Assertions.assertEquals(CONGRATULATION_WORDS, gameText.getCongratulationWords());
    Assertions.assertEquals(LOOSING_WORDS, gameText.getLoosingWords());
    Assertions.assertEquals(GAME_TITLE, gameText.getGamePresentation().gameTitle());



  }

  @Test
  void GeneratedNewMemoryGameUseCase_level_easy_and_parameter_fix() {

    /**
     * given
     */
    IGenerateNewGameRequest gameLevelRequest = generateNewGameRequest(GameLevel.EASY, ParameterState.FIX);
    GenerateNewMemoryCardGameUseCase.Input input = new GenerateNewMemoryCardGameUseCase.Input(gameLevelRequest);
    Mockito.when(portMemoryCardService.getAllAvailableCardPaths(CardFace.BACK_FACE)).thenReturn(new String[] {"bb", "aa", "cc", "dd"});
    Mockito.when(portMemoryCardService.getAllAvailableCardPaths(CardFace.FRONT_FACE)).thenReturn(new String[] {"a", "b", "c", "d", "e", "f", "g"});

    /**
     * when
     */
    var output = useCase.execute(input);

    /**
     * expect
     */
    BornRangeImpl expectedCardsQuantityToFindBorn = new BornRangeImpl(4,4);
    BornRangeImpl expectedCardsQuantityInGameBorn = new BornRangeImpl(11, 11);
    BornRangeImpl expectedMaxWrongReturnCardBorn = new BornRangeImpl(4,4);
    BornRangeImpl expectedTimeInSecToFinishBorn = new BornRangeImpl(50,50);

    Assertions.assertNotNull(output);

    var data = output.getMemoryGameCardData();

    Assertions.assertTrue(data.getCardToFindQuantity() >=  expectedCardsQuantityToFindBorn.getMin());
    Assertions.assertTrue(data.getCardToFindQuantity() <=  expectedCardsQuantityToFindBorn.getMax());

    Assertions.assertTrue(data.getCards().length >=  expectedCardsQuantityInGameBorn.getMin());
    Assertions.assertTrue(data.getCards().length <=  expectedCardsQuantityInGameBorn.getMax());

    Assertions.assertTrue(data.getTimeInSecToFinish() >=  expectedTimeInSecToFinishBorn.getMin());
    Assertions.assertTrue(data.getTimeInSecToFinish() <=  expectedTimeInSecToFinishBorn.getMax());

    Assertions.assertTrue(data.getMaxErrorQuantity() >=  expectedMaxWrongReturnCardBorn.getMin());
    Assertions.assertTrue(data.getMaxErrorQuantity()<=  expectedMaxWrongReturnCardBorn.getMax());

    Assertions.assertEquals(TIME_TO_OBSERVE_BEFORE_START, data.getTimeToObserveBeforeStart());

    Assertions.assertEquals(CARD_TO_FIND_PRESENTATION, data.getCardToFindInGame().cardTextExplanation());

    // Vérification que toute les cartes à trouver sont présente dans le jeu
    var cardToFindIngGameList = Arrays.stream(data.getCards()).filter(card -> card.isCardToFind()).collect(Collectors.toList());
    var expectedCardToFindQuantity = data.getCardToFindQuantity();
    Assertions.assertTrue(cardToFindIngGameList.size() == expectedCardToFindQuantity);

    // Vérification que toute les cartes a trouvers ont la bonne image de face
    var expectedImage = data.getCardToFindInGame().cardImage().getCardFrontImagePath();
    cardToFindIngGameList.forEach(card-> Assertions.assertTrue(card.cardImages().getCardFrontImagePath().equalsIgnoreCase(expectedImage)));

    // Vérification du text du jeu
    var gameText = data.getGameTextInformation();
    Assertions.assertEquals(GAME_TEXT_PRESENTATION, gameText.getGamePresentation());
    Assertions.assertEquals(GAME_LOOSE_TEXT, gameText.getGameLostText());
    Assertions.assertEquals(GAME_VICTORY_GAME, gameText.getGameVictoryText());
    Assertions.assertEquals(CONGRATULATION_WORDS, gameText.getCongratulationWords());
    Assertions.assertEquals(LOOSING_WORDS, gameText.getLoosingWords());
    Assertions.assertEquals(GAME_TITLE, gameText.getGamePresentation().gameTitle());
  }

  @Test
  void GeneratedNewMemoryGameUseCase_level_medium_and_parameter_random() {

    /**
     * given
     */
    IGenerateNewGameRequest gameLevelRequest = generateNewGameRequest(GameLevel.MEDIUM, ParameterState.RANDOM);
    GenerateNewMemoryCardGameUseCase.Input input = new GenerateNewMemoryCardGameUseCase.Input(gameLevelRequest);
    Mockito.when(portMemoryCardService.getAllAvailableCardPaths(CardFace.BACK_FACE)).thenReturn(new String[] {"bb", "aa", "cc", "dd"});
    Mockito.when(portMemoryCardService.getAllAvailableCardPaths(CardFace.FRONT_FACE)).thenReturn(new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"});

    /**
     * when
     */
    var output = useCase.execute(input);

    /**
     * expect
     */
    BornRangeImpl expectedCardsQuantityToFindBorn = new BornRangeImpl(4,8);
    BornRangeImpl expectedCardsQuantityInGameBorn = new BornRangeImpl(12, 18);
    BornRangeImpl expectedMaxWrongReturnCardBorn = new BornRangeImpl(2,4);
    BornRangeImpl expectedTimeInSecToFinishBorn = new BornRangeImpl(30,40);

    Assertions.assertNotNull(output);

    var data = output.getMemoryGameCardData();

    Assertions.assertTrue(data.getCardToFindQuantity() >=  expectedCardsQuantityToFindBorn.getMin());
    Assertions.assertTrue(data.getCardToFindQuantity() <=  expectedCardsQuantityToFindBorn.getMax());

    Assertions.assertTrue(data.getCards().length >=  expectedCardsQuantityInGameBorn.getMin());
    Assertions.assertTrue(data.getCards().length <=  expectedCardsQuantityInGameBorn.getMax());

    Assertions.assertTrue(data.getTimeInSecToFinish() >=  expectedTimeInSecToFinishBorn.getMin());
    Assertions.assertTrue(data.getTimeInSecToFinish() <=  expectedTimeInSecToFinishBorn.getMax());

    Assertions.assertTrue(data.getMaxErrorQuantity() >=  expectedMaxWrongReturnCardBorn.getMin());
    Assertions.assertTrue(data.getMaxErrorQuantity()<=  expectedMaxWrongReturnCardBorn.getMax());

    Assertions.assertEquals(TIME_TO_OBSERVE_BEFORE_START, data.getTimeToObserveBeforeStart());

    Assertions.assertEquals(CARD_TO_FIND_PRESENTATION, data.getCardToFindInGame().cardTextExplanation());

    // Vérification que toute les cartes à trouver sont présente dans le jeu
    var cardToFindIngGameList = Arrays.stream(data.getCards()).filter(card -> card.isCardToFind()).collect(Collectors.toList());
    var expectedCardToFindQuantity = data.getCardToFindQuantity();
    Assertions.assertTrue(cardToFindIngGameList.size() == expectedCardToFindQuantity);

    // Vérification que toute les cartes a trouvers ont la bonne image de face
    var expectedImage = data.getCardToFindInGame().cardImage().getCardFrontImagePath();
    cardToFindIngGameList.forEach(card-> Assertions.assertTrue(card.cardImages().getCardFrontImagePath().equalsIgnoreCase(expectedImage)));

    // Vérification du text du jeu
    var gameText = data.getGameTextInformation();
    Assertions.assertEquals(GAME_TEXT_PRESENTATION, gameText.getGamePresentation());
    Assertions.assertEquals(GAME_LOOSE_TEXT, gameText.getGameLostText());
    Assertions.assertEquals(GAME_VICTORY_GAME, gameText.getGameVictoryText());
    Assertions.assertEquals(CONGRATULATION_WORDS, gameText.getCongratulationWords());
    Assertions.assertEquals(LOOSING_WORDS, gameText.getLoosingWords());
    Assertions.assertEquals(GAME_TITLE, gameText.getGamePresentation().gameTitle());


  }

  @Test
  void GeneratedNewMemoryGameUseCase_level_medium_and_parameter_fix() {

    /**
     * given
     */
    IGenerateNewGameRequest gameLevelRequest = generateNewGameRequest(GameLevel.MEDIUM, ParameterState.FIX);
    GenerateNewMemoryCardGameUseCase.Input input = new GenerateNewMemoryCardGameUseCase.Input(gameLevelRequest);
    Mockito.when(portMemoryCardService.getAllAvailableCardPaths(CardFace.BACK_FACE)).thenReturn(new String[] {"bb", "aa", "cc", "dd"});
    Mockito.when(portMemoryCardService.getAllAvailableCardPaths(CardFace.FRONT_FACE)).thenReturn(new String[] {"a", "b", "c", "d", "e", "f", "g", "v", "n"});

    /**
     * when
     */
    var output = useCase.execute(input);

    /**
     * expect
     */
    BornRangeImpl expectedCardsQuantityToFindBorn = new BornRangeImpl(6,6);
    BornRangeImpl expectedCardsQuantityInGameBorn = new BornRangeImpl(15, 15);
    BornRangeImpl expectedMaxWrongReturnCardBorn = new BornRangeImpl(3,3);
    BornRangeImpl expectedTimeInSecToFinishBorn = new BornRangeImpl(35,35);

    Assertions.assertNotNull(output);

    var data = output.getMemoryGameCardData();

    Assertions.assertTrue(data.getCardToFindQuantity() >=  expectedCardsQuantityToFindBorn.getMin());
    Assertions.assertTrue(data.getCardToFindQuantity() <=  expectedCardsQuantityToFindBorn.getMax());

    Assertions.assertTrue(data.getCards().length >=  expectedCardsQuantityInGameBorn.getMin());
    Assertions.assertTrue(data.getCards().length <=  expectedCardsQuantityInGameBorn.getMax());

    Assertions.assertTrue(data.getTimeInSecToFinish() >=  expectedTimeInSecToFinishBorn.getMin());
    Assertions.assertTrue(data.getTimeInSecToFinish() <=  expectedTimeInSecToFinishBorn.getMax());

    Assertions.assertTrue(data.getMaxErrorQuantity() >=  expectedMaxWrongReturnCardBorn.getMin());
    Assertions.assertTrue(data.getMaxErrorQuantity()<=  expectedMaxWrongReturnCardBorn.getMax());

    Assertions.assertEquals(TIME_TO_OBSERVE_BEFORE_START, data.getTimeToObserveBeforeStart());

    Assertions.assertEquals(CARD_TO_FIND_PRESENTATION, data.getCardToFindInGame().cardTextExplanation());

    // Vérification que toute les cartes à trouver sont présente dans le jeu
    var cardToFindIngGameList = Arrays.stream(data.getCards()).filter(card -> card.isCardToFind()).collect(Collectors.toList());
    var expectedCardToFindQuantity = data.getCardToFindQuantity();
    Assertions.assertTrue(cardToFindIngGameList.size() == expectedCardToFindQuantity);

    // Vérification que toute les cartes a trouvers ont la bonne image de face
    var expectedImage = data.getCardToFindInGame().cardImage().getCardFrontImagePath();
    cardToFindIngGameList.forEach(card-> Assertions.assertTrue(card.cardImages().getCardFrontImagePath().equalsIgnoreCase(expectedImage)));

    // Vérification du text du jeu
    var gameText = data.getGameTextInformation();
    Assertions.assertEquals(GAME_TEXT_PRESENTATION, gameText.getGamePresentation());
    Assertions.assertEquals(GAME_LOOSE_TEXT, gameText.getGameLostText());
    Assertions.assertEquals(GAME_VICTORY_GAME, gameText.getGameVictoryText());
    Assertions.assertEquals(CONGRATULATION_WORDS, gameText.getCongratulationWords());
    Assertions.assertEquals(LOOSING_WORDS, gameText.getLoosingWords());
    Assertions.assertEquals(GAME_TITLE, gameText.getGamePresentation().gameTitle());
  }

  @Test
  void GeneratedNewMemoryGameUseCase_level_difficult_and_parameter_random() {

    /**
     * given
     */
    IGenerateNewGameRequest gameLevelRequest = generateNewGameRequest(GameLevel.DIFFICULT, ParameterState.RANDOM);
    GenerateNewMemoryCardGameUseCase.Input input = new GenerateNewMemoryCardGameUseCase.Input(gameLevelRequest);
    Mockito.when(portMemoryCardService.getAllAvailableCardPaths(CardFace.BACK_FACE)).thenReturn(new String[] {"bb", "aa", "cc", "dd"});
    Mockito.when(portMemoryCardService.getAllAvailableCardPaths(CardFace.FRONT_FACE)).thenReturn(new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"});

    /**
     * when
     */
    var output = useCase.execute(input);

    /**
     * expect
     */
    BornRangeImpl expectedCardsQuantityToFindBorn = new BornRangeImpl(7,11);
    BornRangeImpl expectedCardsQuantityInGameBorn = new BornRangeImpl(15, 25);
    BornRangeImpl expectedMaxWrongReturnCardBorn = new BornRangeImpl(1,2);
    BornRangeImpl expectedTimeInSecToFinishBorn = new BornRangeImpl(20,30);

    Assertions.assertNotNull(output);

    var data = output.getMemoryGameCardData();

    Assertions.assertTrue(data.getCardToFindQuantity() >=  expectedCardsQuantityToFindBorn.getMin());
    Assertions.assertTrue(data.getCardToFindQuantity() <=  expectedCardsQuantityToFindBorn.getMax());

    Assertions.assertTrue(data.getCards().length >=  expectedCardsQuantityInGameBorn.getMin());
    Assertions.assertTrue(data.getCards().length <=  expectedCardsQuantityInGameBorn.getMax());

    Assertions.assertTrue(data.getTimeInSecToFinish() >=  expectedTimeInSecToFinishBorn.getMin());
    Assertions.assertTrue(data.getTimeInSecToFinish() <=  expectedTimeInSecToFinishBorn.getMax());

    Assertions.assertTrue(data.getMaxErrorQuantity() >=  expectedMaxWrongReturnCardBorn.getMin());
    Assertions.assertTrue(data.getMaxErrorQuantity()<=  expectedMaxWrongReturnCardBorn.getMax());

    Assertions.assertEquals(TIME_TO_OBSERVE_BEFORE_START, data.getTimeToObserveBeforeStart());

    Assertions.assertEquals(CARD_TO_FIND_PRESENTATION, data.getCardToFindInGame().cardTextExplanation());

    // Vérification que toute les cartes à trouver sont présente dans le jeu
    var cardToFindIngGameList = Arrays.stream(data.getCards()).filter(card -> card.isCardToFind()).collect(Collectors.toList());
    var expectedCardToFindQuantity = data.getCardToFindQuantity();
    Assertions.assertTrue(cardToFindIngGameList.size() == expectedCardToFindQuantity);

    // Vérification que toute les cartes a trouvers ont la bonne image de face
    var expectedImage = data.getCardToFindInGame().cardImage().getCardFrontImagePath();
    cardToFindIngGameList.forEach(card-> Assertions.assertTrue(card.cardImages().getCardFrontImagePath().equalsIgnoreCase(expectedImage)));

    // Vérification du text du jeu
    var gameText = data.getGameTextInformation();
    Assertions.assertEquals(GAME_TEXT_PRESENTATION, gameText.getGamePresentation());
    Assertions.assertEquals(GAME_LOOSE_TEXT, gameText.getGameLostText());
    Assertions.assertEquals(GAME_VICTORY_GAME, gameText.getGameVictoryText());
    Assertions.assertEquals(CONGRATULATION_WORDS, gameText.getCongratulationWords());
    Assertions.assertEquals(LOOSING_WORDS, gameText.getLoosingWords());
    Assertions.assertEquals(GAME_TITLE, gameText.getGamePresentation().gameTitle());
  }

  @Test
  void GeneratedNewMemoryGameUseCase_level_difficult_and_parameter_fix() {

    /**
     * given
     */
    IGenerateNewGameRequest gameLevelRequest = generateNewGameRequest(GameLevel.DIFFICULT, ParameterState.FIX);
    GenerateNewMemoryCardGameUseCase.Input input = new GenerateNewMemoryCardGameUseCase.Input(gameLevelRequest);
    Mockito.when(portMemoryCardService.getAllAvailableCardPaths(CardFace.BACK_FACE)).thenReturn(new String[] {"bb", "aa", "cc", "dd"});
    Mockito.when(portMemoryCardService.getAllAvailableCardPaths(CardFace.FRONT_FACE)).thenReturn(new String[] {"a", "b", "c", "d", "e", "f", "g", "v", "n"});

    /**
     * when
     */
    var output = useCase.execute(input);

    /**
     * expect
     */
    BornRangeImpl expectedCardsQuantityToFindBorn = new BornRangeImpl(9,9);
    BornRangeImpl expectedCardsQuantityInGameBorn = new BornRangeImpl(20, 20);
    BornRangeImpl expectedMaxWrongReturnCardBorn = new BornRangeImpl(2,2);
    BornRangeImpl expectedTimeInSecToFinishBorn = new BornRangeImpl(25,25);

    Assertions.assertNotNull(output);

    var data = output.getMemoryGameCardData();

    Assertions.assertTrue(data.getCardToFindQuantity() >=  expectedCardsQuantityToFindBorn.getMin());
    Assertions.assertTrue(data.getCardToFindQuantity() <=  expectedCardsQuantityToFindBorn.getMax());

    Assertions.assertTrue(data.getCards().length >=  expectedCardsQuantityInGameBorn.getMin());
    Assertions.assertTrue(data.getCards().length <=  expectedCardsQuantityInGameBorn.getMax());

    Assertions.assertTrue(data.getTimeInSecToFinish() >=  expectedTimeInSecToFinishBorn.getMin());
    Assertions.assertTrue(data.getTimeInSecToFinish() <=  expectedTimeInSecToFinishBorn.getMax());

    Assertions.assertTrue(data.getMaxErrorQuantity() >=  expectedMaxWrongReturnCardBorn.getMin());
    Assertions.assertTrue(data.getMaxErrorQuantity()<=  expectedMaxWrongReturnCardBorn.getMax());

    Assertions.assertEquals(TIME_TO_OBSERVE_BEFORE_START, data.getTimeToObserveBeforeStart());

    Assertions.assertEquals(CARD_TO_FIND_PRESENTATION, data.getCardToFindInGame().cardTextExplanation());

    // Vérification que toute les cartes à trouver sont présente dans le jeu
    var cardToFindIngGameList = Arrays.stream(data.getCards()).filter(card -> card.isCardToFind()).collect(Collectors.toList());
    var expectedCardToFindQuantity = data.getCardToFindQuantity();
    Assertions.assertTrue(cardToFindIngGameList.size() == expectedCardToFindQuantity);

    // Vérification que toute les cartes a trouvers ont la bonne image de face
    var expectedImage = data.getCardToFindInGame().cardImage().getCardFrontImagePath();
    cardToFindIngGameList.forEach(card-> Assertions.assertTrue(card.cardImages().getCardFrontImagePath().equalsIgnoreCase(expectedImage)));

    // Vérification du text du jeu
    var gameText = data.getGameTextInformation();
    Assertions.assertEquals(GAME_TEXT_PRESENTATION, gameText.getGamePresentation());
    Assertions.assertEquals(GAME_LOOSE_TEXT, gameText.getGameLostText());
    Assertions.assertEquals(GAME_VICTORY_GAME, gameText.getGameVictoryText());
    Assertions.assertEquals(CONGRATULATION_WORDS, gameText.getCongratulationWords());
    Assertions.assertEquals(LOOSING_WORDS, gameText.getLoosingWords());
    Assertions.assertEquals(GAME_TITLE, gameText.getGamePresentation().gameTitle());
  }

  private IGenerateNewGameRequest generateNewGameRequest(GameLevel gameLevel, ParameterState parameterState) {
    return new IGenerateNewGameRequest() {
      @Override
      public GameLevel getGameLevel() {
        return gameLevel;
      }

      @Override
      public ParameterState getParameterState() {
        return parameterState;
      }
    };
  }
}
