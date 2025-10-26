package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter;

import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.GameLevel;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorType;

import java.util.List;

/**
 * Données des calcul suivant le niveau
 */
public class CalculParameter {

   private final GameLevel gameLevel;

  /**
   * Nombre de calcul à faire
   */
  private final int calculQuantity;

  /**
   * Nombre mini d'opérateur par calcul
   */
  private final int minOperatorByCalcul;

  /**
   * Nombre d'operation max que peut avoir un calcul
   */
  private final int maxOperatorByCalcul;

  /**
   * Resultat de calcul négatif
   */
  private final boolean isNegativeCalculResultAccepted;

  /**
   * Temps de calcul
   */
  private final int timeAvailableToCalculate;

  /**
   * Operation qui peuvent être associé
   */
  private final List<OperatorType>  acceptedOperatorAssociationList;

  /**
   * Liste des derniers Chiffre de fin qui sont accépté
   */
  private final List<Double> lastCalculatedDigitAcceptedList;

  /**
   * Born min et max encadrant le resultat d'une operation
   */
  public IMinAndMax<Double> minMaxAcceptedCalculResult;

  public CalculParameter(
          GameLevel level,
          int calculQuantity,
          int minOperatorByCalcul,
          int maxOperatorByCalcul,
          boolean isNegativeCalculResultAccepted,
          int timeAvailableToCalculate,
          List<OperatorType> acceptedOperatorAssociationList,
          List<Double> lastCalculatedDigitAcceptedList,
          IMinAndMax<Double> minMaxCalculResult) {
    this.gameLevel = level;
    this.calculQuantity = calculQuantity;
    this.minOperatorByCalcul = minOperatorByCalcul;
    this.maxOperatorByCalcul = maxOperatorByCalcul;
    this.isNegativeCalculResultAccepted = isNegativeCalculResultAccepted;
    this.timeAvailableToCalculate = timeAvailableToCalculate;
    this.acceptedOperatorAssociationList = acceptedOperatorAssociationList;
    this.lastCalculatedDigitAcceptedList = lastCalculatedDigitAcceptedList;
    this.minMaxAcceptedCalculResult = minMaxCalculResult;
  }

  public List<Double> getLastCalculatedDigitAcceptedList() {
    return lastCalculatedDigitAcceptedList;
  }

  public int getCalculQuantity() {
    return calculQuantity;
  }

  public int getMinOperatorByCalcul() {
    return minOperatorByCalcul;
  }

  public int getMaxOperatorByCalcul() {
    return maxOperatorByCalcul;
  }

  public boolean getIsNegativeCalculResultAccepted() {
    return isNegativeCalculResultAccepted;
  }

  public List<OperatorType> getAcceptedOperatorAssociationList() {
    return acceptedOperatorAssociationList;
  }

  public GameLevel getGameLevel() {
    return gameLevel;
  }

  public int getTimeAvailableToCalculate() {
    return timeAvailableToCalculate;
  }

  public IMinAndMax<Double> getMinMaxAcceptedCalculResult() {
    return minMaxAcceptedCalculResult;
  }
}
