package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operator.OperatorManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.OperatorParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.proposalResponse.ProposalResponses;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.ProposalResponse;
import com.ctoutweb.aet.domain.util.IEventBus;
import com.ctoutweb.aet.domain.util.logger.ILogger;
import com.ctoutweb.aet.domain.util.logger.LogLevel;

import java.util.*;

import static com.ctoutweb.aet.domain.provider.CoreFactory.COMMON_INSTANCE_PROVIDER;

/**
 * Generation des operandes ainsi que determination du resltat du calcul pour une operation *
 */
public class OperandManager {

  private final CalculParameter calculParameter;
  private final OperandAssociatedToPriorityOperator operandAssociatedToPriorityOperator;
  private final GenerateRandomOperand generateRandomOperand;
  private final ProposalResponses generateProposalResponse;
  private final IEventBus eventBus;
  private final ILogger LOGGER = COMMON_INSTANCE_PROVIDER.provideLoggerInstance();

  /**
   * Liste des operandes générés
   */
  private final List<Integer> initialOperands = new ArrayList<>();

  public OperandManager(
          CalculParameter calculParameter,
          OperandAssociatedToPriorityOperator operandAssociatedToPriorityOperator,
          GenerateRandomOperand generateRandomOperand,
          ProposalResponses generateProposalResponse,
          IEventBus eventBus) {
      this.calculParameter = calculParameter;
      this.operandAssociatedToPriorityOperator = operandAssociatedToPriorityOperator;
      this.generateRandomOperand = generateRandomOperand;
      this.generateProposalResponse = generateProposalResponse;
      this.eventBus = eventBus;
  }

  public OperandManager reinitializeOperandManager() {
    this.initialOperands.clear();
    return this;
  }

  /**
   * Génération d'un liste d'opérandes
   *
   * @param generatedOperatorParameters Liste des Operateurs avec les parametre générée à l'étape précedente
   *
   * @see OperatorManager
   *
   * @return CalculateOperandResult
   */
  public OperandManager generateOperands(List<OperatorParameter> generatedOperatorParameters) {
    this.initialOperands.addAll(generateRandomOperand.generateCalculOperand(generatedOperatorParameters));

    return this;
  }

  /**
   * Calcul du résultat de l'opération
   *
   * @param initialOperator - List<OperatorType> - Lite des operateurs initiaux
   * @param initialOperands - List<Integer> - Liste des opérandes initiaux
   *
   * @return CalculateOperandResult
   */
  public double calculateOperandResult(List<OperatorType> initialOperator, List<Integer> initialOperands) {

    // Calcul des resultats lié aux opérateur prioritaire
    List<Double> updatedOperands = operandAssociatedToPriorityOperator
            .calculPriorityOperatorResult(initialOperator, initialOperands)
            .updateInitialOperandsWithPriorityOperatorResult(initialOperands);

    eventBus.publish(LOGGER.log(LogLevel.INFO, updatedOperands.toString()));

    // Calcul final de l'opération
    double operationResult = this.finalAggregateResult(initialOperator, updatedOperands);

    eventBus.publish(LOGGER.log(LogLevel.INFO, String.valueOf(operationResult)));

    return operationResult;
  }

  /**
   * Calcul du résult de l'opération
   *
   * @param initialOperators - List<OperatorType> - Lite des operateurs initiaux
   * @param updatedOperands - List<Double> - Liste des operandes mise a jour avec les calculs des operateurs prioritaires
   *
   * @see OperandAssociatedToPriorityOperator
   *
   * @return CalculateOperandResult
   */
  private double finalAggregateResult(List<OperatorType> initialOperators, List<Double> updatedOperands) {

    List<OperatorType> lowPriorityOperators = initialOperators
            .stream()
            .filter(operator -> OperatorType.ADDITION == operator || OperatorType.SOUSTRACTION == operator )
            .toList();

    var calculatedOperationResult = updatedOperands.get(0);

    for(int i = 0; i < lowPriorityOperators.size(); i++) {
      calculatedOperationResult = CalculateOperation.calculateOperationResult(lowPriorityOperators.get(i), calculatedOperationResult, updatedOperands.get(i + 1));
    }

    return calculatedOperationResult;
  }

  /**
   * Génération d'une liste de proposition de réponses
   *
   * @param responseQuantity - Integer - nombre de proposition de réponse à générer
   * @return CalculateOperandResult
   */
  public List<ProposalResponse>  generateProposalResponse(int responseQuantity, double operationResult) {
    return this.generateProposalResponse.generateProposalResponses(responseQuantity, operationResult);
  }

  public List<Integer> getInitialOperands() {
    return initialOperands;
  }
}

