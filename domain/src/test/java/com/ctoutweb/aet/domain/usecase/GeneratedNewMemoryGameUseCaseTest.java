package com.ctoutweb.aet.domain.usecase;

import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndParameterByLevel;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.CardFace;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.GameLevel;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.ParameterState;
import com.ctoutweb.aet.domain.entity.MinAndMaxImpl;
import com.ctoutweb.aet.domain.port.generateMemoryCardGame.IGenerateMemoryCardGameInput;
import com.ctoutweb.aet.domain.port.generateMemoryCardGame.IGenerateMemoryCardGameGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.params.ParameterizedTest;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ctoutweb.aet.domain.gameConfiguration.generateMemoryCardGame.GameData.*;

public class GeneratedNewMemoryGameUseCaseTest {
  @Mock
  IGenerateMemoryCardGameGateway portMemoryCardService;

  GenerateMemoryCardGameUseCase useCase;

  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);

    useCase = new GenerateMemoryCardGameUseCase(
            portMemoryCardService
    );

  }
  @ParameterizedTest
  @MethodSource("provideRequestDtoParameter")
  void GeneratedNewMemoryGameUseCase_level_easy_and_parameter_random(ParameterState parameterState, GameLevel gameLevel) {

    /**
     * given
     */
    IGenerateMemoryCardGameInput gameLevelRequest = generateNewGameRequest(gameLevel, parameterState);
    GenerateMemoryCardGameUseCase.Input input = new GenerateMemoryCardGameUseCase.Input(gameLevelRequest);
    Mockito.when(portMemoryCardService.getAvailableCardName(CardFace.BACK_FACE)).thenReturn(new String[] {"bb", "aa", "cc", "dd"});
    Mockito.when(portMemoryCardService.getAvailableCardName(CardFace.FRONT_FACE)).thenReturn(new String[] {"a", "b", "c", "d", "e", "f", "g"});

    /**
     * when
     */
    var output = useCase.execute(input);

    /**
     * expect
     */

    IMinAndMax<Integer> expectedCardsQuantityToFindBorn;
    IMinAndMax<Integer> expectedCardsQuantityInGameBorn;
    IMinAndMax<Integer> expectedMaxWrongReturnCardBorn;
    IMinAndMax<Integer> expectedTimeInSecToFinishBorn;

    if(parameterState == ParameterState.RANDOM && gameLevel == GameLevel.EASY) {
      expectedCardsQuantityToFindBorn = new MinAndMaxImpl<>(2,5);
      expectedCardsQuantityInGameBorn = new MinAndMaxImpl<>(9, 13);
      expectedMaxWrongReturnCardBorn = new MinAndMaxImpl<>(3,5);
      expectedTimeInSecToFinishBorn = new MinAndMaxImpl<>(40,55);

    } else if(parameterState == ParameterState.RANDOM && gameLevel == GameLevel.MEDIUM) {
      expectedCardsQuantityToFindBorn = new MinAndMaxImpl<>(4,8);
      expectedCardsQuantityInGameBorn = new MinAndMaxImpl<>(12, 18);
      expectedMaxWrongReturnCardBorn = new MinAndMaxImpl<>(2,4);
      expectedTimeInSecToFinishBorn = new MinAndMaxImpl<>(30,40);

    } else if(parameterState == ParameterState.RANDOM && gameLevel == GameLevel.DIFFICULT) {
      expectedCardsQuantityToFindBorn = new MinAndMaxImpl<>(7,11);
      expectedCardsQuantityInGameBorn = new MinAndMaxImpl<>(15, 25);
      expectedMaxWrongReturnCardBorn = new MinAndMaxImpl<>(1,2);
      expectedTimeInSecToFinishBorn = new MinAndMaxImpl<>(20,30);

    } else if(parameterState == ParameterState.FIX && gameLevel == GameLevel.EASY) {
      expectedCardsQuantityToFindBorn = new MinAndMaxImpl<>(4,4);
      expectedCardsQuantityInGameBorn = new MinAndMaxImpl<>(11, 11);
      expectedMaxWrongReturnCardBorn = new MinAndMaxImpl<>(4,4);
      expectedTimeInSecToFinishBorn = new MinAndMaxImpl<>(50,50);

    } else if(parameterState == ParameterState.FIX && gameLevel == GameLevel.MEDIUM) {
      expectedCardsQuantityToFindBorn = new MinAndMaxImpl<>(6,6);
      expectedCardsQuantityInGameBorn = new MinAndMaxImpl<>(15, 15);
      expectedMaxWrongReturnCardBorn = new MinAndMaxImpl<>(3,3);
      expectedTimeInSecToFinishBorn =  new MinAndMaxImpl<>(35,35);

    } else if(parameterState == ParameterState.FIX && gameLevel == GameLevel.DIFFICULT) {
      expectedCardsQuantityToFindBorn = new MinAndMaxImpl<>(9,9);
      expectedCardsQuantityInGameBorn = new MinAndMaxImpl<>(20, 20);
      expectedMaxWrongReturnCardBorn = new MinAndMaxImpl<>(2,2);
      expectedTimeInSecToFinishBorn = new MinAndMaxImpl<>(25,25);

    } else {
      expectedCardsQuantityToFindBorn = new MinAndMaxImpl<>(99999,99999);
      expectedCardsQuantityInGameBorn = new MinAndMaxImpl<>(99999,99999);
      expectedMaxWrongReturnCardBorn = new MinAndMaxImpl<>(99999,99999);
      expectedTimeInSecToFinishBorn = new MinAndMaxImpl<>(99999,99999);
    }
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
    var expectedImage = data.getCardToFindInGame().cardImage().getImageFrontName();
    cardToFindIngGameList.forEach(card-> Assertions.assertTrue(card.cardImages().getImageFrontName().equalsIgnoreCase(expectedImage)));

    // Vérification du text du jeu
    var gameText = data.getGameTextInformation();
    Assertions.assertEquals(GAME_TITLE, gameText.getGamePresentation().getGameTitle());
    Assertions.assertEquals(GAME_TEXT_PRESENTATION, gameText.getGamePresentation().getPresentationText());
    Assertions.assertEquals(CONGRATULATION_WORDS, gameText.getCongratulationWords());
    Assertions.assertEquals(LOOSING_WORDS, gameText.getLoosingWords());

  // Vérification des texts de fin de jeux
    validateGameEndText(gameText);
  }

  private void validateGameEndText(IGameTextInformation gameText) {
    // Vérification des texts de fin de jeux
    for(IGameEndParameterByLevel d : gameText.getGameEndParameterByLevels()) {
      switch (d.getEndErrorLevel()) {
        case EXCELLENT ->  {
          Assertions.assertEquals(END_TEXT_EXCELLENT.getEndText(), d.getGameEndText().getEndText());
          Assertions.assertEquals(END_TEXT_EXCELLENT.getEndTitle(), d.getGameEndText().getEndTitle());
        }
        case VERY_GOOD -> {
          Assertions.assertEquals(END_TEXT_VERY_GOOD.getEndText(), d.getGameEndText().getEndText());
          Assertions.assertEquals(END_TEXT_VERY_GOOD.getEndTitle(), d.getGameEndText().getEndTitle());
        }
        case GOOD -> {
          Assertions.assertEquals(END_TEXT_GOOD.getEndText(), d.getGameEndText().getEndText());
          Assertions.assertEquals(END_TEXT_GOOD.getEndTitle(), d.getGameEndText().getEndTitle());
        }
        case MEDUIM -> {
          Assertions.assertEquals(END_TEXT_MEDIUM.getEndText(), d.getGameEndText().getEndText());
          Assertions.assertEquals(END_TEXT_MEDIUM.getEndTitle(), d.getGameEndText().getEndTitle());
        }
        case BAD -> {
          Assertions.assertEquals(END_TEXT_BAD.getEndText(), d.getGameEndText().getEndText());
          Assertions.assertEquals(END_TEXT_BAD.getEndTitle(), d.getGameEndText().getEndTitle());
        }
        case VERY_BAD -> {
          Assertions.assertEquals(END_TEXT_VERY_BAD.getEndText(), d.getGameEndText().getEndText());
          Assertions.assertEquals(END_TEXT_VERY_BAD.getEndTitle(), d.getGameEndText().getEndTitle());
        }
        case LOOSE -> {
          Assertions.assertEquals(END_TEXT_LOOSE.getEndText(), d.getGameEndText().getEndText());
          Assertions.assertEquals(END_TEXT_LOOSE.getEndTitle(), d.getGameEndText().getEndTitle());
        }
      }
    }
  }

  private static Stream<Arguments> provideRequestDtoParameter() {
    return Stream.of(
            Arguments.of(ParameterState.RANDOM, GameLevel.EASY),
            Arguments.of(ParameterState.RANDOM, GameLevel.MEDIUM),
            Arguments.of(ParameterState.RANDOM, GameLevel.DIFFICULT),
            Arguments.of(ParameterState.FIX, GameLevel.EASY),
            Arguments.of(ParameterState.FIX, GameLevel.MEDIUM),
            Arguments.of(ParameterState.FIX, GameLevel.DIFFICULT)
    );
  }

  private IGenerateMemoryCardGameInput generateNewGameRequest(GameLevel gameLevel, ParameterState parameterState) {
    return new IGenerateMemoryCardGameInput() {
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
