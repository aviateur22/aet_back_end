package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand;

/**
 * Model final du calcul de l'operation et de la validité de toutes les operations intermédiaies
 *
 * @param calculResult Le resultat final de l'operation
 * @param areAllIntermediateCalculValid Treu si tous les calcul intermédiaires respectent les ocntraintes
 */
public record CalculOperandResult(double calculResult, boolean areAllIntermediateCalculValid) {
}
