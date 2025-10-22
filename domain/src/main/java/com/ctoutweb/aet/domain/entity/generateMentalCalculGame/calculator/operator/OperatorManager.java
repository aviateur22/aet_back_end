package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operator;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorParameterFactory;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.OperatorParameter;
import com.ctoutweb.aet.domain.util.NumberUtil;

import java.util.*;

public class OperatorManager {
  private final CalculParameter calculParameter;
  private int operatorQuantityInGame;
  private final List<OperatorInCalcul> operationInCalculs = new ArrayList<>();
  public OperatorManager(CalculParameter calculParameter) {
    this.calculParameter = calculParameter;
  }
  /**
   * Détermine le nombre d'opérateur nécéssaire pour tous les calculs du jeu
   *
   * @return OperatorManager
   */
  public OperatorManager determineOperatorQuantityInCalculGame() {
    int minOperatorQuantityPerOperation = calculParameter.getMinOperatorByCalcul();
    int maxOperatorQuantityPerOperation = calculParameter.getMaxOperatorByCalcul();

    for(int i = 0; i < calculParameter.getCalculQuantity(); i++) {
      int operatorQuantity = NumberUtil.generateRandomNumberBetweenMinAndMax(minOperatorQuantityPerOperation, maxOperatorQuantityPerOperation);
      operatorQuantityInGame += operatorQuantity;
      operationInCalculs.add(new OperatorInCalcul(i, operatorQuantity));
    }

    return this;
  }

  /**
   * Chargement des opérateurs à partir des données de la liste  operationInCalculs
   * @see #determineOperatorQuantityInCalculGame()
   *
   * @return OperatorManager
   */
  public OperatorManager loadOperatorInCalculGame() {

    for(OperatorInCalcul operationInCalcul: operationInCalculs) {
      List<OperatorParameter> operationOperators = new ArrayList<>();

      if(operationInCalcul.getOperatorQuantity() == 1) {
        operationOperators.add(getCalculOperator());
        operationInCalcul.setOperators(operationOperators);
        continue;
      }

      for(int i = 0; i < operationInCalcul.getOperatorQuantity(); i++) {
        int randomOperatorIndex = NumberUtil.generateRandomNumberBetweenMinAndMax(0, calculParameter.getAcceptedOperatorAssociationList().size() - 1);
        OperatorType selectRandomOperator = calculParameter.getAcceptedOperatorAssociationList().get(randomOperatorIndex);
        operationOperators.add(getOperatorParameter(selectRandomOperator));
      }
      operationInCalcul.setOperators(operationOperators);
    }

    return this;
  }

  /**
   * Détermine l'opérateur le plus manquant dans le jeu a partir
   *
   * @return OperatorParameter
   */
  private OperatorParameter getCalculOperator() {
    int missingAdditionOperatorInGame = calculateMissingOperatorInGame(OperatorType.ADDITION);
    int missingSoustractionOperatorInGame = calculateMissingOperatorInGame(OperatorType.SOUSTRACTION);
    int missingMultiplicationOperatorInGame = calculateMissingOperatorInGame(OperatorType.MULTIPLICATION);
    int missingDivisionOperatorInGame = calculateMissingOperatorInGame(OperatorType.DIVISION);

    Map<OperatorType, Integer> missingByOperator = new HashMap<>();
    missingByOperator.put(OperatorType.ADDITION, missingAdditionOperatorInGame);
    missingByOperator.put(OperatorType.SOUSTRACTION, missingSoustractionOperatorInGame);
    missingByOperator.put(OperatorType.MULTIPLICATION, missingMultiplicationOperatorInGame);
    missingByOperator.put(OperatorType.DIVISION, missingDivisionOperatorInGame);

    OperatorType operatorMissing = Collections.max(missingByOperator.entrySet(), Map.Entry.comparingByValue()).getKey();
    return getOperatorParameter(operatorMissing);
  }

  /**
   * Renvoie le nombre d'opérateur manquant pour un type d'opérateur donnée dans le jeu
   *
   * @param operatorType Le type d'opérateur a
   * @return int - La quantité manquante pour le jey
   */
  private int calculateMissingOperatorInGame(OperatorType operatorType) {
    int expectedOperatorUsageQuantityInGame = this.getExpectedOperatorQuantityInGame(operatorType);
    int actualOperatorUsageQuantityInGame = this.getActualOperatorQuantityInGame(operatorType);

    return expectedOperatorUsageQuantityInGame - actualOperatorUsageQuantityInGame;

  }

  /**
   * Calcul le nombre d'opérateur prévu dans le jeu pour un type d'opérateur
   *
   * @param operatorType Le type d'operateur pour le calcul
   * @return int
   */
  private int getExpectedOperatorQuantityInGame(OperatorType operatorType) {

    int operatorPresencePercent = getOperatorParameter(operatorType)
            .getOperatorPresencePercent();
    return  (int) Math.round((operatorPresencePercent / 100.0) * operatorQuantityInGame);
  }

  /**
   * Calcul du nombre d'operateur total dans le jeu correspondant a un operateur précis
   * @param operatorToFind OperatorType
   * @return int - Nombre d'operateur dans le jeu egal a operatorToFind
   */
  private int getActualOperatorQuantityInGame(OperatorType operatorToFind) {
    return (int) operationInCalculs
            .stream()
            .flatMap(actualOperation -> actualOperation.getOperators().stream())
            .filter(operator -> operator.getOperatorType() == operatorToFind)
            .count();
  }

  /**
   * Renvoie les parametre de calcul d'un Operateur sauivant le niveau de jeu
   * @param operatorType OperatorType
   * @return OperatorParameter
   */
  private OperatorParameter getOperatorParameter(OperatorType operatorType) {
    return OperatorParameterFactory.loadOperatorParameter(operatorType, calculParameter.getGameLevel());
  }

  public List<OperatorInCalcul> getOperationInCalculs() {
    return operationInCalculs;
  }
}
