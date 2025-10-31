package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand;

/**
 * Model permettant de stocker le resulatat final ainsi que la validité des calculul intermlédiaire lié au clacul des operateur
 * non prioritaire
 *
 * @param areLowPriorityOperatorCalculResultValid
 * @param finalCalculResult
 */
public record LowPriorityOperatorResult(boolean areLowPriorityOperatorCalculResultValid, double finalCalculResult) {
}
