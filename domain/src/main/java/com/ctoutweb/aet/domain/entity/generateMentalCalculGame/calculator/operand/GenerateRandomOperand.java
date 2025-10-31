package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand;

import com.ctoutweb.aet.domain.annotation.InjectConstructorParam;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.paramter.CalculParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.paramter.OperatorParameter;
import com.ctoutweb.aet.domain.util.NumberUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Génération d'operand pour un calcul.
 */
public class GenerateRandomOperand {
    private final CalculParameter calculParameter;

    public GenerateRandomOperand(@InjectConstructorParam CalculParameter calculParameter) {
        this.calculParameter = calculParameter;
    }


    public List<Integer> generateCalculOperand(List<OperatorParameter> initialOperatorParameters) {
        List<Integer> operandList = new ArrayList<>();
        final int operandQuantityInCalcul = initialOperatorParameters.size() + 1;

        for(int i = 0; i < operandQuantityInCalcul; i++) {
            if(i == 0) {
                operandList.add(generateRandomOperand(initialOperatorParameters.get(0)));
            } else {
                operandList.add(generateRandomOperand(initialOperatorParameters.get(i-1)));
            }
        }
        return operandList;
    }

    /**
     * Generation d'un entier aléatoir en fonction de l'operator.
     * @param activeOperatorParameter - OperatorParameter - Operator permettant de délimiter les bornes min et max
     * @return int - Operand aléatoire du calcul
     */
    private int generateRandomOperand(OperatorParameter activeOperatorParameter) {
        int minAcceptedOperandValue = activeOperatorParameter.getMinAndMaxNumberForCalcul().getMin();
        int maxAcceptedOperandValue = activeOperatorParameter.getMinAndMaxNumberForCalcul().getMax();

        int generatedOperand;

        do {
            generatedOperand = NumberUtil.generateRandomNumberBetweenMinAndMax(minAcceptedOperandValue, maxAcceptedOperandValue);
        } while (!isRandomOperandValid(generatedOperand));

        return generatedOperand;
    }

    private boolean isRandomOperandValid(Integer operand) {
        var operandDouble = operand.doubleValue();
        return calculParameter.getLastCalculatedDigitAcceptedList().contains(operandDouble % 10);
    }
}
