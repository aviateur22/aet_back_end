package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.CardFaceIdent;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.GeneratedOperation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.MentalCalculGame;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.TimeUnit;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand.CalculOperandResult;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand.OperandManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operation.OperationManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operator.OperatorInCalcul;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operator.OperatorManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.validator.ValidationManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText.GameTextManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.IOperation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.Operation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.TimeToCalculate;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.paramter.CalculParameter;
import com.ctoutweb.aet.domain.usecase.GenerateMentalCalculGameUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;

public class MentalCalculGameTest {

    @Mock
    private OperationManager operationManager;

    @Mock
    private OperatorManager operatorManager;

    @Mock
    private OperandManager operandManager;

    @Mock
    private ValidationManager validationManager;

    @Mock
    private GameTextManager gameTextManager;

    @Mock
    private CalculParameter calculParameter;

    @Mock
    private IOperation operation;

    @InjectMocks
    private MentalCalculGame mentalCalculGame;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateOperandForCalculGame_should_create_one_operation() {
        /**
         * Given
         */
        // Mock de la liste operatorsInCalculs
        OperatorInCalcul mockOperatorInCalcul = mock(OperatorInCalcul.class);
        mentalCalculGame.getOperatorsInCalculs().add(mockOperatorInCalcul);

        // Mock les differentes methodes utilisées dans la class OperandManager
        Mockito.when(operandManager.reinitializeOperandManager())
                .thenReturn(operandManager);

        Mockito.when(operandManager.generateOperands(anyList()))
                .thenReturn(operandManager);

        Mockito.when(operandManager.calculateOperandResult(anyList(), anyList()))
                .thenReturn(new CalculOperandResult(10, true));

        // Mock les differentes methodes utilisées dans la class OperationManager
        Mockito.when(operationManager.getGeneratedOperation(anyInt(), anyInt(),anyDouble(),anyList(),anyList(),anyList()))
                .thenReturn(Mockito.mock(Operation.class));

        // Mock les differentes methodes utilisées dans la class ValidationManager
        Mockito.when(validationManager.isGeneratedOperationValid(any(IOperation.class)))
                .thenReturn(true);

        /**
         * when
         */
        MentalCalculGame mentalCalculGameWithOperationData = mentalCalculGame.generateOperandForCalculGame();
        GenerateMentalCalculGameUseCase.Output outputResult = mentalCalculGameWithOperationData.getGeneratedGameData();

        /**
         * Then
         */
        int generatedOperationQuantity = outputResult.generateMentalCalculGameOutput().getOperations().size();
        Assertions.assertEquals(1, generatedOperationQuantity);
    }

    @Test
    void generateOperandForCalculGame_should_load_default_operation_on_generation_failed() {
        /**
         * Given
         */
        // Mock de la liste operatorsInCalculs
        OperatorInCalcul mockOperatorInCalcul1 = mock(OperatorInCalcul.class);
        OperatorInCalcul mockOperatorInCalcul2 = mock(OperatorInCalcul.class);
        OperatorInCalcul mockOperatorInCalcul3 = mock(OperatorInCalcul.class);

        mentalCalculGame.getOperatorsInCalculs().add(mockOperatorInCalcul1);
        mentalCalculGame.getOperatorsInCalculs().add(mockOperatorInCalcul2);
        mentalCalculGame.getOperatorsInCalculs().add(mockOperatorInCalcul3);


        // Mock les differentes methodes utilisées dans la class OperandManager
        Mockito.when(operandManager.reinitializeOperandManager())
                .thenReturn(operandManager);

        Mockito.when(operandManager.generateOperands(anyList()))
                .thenReturn(operandManager);

        Mockito.when(operandManager.calculateOperandResult(anyList(), anyList()))
                .thenReturn(new CalculOperandResult(10, false));

        // Mock les differentes methodes utilisées dans la class OperationManager
        Mockito.when(operationManager.getGeneratedOperation(anyInt(), anyInt(),anyDouble(),anyList(),anyList(),anyList()))
                .thenReturn(Mockito.mock(Operation.class));

        Mockito.when(operationManager.generateDefaultOperation(anyInt(), anyInt()))
                .thenReturn(new Operation()
                        .setTimeToCalculate(12)
                        .setValidOperationResponse(100)
                );


        // Mock les differentes methodes utilisées dans la class ValidationManager
        Mockito.when(validationManager.isGeneratedOperationValid(any(IOperation.class)))
                .thenReturn(false);

        /**
         * when
         */
        MentalCalculGame mentalCalculGameWithOperationData = mentalCalculGame.generateOperandForCalculGame();

        // Operations générés
        var generatedOperations = mentalCalculGameWithOperationData
                .getGeneratedGameData()
                .generateMentalCalculGameOutput()
                .getOperations();

        /**
         * Then
         */
        int generatedOperationQuantity = generatedOperations.size();
        Assertions.assertEquals(3, generatedOperationQuantity);
        generatedOperations.forEach(operation -> {
                Assertions.assertEquals(100, operation.getValidOperationResponse());
                Assertions.assertEquals(new TimeToCalculate(TimeUnit.SEC.name(), 12), operation.getTimeToCalculate());
        });
    }
}
