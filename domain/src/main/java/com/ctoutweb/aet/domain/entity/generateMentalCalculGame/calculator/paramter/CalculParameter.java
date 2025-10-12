package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter;

import com.ctoutweb.aet.domain.entity.LevelType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.MentalNumber;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.Operation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.ProposalResponse;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.TimeToCalculate;
import com.ctoutweb.aet.domain.util.NumberUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Données des calcul suivant le niveau
 */
public class CalculParameter {
  private final OperatorParameter additionParameter;
  private final OperatorParameter soustractionParameter;
  private final OperatorParameter multiplicationParameter;
  private final OperatorParameter divisionParameter;
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
  private final List<Integer> lastCalculatedDigitAcceptedList;

  private List<Operation> generatedCalculOperations = new ArrayList<>();

  public List<Operation> getGeneratedCalculOperations() {
    return generatedCalculOperations;
  }

  public CalculParameter(
          OperatorParameter additionParameter,
          OperatorParameter soustractionParameter,
          OperatorParameter multiplicationParameter,
          OperatorParameter divisionParameter,
          LevelType level,
          int calculQuantity,
          int minOperatorByCalcul,
          int maxOperatorByCalcul,
          boolean isNegativeCalculResultAccepted,
          int timeAvailableToCalculate,
          List<OperatorType> acceptedOperatorAssociationList,
          List<Integer> lastCalculatedDigitAcceptedList) {
    this.level = level;
    this.additionParameter = additionParameter;
    this.soustractionParameter = soustractionParameter;
    this.multiplicationParameter = multiplicationParameter;
    this.divisionParameter = divisionParameter;
    this.calculQuantity = calculQuantity;
    this.minOperatorByCalcul = minOperatorByCalcul;
    this.maxOperatorByCalcul = maxOperatorByCalcul;
    this.isNegativeCalculResultAccepted = isNegativeCalculResultAccepted;
    this.timeAvailableToCalculate = timeAvailableToCalculate;
    this.acceptedOperatorAssociationList = acceptedOperatorAssociationList;
    this.lastCalculatedDigitAcceptedList = lastCalculatedDigitAcceptedList;
  }

  public void getAritmeticOperatorNumberForCalcul() {
    this.generatedCalculOperations.clear();

    double additionOperatorNumber = Math.ceil((this.additionParameter.getOperatorPresencePercent() / 100.0) * this.calculQuantity);
    double soustractionOperatorNumber = Math.ceil((this.soustractionParameter.getOperatorPresencePercent() / 100.0) * this.calculQuantity);
    double multiplicationOperatorNumber = Math.ceil((this.multiplicationParameter.getOperatorPresencePercent() / 100.0) * this.calculQuantity);
    double divisionOperatorNumber = Math.ceil((double) this.divisionParameter.getOperatorPresencePercent() / 100 * this.calculQuantity);

    generateCalculWithTwoNumerals(additionOperatorNumber, this.additionParameter);
    generateCalculWithTwoNumerals(soustractionOperatorNumber, this.soustractionParameter);
    generateCalculWithTwoNumerals(multiplicationOperatorNumber, this.multiplicationParameter);
    generateCalculWithTwoNumerals(divisionOperatorNumber, this.divisionParameter);

  }

  private void generateCalculWithTwoNumerals(double quantity, OperatorParameter operatorParamter) {
    int minAcceptedNumeral = operatorParamter.getMinAndMaxNumberForCalcul().getMin();
    int maxAcceptedNumeral = operatorParamter.getMinAndMaxNumberForCalcul().getMax();

    for(int i = 0; i < quantity ; i++) {

      Operation operation = null;
      int attempts = 0;
      do {
        operation = this.mapDataToOperation(operatorParamter.getOperatorType(), minAcceptedNumeral, maxAcceptedNumeral, i);
        attempts++;

      } while ((operation.validOperationResponse() < 0 ||  !isCalculatedResponseRespectLastDigitContraint(operation.validOperationResponse())) && attempts < 100) ;

      if(operation == null)
        this.createOperationFallBack(operatorParamter.getOperatorType());

      this.generatedCalculOperations.add(operation);
    }
  }

  private Operation mapDataToOperation(OperatorType operatorType, int minAcceptedNumeral, int maxAcceptedNumeral, int i) {
    int firstNumeral = generateNumeralNumber(minAcceptedNumeral, maxAcceptedNumeral);
    int secondNumeral = generateNumeralNumber(minAcceptedNumeral, maxAcceptedNumeral);

    int result = switch (operatorType) {
      case ADDITION -> firstNumeral + secondNumeral;
      case SOUSTRACTION -> firstNumeral - secondNumeral;
      case MULTIPLICATION -> firstNumeral * secondNumeral;
      case DIVISION-> firstNumeral / secondNumeral;
    };

    TimeToCalculate timeToCalculate = new TimeToCalculate("", timeAvailableToCalculate);

    MentalNumber firstNumeralNumber = new MentalNumber(i, firstNumeral, "");
    MentalNumber secondNumeralNumber = new MentalNumber(i, secondNumeral, "");
    List<MentalNumber> mentalNumbers = List.of(firstNumeralNumber, secondNumeralNumber);
    List<String> operators = List.of(operatorType.getOperationSign());
    List<ProposalResponse> proposalResponses = new ArrayList<>();

    for(int j = 0; j < 4 ; j++) {
      int proposalAnswer = NumberUtil.generateRandomNumberBetweenMinAndMax(result - 20, result + 20);
      proposalResponses.add(new ProposalResponse(j, proposalAnswer));
    }

    Operation operation = new Operation(
            i,
            timeToCalculate,
            mentalNumbers,
            operators,
            proposalResponses,
            result
    );

    return  operation;
  }

  private boolean isCalculatedResponseRespectLastDigitContraint(int calcultatedResponse) {
    int lastDigit = Math.abs(calcultatedResponse % 10);
    return lastCalculatedDigitAcceptedList.contains(lastDigit);
  }

  private int generateNumeralNumber(int minAcceptedNumeral, int maxAcceptedNumeral) {
    int attempts = 0;
    int generateNumeralNumber;
    do {
      generateNumeralNumber = NumberUtil.generateRandomNumberBetweenMinAndMax(minAcceptedNumeral, maxAcceptedNumeral);
      attempts++;

    } while ((!isCalculatedResponseRespectLastDigitContraint(generateNumeralNumber)) && attempts < 100) ;

    return generateNumeralNumber;
  }

  private Operation createOperationFallBack(OperatorType operatorType) {
    TimeToCalculate timeToCalculate = new TimeToCalculate("", timeAvailableToCalculate);

    int firstNumeral = 10;
    int secondNumeral = 2;
    int result = switch (operatorType) {
      case ADDITION -> firstNumeral + secondNumeral;
      case SOUSTRACTION -> firstNumeral - secondNumeral;
      case MULTIPLICATION -> firstNumeral * secondNumeral;
      case DIVISION-> firstNumeral / secondNumeral;
    };

    MentalNumber firstNumeralNumber = new MentalNumber(10, firstNumeral, "");
    MentalNumber secondNumeralNumber = new MentalNumber(10, secondNumeral, "");
    List<MentalNumber> mentalNumbers = List.of(firstNumeralNumber, secondNumeralNumber);
    List<String> operators = List.of(operatorType.getOperationSign());
    List<ProposalResponse> proposalResponses = new ArrayList<>();

    Operation operation = new Operation(
            10,
            timeToCalculate,
            mentalNumbers,
            operators,
            proposalResponses,
            result
    );

    return  operation;

  }

  public List<Integer> getLastCalculatedDigitAcceptedList() {
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
}
