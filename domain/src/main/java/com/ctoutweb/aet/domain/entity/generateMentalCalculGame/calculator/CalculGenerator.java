package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand.OperandInCalcul;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand.OperandManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand.OperationGeneratedInformation;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operator.OperatorInCalcul;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operator.OperatorManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;

import java.util.ArrayList;
import java.util.List;

public class CalculGenerator {

  private final OperatorManager operatorManager;
  private final OperandManager operandManager;
  private final CalculParameter calculParameter;

  private List<OperatorInCalcul> operatorsInCalculs;
  private List<OperandInCalcul> operandInCalculs;

  public CalculGenerator(
          CalculParameter calculParameter,
          OperatorManager operatorManager,
          OperandManager calculateOperandResult) {
    this.operatorManager = operatorManager;
    this.calculParameter = calculParameter;
    this.operandManager = calculateOperandResult;
  }

  /**
   *
   *
   * @return Liste comportants les données générées pour le jeu de clacul
   */
  public List<OperationGeneratedInformation> generateCalculGame() {
    return generateOperatorForCalculGame()
            .generateOperandForCalculGame()
            .loadOperationGeneratedInformationList();
  }

  /**
   * Génére une liste d'operator pour un calcul
   *
   * @return CalculGenerator
   */
  private CalculGenerator generateOperatorForCalculGame() {

    this.operatorsInCalculs = operatorManager
            .determineOperatorQuantityInCalculGame()
            .loadOperatorInCalculGame()
            .getOperationInCalculs();

    return this;
  }

  /**
   * Détermine
   * @return
   */
  private CalculGenerator generateOperandForCalculGame() {
    if(operatorsInCalculs.isEmpty())
      return this;


    this.operandInCalculs = new ArrayList<>();
    for(OperatorInCalcul operation: operatorsInCalculs) {
      int attempts = 0;
      OperandInCalcul operandInCalcul;

      do {
        operandInCalcul = this.generateSingleOperation(operation);
        attempts++;

      } while(attempts < 100 && !isOperationResultValid(operandInCalcul)) ;

      this.operandInCalculs.add(operandInCalcul);
    };

    return this;
  }


  /**
   * Génération des données nécéssaire a une opération
   * @param operation OperatorInCalcul -
   * @return OperationInformation - Les données générées sur l'operation
   */
  private OperandInCalcul generateSingleOperation(OperatorInCalcul operation) {
    return operandManager
            .generateOperands(operation.getOperators())
            .calculateOperandResult(operandManager.getInitialOperators(), operandManager.getInitialOperands())
            .generateProposalResponse(4)
            .getOperandInCalcul();
  }

  /**
   * Renvoie la validité de la réponse de calcul qui a été généréré
   * @param operationInformation OperationInformation
   * @return boolean - Si la réponse est valide
   */
  private boolean isOperationResultValid(OperandInCalcul operationInformation) {
    return isCalculatedResponseRespectLastDigitContraint(operationInformation.getOperationCalculResult())
            && isCalculatedResponseRespectNegativeConstraint(operationInformation.getOperationCalculResult());
  }

  /**
   * Vérification si le dernier digit est valide
   * @return boolean - Dernier digit valide
   */
  private boolean isCalculatedResponseRespectLastDigitContraint(double calculatedOperationResult) {
    double lastDigit = Math.abs(calculatedOperationResult % 10);
    return this.calculParameter.getLastCalculatedDigitAcceptedList().contains(lastDigit);
  }

  /**
   * Vérification si le dernier digit est valide
   * @return boolean - Dernier digit valide
   */
  private boolean isCalculatedResponseRespectNegativeConstraint(double calculatedOperationResult) {
    if(this.calculParameter.getIsNegativeCalculResultAccepted())
      return true;

    return !(calculatedOperationResult < 0);
  }

  List<OperationGeneratedInformation> loadOperationGeneratedInformationList() {
   return null;
  }

}
