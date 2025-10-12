package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter;

import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorType;

/**
 * Données permettant de parametrer un opération
 */
public class OperatorParameter {
  private final OperatorType operatorType;
  private final IMinAndMax<Integer> minAndMaxNumberForCalcul;
  private final int operatorPresencePercent;

  public OperatorParameter(
          OperatorType operationType,
          IMinAndMax<Integer> minAndMaxNumberForCalcul,
          int operationPresenceInPourcentage) {
    this.operatorType = operationType;
    this.minAndMaxNumberForCalcul = minAndMaxNumberForCalcul;
    this.operatorPresencePercent = operationPresenceInPourcentage;
  }
  public OperatorType getOperatorType() {
    return operatorType;
  }

  public IMinAndMax<Integer> getMinAndMaxNumberForCalcul() {
    return minAndMaxNumberForCalcul;
  }

  public int getOperatorPresencePercent() {
    return operatorPresencePercent;
  }
}
