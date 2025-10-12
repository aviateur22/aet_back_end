package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorType;

public class OperatorCalculFactory {
  private OperatorCalculFactory() {
    throw new IllegalStateException("Utility class");
  }
  public static double calculateOperationResult(OperatorType operator, double firstNumeral, double secondNumeral) {
    return switch (operator) {
      case ADDITION -> firstNumeral + secondNumeral;
      case SOUSTRACTION -> firstNumeral - secondNumeral;
      case MULTIPLICATION -> firstNumeral * secondNumeral;
      case DIVISION-> firstNumeral / secondNumeral;
    };
  }
}
