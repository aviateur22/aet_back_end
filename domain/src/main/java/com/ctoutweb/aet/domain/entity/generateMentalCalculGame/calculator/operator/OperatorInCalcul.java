package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operator;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.OperatorParameter;

import java.util.*;

public class OperatorInCalcul {
  /**
   * Position de l'opération a calculer dans le jeu
   */
  private int operationPosition;

  /**
   * Quantité d'opérateur dans 1 opération
   */
  private int operatorQuantity;

  /**
   * Liste des opérateurs prévu pour le calcul.
   */
  private List<OperatorParameter> operators = new ArrayList<>();

  public OperatorInCalcul() {
  }

  public OperatorInCalcul(int operationPosition, int operatorQuantity) {
    this.operationPosition = operationPosition;
    this.operatorQuantity = operatorQuantity;
  }

  public int getOperationPosition() {
    return operationPosition;
  }

  public void setOperationPosition(int operationPosition) {
    this.operationPosition = operationPosition;
  }

  public int getOperatorQuantity() {
    return operatorQuantity;
  }

  public void setOperatorQuantity(int operatorQuantity) {
    this.operatorQuantity = operatorQuantity;
  }

  public List<OperatorParameter> getOperators() {
    return operators;
  }

  public void setOperators(List<OperatorParameter> operators) {
    this.operators = operators;
  }
}
