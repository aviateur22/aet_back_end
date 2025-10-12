package com.ctoutweb.aet.domain.usecase;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.GameLevel;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.Operation;
import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.difficultLevel.DifficultLevelCalculParameter;
import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.easyLevel.EasyLevelCalculParameter;
import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.meduimLevel.MeduimLevelCalculParameter;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.stream.Stream;

public class GenerateMentalCalculGameUseCaseTest {
  GenerateMentalCalculGameUseCase generateMentalCalculGameUseCase;

  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);
    this.generateMentalCalculGameUseCase = new GenerateMentalCalculGameUseCase();
  }

  @ParameterizedTest
  @MethodSource("provideRequestDtoParameter")
  public void generate_mental_calcul_game_easyLevel(GameLevel gameLevel) {

    /**
     * Given
     */
    IGenerateMentalCalculGameInput useCaseInput = this.generateMentalCalculGameInput(gameLevel);
    GenerateMentalCalculGameUseCase.Input input = new GenerateMentalCalculGameUseCase.Input(useCaseInput);

    /**
     * when
     */
    var output = this.generateMentalCalculGameUseCase.execute(input);

    /**
     * then
     */
    var generatedData = output.generateMentalCalculGameOutput();
    Assertions.assertNotNull(generatedData);
    Assertions.assertNotNull(generatedData.getOperations());
    Assertions.assertTrue(isLastDigitValid(gameLevel, generatedData.getOperations()));


  }

  @Test
  public void last_digit_should_be_not_valid() {
    /**
     * Given
     */
    GameLevel gameLevel = GameLevel.EASY;
    int numberTest1 = 3;
    int numberTest2 = 15;
    int numberTest3 = 27;
    int numberTest4 = 39;

    /**
     * When
     */
    boolean isNumberTest1Valid = this.lastNumerberCheckFactory(gameLevel, numberTest1);
    boolean isNumberTest2Valid = this.lastNumerberCheckFactory(gameLevel, numberTest2);
    boolean isNumberTest3Valid = this.lastNumerberCheckFactory(gameLevel, numberTest3);
    boolean isNumberTest4Valid = this.lastNumerberCheckFactory(gameLevel, numberTest4);


    /**
     * Then
     */
    Assertions.assertFalse(isNumberTest1Valid);
    Assertions.assertFalse(isNumberTest2Valid);
    Assertions.assertFalse(isNumberTest3Valid);
    Assertions.assertFalse(isNumberTest4Valid);
  }

  @Test
  public void last_digit_should_be_valid() {
    /**
     * Given
     */
    GameLevel gameLevel = GameLevel.EASY;
    int numberTest1 = 0;
    int numberTest2 = 2;
    int numberTest3 = 4;
    int numberTest4 = 6;
    int numberTest5= 8;

    /**
     * When
     */
    boolean isNumberTest1Valid = this.lastNumerberCheckFactory(gameLevel, numberTest1);
    boolean isNumberTest2Valid = this.lastNumerberCheckFactory(gameLevel, numberTest2);
    boolean isNumberTest3Valid = this.lastNumerberCheckFactory(gameLevel, numberTest3);
    boolean isNumberTest4Valid = this.lastNumerberCheckFactory(gameLevel, numberTest4);
    boolean isNumberTest5Valid = this.lastNumerberCheckFactory(gameLevel, numberTest5);

    /**
     * Then
     */
    Assertions.assertTrue(isNumberTest1Valid);
    Assertions.assertTrue(isNumberTest2Valid);
    Assertions.assertTrue(isNumberTest3Valid);
    Assertions.assertTrue(isNumberTest4Valid);
    Assertions.assertTrue(isNumberTest5Valid);

  }


  private IGenerateMentalCalculGameInput generateMentalCalculGameInput(GameLevel gameLevel) {
    return new IGenerateMentalCalculGameInput() {
      @Override
      public GameLevel getGameLevel() {
        return gameLevel;
      }

      @Override
      public List<Operation> getOpertionCalculs() {
        return null;
      }
    };
  }

  private static Stream<Arguments> provideRequestDtoParameter() {
    return Stream.of(
            Arguments.of(GameLevel.EASY),
            Arguments.of(GameLevel.MEDIUM),
            Arguments.of(GameLevel.DIFFICULT)
    );
  }

  private boolean isLastDigitValid(GameLevel gameLevel, List<Operation> operations) {
    return operations.stream().allMatch(operation -> {
      boolean areLastDigitOfMentalNumberValid = operation.mentalNumbers().stream().allMatch(num->lastNumerberCheckFactory(gameLevel, num.number()));
      boolean isLastDigitOfOperationResponseValid = lastNumerberCheckFactory(gameLevel, operation.validOperationResponse());
      return areLastDigitOfMentalNumberValid && isLastDigitOfOperationResponseValid;
    });
  }

  private boolean lastNumerberCheckFactory(GameLevel gameLevel, int numberToCheck) {
    return switch (gameLevel) {
      case EASY -> EasyLevelCalculParameter.LAST_CALCULTED_DIGIT_ACCEPEDTED_LIST.contains(Math.abs(numberToCheck % 10));
      case MEDIUM -> MeduimLevelCalculParameter.LAST_CALCULTED_DIGIT_ACCEPEDTED_LIST.contains(Math.abs(numberToCheck % 10));
      case DIFFICULT -> DifficultLevelCalculParameter.LAST_CALCULTED_DIGIT_ACCEPEDTED_LIST.contains(Math.abs(numberToCheck % 10));
    };
  }

}
