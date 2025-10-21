package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand;

import com.ctoutweb.aet.domain.entity.LevelType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.GameLevel;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.OperatorParameterFactory;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.OperatorParameter;
import com.ctoutweb.aet.domain.injector.MethodInjectorContainer;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.ICardFaceIdent;
import com.ctoutweb.aet.domain.util.IEventBus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.ctoutweb.aet.domain.provider.CoreFactory.MENTAL_CALCUL_INSTANCE_PROVIDER;

class OperandManagerTest {

  OperandAssociatedToPriorityOperator operandAssociatedToPriorityOperator;
  OperandManager operandManager;

  @Mock
  IEventBus eventBus;

  CalculParameter calculParameter = GameLevel.EASY.getParameter();

  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);

    operandAssociatedToPriorityOperator = MENTAL_CALCUL_INSTANCE_PROVIDER.provideOperandAssociatedToPriorityOperatorInstance();
    operandManager = MENTAL_CALCUL_INSTANCE_PROVIDER.provideOperandManagerInstance(calculParameter, eventBus);
  }

  @Test
  void calculPriorityOperator_zero_priority_operator() {
    /**
     * Given
     * Calcul 2 + 2
     */
    List<Integer> numerals = List.of(2, 2);
    List<OperatorType> operators = new ArrayList<>();
    operators.add(OperatorType.ADDITION);

    /**
     * When
     */
    var calculPriorityOperators = operandAssociatedToPriorityOperator
            .calculPriorityOperatorResult(operators, numerals)
            .getPriortyOperatorCalculResults();

    /**
     * Then
     */
    Assertions.assertEquals(0, calculPriorityOperators.size());
  }

  @Test
  void calculPriorityOperator_one_priority_operator() {
     /**
     * Given
     * Calcul 2 * 2
     */
    List<Integer> numerals = List.of(2, 2);
    List<OperatorType> operators = new ArrayList<>();
    operators.add(OperatorType.MULTIPLICATION);

    /**
     * When
     */
    var calculPriorityOperators = operandAssociatedToPriorityOperator
            .calculPriorityOperatorResult(operators, numerals)
            .getPriortyOperatorCalculResults();

    /**
     * Then
     */
    Assertions.assertEquals(1, calculPriorityOperators.size());
    Assertions.assertEquals(4, calculPriorityOperators.get(0).getValue());
  }

  @Test
  void calculPriorityOperator_two_priority_operator() {
    /**
     * Given
     * Calcul 2 * 2 + 2 * 2
     */
    List<Integer> numerals = List.of(2, 2, 2, 2);
    List<OperatorType> operators = new ArrayList<>();
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.ADDITION);
    operators.add(OperatorType.MULTIPLICATION);

    /**
     * When
     */
    var calculPriorityOperators = operandAssociatedToPriorityOperator
            .calculPriorityOperatorResult(operators, numerals)
            .getPriortyOperatorCalculResults();

    /**
     * Then
     */
    Assertions.assertEquals(2, calculPriorityOperators.size());
    Assertions.assertEquals(4, calculPriorityOperators.get(0).getValue());
    Assertions.assertEquals(4, calculPriorityOperators.get(1).getValue());

    /**
     * Given
     * Calcul 2 * 2 * 2
     */
    numerals = List.of(2, 2, 2);
    operators = new ArrayList<>();
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.MULTIPLICATION);

    /**
     * When
     */
    calculPriorityOperators = operandAssociatedToPriorityOperator
            .calculPriorityOperatorResult(operators, numerals)
            .getPriortyOperatorCalculResults();

    /**
     * Then
     */
    Assertions.assertEquals(1, calculPriorityOperators.size());
    Assertions.assertEquals(8, calculPriorityOperators.get(0).getValue());

    /**
     * Given
     * Calcul 2 / 2 * 2
     */
    numerals = List.of(2, 2, 2);
    operators = new ArrayList<>();
    operators.add(OperatorType.DIVISION);
    operators.add(OperatorType.MULTIPLICATION);

    /**
     * When
     */
    calculPriorityOperators = operandAssociatedToPriorityOperator
            .calculPriorityOperatorResult(operators, numerals)
            .getPriortyOperatorCalculResults();

    /**
     * Then
     */
    Assertions.assertEquals(1, calculPriorityOperators.size());
    Assertions.assertEquals(2, calculPriorityOperators.get(0).getValue());
  }

  @Test
  void calculPriorityOperator_tree_priority_operator() {
    /**
     * Given
     * Calcul 1 + 2 * 2 * 2 - 1 * 2
     */
    List<Integer> numerals = List.of(1, 2, 2, 2, 1, 2);
    List<OperatorType> operators = new ArrayList<>();
    operators.add(OperatorType.ADDITION);
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.SOUSTRACTION);
    operators.add(OperatorType.MULTIPLICATION);

    /**
     * When
     */
    var calculPriorityOperators = operandAssociatedToPriorityOperator
            .calculPriorityOperatorResult(operators, numerals)
            .getPriortyOperatorCalculResults();

    /**
     * Then
     */
    Assertions.assertEquals(2, calculPriorityOperators.size());
    Assertions.assertEquals(8, calculPriorityOperators.get(0).getValue());
    Assertions.assertEquals(2, calculPriorityOperators.get(1).getValue());

    /**
     * Given
     * Calcul 1 * 2 * 2 + 2 - 8 * 2
     */
    numerals = List.of(1, 2, 2, 2, 8, 2);
    operators = new ArrayList<>();
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.ADDITION);
    operators.add(OperatorType.SOUSTRACTION);
    operators.add(OperatorType.MULTIPLICATION);

    /**
     * When
     */
    calculPriorityOperators = operandAssociatedToPriorityOperator
            .calculPriorityOperatorResult(operators, numerals)
            .getPriortyOperatorCalculResults();

    /**
     * Then
     */
    Assertions.assertEquals(2, calculPriorityOperators.size());
    Assertions.assertEquals(4, calculPriorityOperators.get(0).getValue());
    Assertions.assertEquals(16, calculPriorityOperators.get(1).getValue());

    /**
     * Given
     * Calcul 4 * 2 * 2 * 2
     */
    numerals = List.of(4, 2, 2, 2);
    operators = new ArrayList<>();
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.MULTIPLICATION);

    /**
     * When
     */
    calculPriorityOperators = operandAssociatedToPriorityOperator
            .calculPriorityOperatorResult(operators, numerals)
            .getPriortyOperatorCalculResults();

    /**
     * Then
     */
    Assertions.assertEquals(1, calculPriorityOperators.size());
    Assertions.assertEquals(32, calculPriorityOperators.get(0).getValue());

    /**
     * Given
     * Calcul 4 * 2 / 2 * 2
     */
    numerals = List.of(4, 2, 2, 2);
    operators = new ArrayList<>();
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.DIVISION);
    operators.add(OperatorType.MULTIPLICATION);

    /**
     * When
     */
    calculPriorityOperators = operandAssociatedToPriorityOperator
            .calculPriorityOperatorResult(operators, numerals)
            .getPriortyOperatorCalculResults();

    /**
     * Then
     */
    Assertions.assertEquals(1, calculPriorityOperators.size());
    Assertions.assertEquals(8, calculPriorityOperators.get(0).getValue());
  }

  @Test
  void calculPriorityOperator_four_priority_operator() {
    /**
     * Given
     * Calcul 2 * 2 + 2 - 2 * 2 * 2 + 2 * 2
     */
    List<Integer> numerals = List.of(2, 2, 2, 2, 2, 2, 2, 2);
    List<OperatorType> operators = new ArrayList<>();
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.ADDITION);
    operators.add(OperatorType.SOUSTRACTION);
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.ADDITION);
    operators.add(OperatorType.MULTIPLICATION);

    /**
     * When
     */
    var calculPriorityOperators = operandAssociatedToPriorityOperator
            .calculPriorityOperatorResult(operators, numerals)
            .getPriortyOperatorCalculResults();

    /**
     * Then
     */
    Assertions.assertEquals(3, calculPriorityOperators.size());
    Assertions.assertEquals(4, calculPriorityOperators.get(0).getValue());
    Assertions.assertEquals(8, calculPriorityOperators.get(1).getValue());
    Assertions.assertEquals(4, calculPriorityOperators.get(2).getValue());

    /**
     * Given
     * Calcul 2 * 2 * 2 * 2 * 2
     */
    numerals = List.of(2, 2, 2, 2, 2);
    operators = new ArrayList<>();
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.MULTIPLICATION);

    /**
     * When
     */
    calculPriorityOperators = operandAssociatedToPriorityOperator
            .calculPriorityOperatorResult(operators, numerals)
            .getPriortyOperatorCalculResults();

    /**
     * Then
     */
    Assertions.assertEquals(1, calculPriorityOperators.size());
    Assertions.assertEquals(32, calculPriorityOperators.get(0).getValue());
  }

  @Test
  void calculateResult() {

    MethodInjectorContainer.getInstance().register(ICardFaceIdent.class, new ICardFaceIdent() {
      @Override
      public String getCardIdent() {
        return "test";
      }
    });

    /**
     * Given
     * Calcul 2 * 2 + 2 - 2 * 2 * 2 + 2 * 2
     */
    List<Integer> numerals = List.of(2, 2, 2, 2, 2, 2, 2, 2);
    List<OperatorType> operators = new ArrayList<>();
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.ADDITION);
    operators.add(OperatorType.SOUSTRACTION);
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.ADDITION);
    operators.add(OperatorType.MULTIPLICATION);

    var generatedOperandData1 = operandManager
            .calculateOperandResult(operators, numerals)
            .getGeneratedOperation();

    /**
     * Given
     * Calcul 2 * 2 * 2 * 2 * 2
     */
    numerals = List.of(2, 2, 2, 2, 2);
    operators = new ArrayList<>();
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.MULTIPLICATION);

    /**
     * When
     */
    var generatedOperandData2 = operandManager
            .calculateOperandResult(operators, numerals)
            .getGeneratedOperation();

    /**
     * Given
     * Calcul 1 + 2 * 2 * 2 - 1 * 2
     */
    numerals = List.of(1, 2, 2, 2, 1, 2);
    operators = new ArrayList<>();
    operators.add(OperatorType.ADDITION);
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.MULTIPLICATION);
    operators.add(OperatorType.SOUSTRACTION);
    operators.add(OperatorType.MULTIPLICATION);

    /**
     * When
     */
    var generatedOperandData3 = operandManager
            .calculateOperandResult(operators, numerals)
            .getGeneratedOperation();

    /**
     * then - Vérification du resultat
     */
    Assertions.assertNotNull(generatedOperandData1);
    Assertions.assertEquals(2, generatedOperandData1.getValidOperationResponse());
    Assertions.assertEquals(32, generatedOperandData2.getValidOperationResponse());
    Assertions.assertEquals(7, generatedOperandData3.getValidOperationResponse());
  }

  @ParameterizedTest
  @MethodSource("provideOperatorParameterList")
  void verify_calculated_last_digit(List<OperatorParameter> operatorParameters) {
    /**
     * when
     */
    var result = operandManager.generateOperands(operatorParameters, 1);

    /**
     * then
     */
    Assertions.assertEquals(operatorParameters.size() + 1, result.getInitialOperands().size());
    result.getInitialOperands().forEach(operand -> {
      Assertions.assertTrue(calculParameter.getLastCalculatedDigitAcceptedList().contains(operand.doubleValue() % 10));
    });
  }

  @ParameterizedTest
  @MethodSource("provideOperatorParameterList")
  void verify_proposal_responses(List<OperatorParameter> operatorParameters) {
    /**
     * Given
     */
    MethodInjectorContainer.getInstance().register(ICardFaceIdent.class, new ICardFaceIdent() {
      @Override
      public String getCardIdent() {
        return "test";
      }
    });

    /**
     * when
     */
    var actualOperandInCalcul = operandManager
            .generateOperands(operatorParameters, 1)
            .calculateOperandResult(operandManager.getInitialOperators(), operandManager.getInitialOperands())
            .generateProposalResponse(4)
            .getGeneratedOperation();

    /**
     * then
     */
    var proposalResponses = actualOperandInCalcul.getProposalResponses();
    var calculResult = actualOperandInCalcul.getValidOperationResponse();
    Assertions.assertEquals(4, proposalResponses.size());
    Assertions.assertEquals(1, proposalResponses
            .stream()
            .filter(proposal -> proposal.proposalResponse() == calculResult)
            .count());

  }

  private static Stream<Arguments> provideOperatorParameterList() {
    OperatorParameter additionParameter = OperatorParameterFactory.loadOperatorParamter(OperatorType.ADDITION, LevelType.EASY);
    OperatorParameter soustractionParameter = OperatorParameterFactory.loadOperatorParamter(OperatorType.SOUSTRACTION, LevelType.EASY);
    OperatorParameter multiplicationParameter = OperatorParameterFactory.loadOperatorParamter(OperatorType.MULTIPLICATION, LevelType.EASY);

    return Stream.of(
            Arguments.of(List.of(additionParameter)),
            Arguments.of(List.of(additionParameter, soustractionParameter)),
            Arguments.of(List.of(additionParameter, soustractionParameter, multiplicationParameter))
    );
  }
}