package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operator;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.CalculParameterFactory;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.GameLevel;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.ctoutweb.aet.domain.provider.CoreFactory.MENTAL_CALCUL_INSTANCE_PROVIDER;

public class OperatorManagerTest {
    @ParameterizedTest
    @MethodSource("provideCalcuParameter")
    void determineOperatorQuantityInCalculGame(CalculParameter calculParameter) {
        /**
         * Given
         */
        OperatorManager operatorManager = MENTAL_CALCUL_INSTANCE_PROVIDER.provideOperatorManagerInstance(calculParameter);

        /**
         * when
         */
        var opertorsInGame = operatorManager
                .determineOperatorQuantityInCalculGame()
                .loadOperatorInCalculGame()
                .getOperationInCalculs();

        /**
         * then
         */
        Assertions.assertEquals(opertorsInGame.size(), calculParameter.getCalculQuantity());

        // Vérification cohérence entre le nombre opérator / list opérator
        int minOperatorInCalcul = calculParameter.getMinOperatorByCalcul();
        int maxOperatorInCalcul = calculParameter.getMaxOperatorByCalcul();
        List<OperatorType> acceptedOperatorAssociationList = calculParameter.getAcceptedOperatorAssociationList();
        opertorsInGame.forEach(operationInCalcul -> {
            int operatorQuantity = operationInCalcul.getOperatorQuantity();
            long operatorQuantityListLength = operationInCalcul.getOperators().size();
            Assertions.assertEquals(operatorQuantity, operatorQuantityListLength);
            Assertions.assertTrue(operatorQuantity <= maxOperatorInCalcul);
            Assertions.assertTrue(operatorQuantity >= minOperatorInCalcul);

            // Si il y a plus de 1 operateur on vérifie qu'ils peuvent être mixé
            if(operatorQuantity > 1) {
                operationInCalcul.getOperators().forEach(operator -> {
                    Assertions.assertTrue(acceptedOperatorAssociationList.contains(operator.getOperatorType()));
                });
            }
        });

    }

    private static Stream<Arguments> provideCalcuParameter() {
        return Stream.of(
                Arguments.of(CalculParameterFactory.loadCalculParameterByLevel(GameLevel.EASY)),
                Arguments.of(CalculParameterFactory.loadCalculParameterByLevel(GameLevel.MEDIUM)),
                Arguments.of(CalculParameterFactory.loadCalculParameterByLevel(GameLevel.DIFFICULT))
        );
    }
}
