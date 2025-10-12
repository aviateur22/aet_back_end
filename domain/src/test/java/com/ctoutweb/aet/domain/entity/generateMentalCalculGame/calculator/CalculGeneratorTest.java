package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.GameLevel;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;
import com.ctoutweb.aet.domain.util.IEventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.stream.Stream;

import static com.ctoutweb.aet.domain.provider.CoreFactory.MENTAL_CALCUL_INSTANCE_PROVIDER;

class CalculGeneratorTest {

  @Mock
  IEventBus eventBus;


  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);
  }

  @ParameterizedTest
  @MethodSource("provideCalcuParameter")
  void determineOperatorQuantityInCalculGame(CalculParameter calculParameter) {
    /**
     * Given
     */
    CalculGenerator calculGenerator = MENTAL_CALCUL_INSTANCE_PROVIDER.provideCalculGeneratorInstance(calculParameter, eventBus);

    /**
     * when
     */
    calculGenerator.generateCalculGame();

    /**
     * then
     */

  }

  private static Stream<Arguments> provideCalcuParameter() {
    return Stream.of(
            Arguments.of(GameLevel.EASY.getParameter()),
            Arguments.of(GameLevel.MEDIUM.getParameter()),
            Arguments.of(GameLevel.DIFFICULT.getParameter())
    );
  }
}