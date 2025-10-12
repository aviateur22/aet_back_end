package com.ctoutweb.aet.domain.entity.generateMentalCalculGame;

public enum OperatorType {
  ADDITION("+", "+"),
  SOUSTRACTION("-", "-"),
  MULTIPLICATION("x", "*"),
  DIVISION("%", "/");

  private String operationSign;
  private String operationMath;

  private OperatorType(String operationSign, String operationMath) {
    this.operationSign = operationSign;
    this.operationMath = operationMath;
  }

  /**
   * Renvoie le String correspondant à l'operation
   * @return String - L'operateur aritmetique
   */
  public String getOperationSign() {
    return this.operationSign;
  }
}
