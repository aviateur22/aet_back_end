package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter;

import com.ctoutweb.aet.domain.entity.LevelType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.IOperation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.Operation;

import java.util.ArrayList;
import java.util.List;

/**
 * Données des calcul suivant le niveau
 */
public class CalculParameter {

   private final LevelType level;

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

  private List<IOperation> generatedCalculOperations = new ArrayList<>();

  public List<IOperation> getGeneratedCalculOperations() {
    return generatedCalculOperations;
  }

  public CalculParameter(
          LevelType level,
          int calculQuantity,
          int minOperatorByCalcul,
          int maxOperatorByCalcul,
          boolean isNegativeCalculResultAccepted,
          int timeAvailableToCalculate,
          List<OperatorType> acceptedOperatorAssociationList,
          List<Double> lastCalculatedDigitAcceptedList) {
    this.level = level;
    this.calculQuantity = calculQuantity;
    this.minOperatorByCalcul = minOperatorByCalcul;
    this.maxOperatorByCalcul = maxOperatorByCalcul;
    this.isNegativeCalculResultAccepted = isNegativeCalculResultAccepted;
    this.timeAvailableToCalculate = timeAvailableToCalculate;
    this.acceptedOperatorAssociationList = acceptedOperatorAssociationList;
    this.lastCalculatedDigitAcceptedList = lastCalculatedDigitAcceptedList;  }

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

  public LevelType getLevel() {
    return level;
  }

  public int getTimeAvailableToCalculate() {
    return timeAvailableToCalculate;
  }
}
