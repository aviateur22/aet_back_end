package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand;

import com.ctoutweb.aet.domain.annotation.InjectConstructorParam;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.validator.ValidationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OperandAssociatedToPriorityOperator {
    private final ValidationManager validationManager;


    /**
     * Une Liste contenant les resultats des calculs des operateurs prioritaire MULTIPLICATION ou DIVISION
     * Cette liste est composé de MAP.ENTRY avec comme definition:
     * - Integer: Représente la position qui est occupé par ce résulat dans la liste initiale des opérandes
     * - Double: Le résulat du calcul
     */
    private List<Map.Entry<Integer, Double>> priortyOperatorCalculResults;

    /**
     * Une liste regroupant les index des opérandes qui sont à supprimer de la liste initiale des opérandes
     * Pour info: Ces opérandes sont liés a des calculs prioritaire, les résulat de ces calcul sont contenus  dans priortyOperatorCalculResults
     *
     * @see #calculPriorityOperatorResult(List, List) - Calcul des resulats des opérateur prioritaires
     */
    private List<Integer> initialOperandToRemoveIndexes;

    /**
     * Les operations prioritaires respecte t-elle les contraintes
     */
    boolean arePrioritoyOperationResultValid;

    public OperandAssociatedToPriorityOperator(@InjectConstructorParam ValidationManager validationManager) {
        this.validationManager = validationManager;
    }

    public List<Map.Entry<Integer, Double>> getPriortyOperatorCalculResults() {
        return priortyOperatorCalculResults;
    }


    /**
     * Calcul des operandes associées au operateurx prioritaire MULTIPCATION ou DIVISION
     *
     * @param operators Liste des operatreurs qui ont été générérs
     * @param numerals List des numerateurs qui ont été générés
     *
     * @return OperandAssociatedToPriorityOperator
     */
    public OperandAssociatedToPriorityOperator calculPriorityOperatorResult(List<OperatorType> operators, List<Integer> numerals) {
        List<Map.Entry<Integer, OperatorType>> operatorPriorityIndex = new ArrayList<>();
        this.priortyOperatorCalculResults = new ArrayList<>();
        this.initialOperandToRemoveIndexes = new ArrayList<>();

        // Recherche Operator prioritaire
        for (int j = 0; j < operators.size(); j++) {
            if (operators.get(j) == OperatorType.MULTIPLICATION || operators.get(j) == OperatorType.DIVISION) {
                operatorPriorityIndex.add(Map.entry(j, operators.get(j)));
            }
        }

        int lastOperatorPosition = -10;
        double lastOperationResult = 0;

        boolean isOperationResultValid = true;

        for (int j = 0; j < operatorPriorityIndex.size(); j++) {
            int operatorPosition = operatorPriorityIndex.get(j).getKey();
            // Suppression
            OperatorType operator = operatorPriorityIndex.get(j).getValue();

            if (lastOperatorPosition == operatorPosition - 1) {
                double result = CalculateOperation.calculateOperationResult(operator, lastOperationResult, numerals.get(operatorPosition + 1));

                if(!validationManager.areIntermediateCalculValid(result))
                    isOperationResultValid = false;

                lastOperationResult = result;
                initialOperandToRemoveIndexes.add(operatorPosition + 1);
                if(isPriorityCalculResultToBeAdd(j, operatorPriorityIndex, operatorPosition)) {
                    priortyOperatorCalculResults.add(Map.entry(operatorPosition, result));
                }

            } else if (operatorPosition == 0) {
                double result = CalculateOperation.calculateOperationResult(operator, numerals.get(0), numerals.get(1));

                if(!validationManager.areIntermediateCalculValid(result))
                    isOperationResultValid = false;

                lastOperationResult = result;
                initialOperandToRemoveIndexes.add(operatorPosition);
                initialOperandToRemoveIndexes.add(operatorPosition + 1);
                if(isPriorityCalculResultToBeAdd(j, operatorPriorityIndex, operatorPosition)) {
                    priortyOperatorCalculResults.add(Map.entry(operatorPosition, result));
                }

            } else {
                double result = CalculateOperation.calculateOperationResult(operator, numerals.get(operatorPosition), numerals.get(operatorPosition + 1));

                if(!validationManager.areIntermediateCalculValid(result))
                    isOperationResultValid = false;

                lastOperationResult = result;
                initialOperandToRemoveIndexes.add(operatorPosition);
                initialOperandToRemoveIndexes.add(operatorPosition + 1);
                if(isPriorityCalculResultToBeAdd(j, operatorPriorityIndex, operatorPosition)) {
                    priortyOperatorCalculResults.add(Map.entry(operatorPosition, result));
                }
            }
            lastOperatorPosition = operatorPosition;
        }
        this.arePrioritoyOperationResultValid = isOperationResultValid;
        return this;
    }

    /**
     * Regroupement des calculs operators prioritaires entre eux
     * ex: 1 + 3 * 3 * 3 + 1 . alors 3 * 3 * 3 sera groupé pour être calculé
     *
     * @param j Position de l'opérateur prioritaire dans les liste des operateurs prioritaires
     * @param operatorPriorityIndex Map avec l'index de l'operataire prioritaire
     * @param operatorPosition
     * @return
     */
    boolean isPriorityCalculResultToBeAdd(int j , List<Map.Entry<Integer, OperatorType>> operatorPriorityIndex, int operatorPosition) {
        if(j < operatorPriorityIndex.size() - 1) {
            int nextOperatorPosition = operatorPriorityIndex.get(j + 1).getKey();
            return operatorPosition + 1 < nextOperatorPosition;
        } else  {
            return true;
        }
    }

    /**
     * Mise à jour des operands initial
     * Tous les operands associés a un operateur prioritaire sont remplacé par le resultat de leur calcul
     *
     * @param initialOperands - List<Integer> initialOperands qui sera mis à jour
     *
     * @return List<Integer> - Liste des operandes mis à jour
     */
    public PriorityOperatorResult updateInitialOperandsWithPriorityOperatorResult(List<Integer> initialOperands) {
        List<Double> updatedOperands = new ArrayList<>();

        if(priortyOperatorCalculResults.isEmpty())
            return new PriorityOperatorResult(
                    this.arePrioritoyOperationResultValid,
                    initialOperands.stream().map(Integer::doubleValue).toList()
            );

        int nextPriorityIndex = priortyOperatorCalculResults.get(0).getKey();
        double nextPriorityResult = priortyOperatorCalculResults.get(0).getValue();

        int j = 0;
        for (int i = 0; i < initialOperands.size(); i++) {

            if (i < nextPriorityIndex && !initialOperandToRemoveIndexes.contains(i)) {
                updatedOperands.add(initialOperands.get(i).doubleValue());
            } else if(i > nextPriorityIndex){
                updatedOperands.add(nextPriorityResult);
                if(j < priortyOperatorCalculResults.size() - 1) {
                    j += 1;
                    nextPriorityIndex = priortyOperatorCalculResults.get(j).getKey();
                    nextPriorityResult = priortyOperatorCalculResults.get(j).getValue();
                }
            }
        }

        return new PriorityOperatorResult(this.arePrioritoyOperationResultValid, updatedOperands);
    }
}
