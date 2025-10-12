package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.OperatorCalculFactory;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.OperatorParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.proposalResponse.ProposalResponses;
import com.ctoutweb.aet.domain.util.IEventBus;
import com.ctoutweb.aet.domain.util.logger.ILogger;
import com.ctoutweb.aet.domain.util.logger.LogLevel;

import java.util.*;
import java.util.stream.Collectors;

import static com.ctoutweb.aet.domain.provider.CoreFactory.COMMON_INSTANCE_PROVIDER;
import static com.ctoutweb.aet.domain.provider.CoreFactory.MENTAL_CALCUL_INSTANCE_PROVIDER;

public class OperandManager {

  private final OperandAssociatedToPriorityOperator operandAssociatedToPriorityOperator;
  private final GenerateRandomOperand generateRandomOperand;
  private final ProposalResponses generateProposalResponse;
  private final IEventBus eventBus;
  private final ILogger LOGGER = COMMON_INSTANCE_PROVIDER.provideLoggerInstance();

  /**
   * Regroupement des données sur l'opération qui a été générée
   */
  private OperandInCalcul operandInCalcul;

  /**
   * Résulat de calcul sur l'opération généré
   */
  private double calculatedOperationResult;

  /**
   * Liste des operandes générés
   */
  private List<Integer> initialOperands;

  /**
   * Liste des opérateurs générés
   */
  private List<OperatorType> initialOperators;

  /**
   * Liste des porposition de réponses
   */
  private List<Double> proposalResponses;

  public OperandManager(
          OperandAssociatedToPriorityOperator operandAssociatedToPriorityOperator,
          GenerateRandomOperand generateRandomOperand,
          ProposalResponses generateProposalResponse, IEventBus eventBus) {
      this.operandAssociatedToPriorityOperator = operandAssociatedToPriorityOperator;
      this.generateRandomOperand = generateRandomOperand;
      this.generateProposalResponse = generateProposalResponse;
      this.eventBus = eventBus;
  }

  /**
   * Génération d'opérandes
   *
   * @param generatedOperatorParameters - List<OperatorParameter>
   * @return CalculateOperandResult
   */
  public OperandManager generateOperands(List<OperatorParameter> generatedOperatorParameters) {
    this.initialOperands = generateRandomOperand.generateCalculOperand(generatedOperatorParameters);
    this.initialOperators = generatedOperatorParameters.stream()
            .map(OperatorParameter::getOperatorType).collect(Collectors.toList());

    return this;
  }

  /**
   * Calcul du résultat de l'opération
   *
   * @param initialOperator - List<OperatorType> - Lite des operateurs initiaux
   * @param initialOperands - List<Integer> - Liste des opérandes initiaux
   * @see #generateOperands(List)
   * @return CalculateOperandResult
   */
  public OperandManager calculateOperandResult(List<OperatorType> initialOperator, List<Integer> initialOperands) {

    // Calcul des resultats lié aux opérateur prioritaire
    List<Double> updatedOperands = operandAssociatedToPriorityOperator
            .calculPriorityOperatorResult(initialOperator, initialOperands)
            .updateInitialOperandsWithPriorityOperatorResult(initialOperands);

    eventBus.publish(LOGGER.log(LogLevel.INFO, updatedOperands.toString()));

    // Calcul final de l'opération
    this.finalAggregateResult(initialOperator, updatedOperands);

    eventBus.publish(LOGGER.log(LogLevel.INFO, String.valueOf(this.calculatedOperationResult)));

    return this;
  }

  /**
   * Génération d'une liste de proposition de réponses
   *
   * @param responseQuantity - Integer - nombre de proposition de réponse à générer
   * @return CalculateOperandResult
   */
  public OperandManager generateProposalResponse(int responseQuantity) {
    this.proposalResponses = this.generateProposalResponse
            .generateProposalResponses(responseQuantity, this.calculatedOperationResult)
            .getProposalResults();
    return this;
  }

  /**
   * Calcul du résult de l'opération
   *
   * @param initialOperators - List<OperatorType> - Lite des operateurs initiaux
   * @param updatedOperands - List<Double> - Liste des operandes mise a jour avec les calculs des operateurs prioritaires
   * @see OperandAssociatedToPriorityOperator
   * @return CalculateOperandResult
   */
  private OperandManager finalAggregateResult(List<OperatorType> initialOperators, List<Double> updatedOperands) {

    List<OperatorType> lowPriorityOperators = initialOperators
            .stream()
            .filter(operator -> OperatorType.ADDITION == operator || OperatorType.SOUSTRACTION == operator )
            .toList();

    calculatedOperationResult = updatedOperands.get(0);

    for(int i = 0; i < lowPriorityOperators.size(); i++) {
      calculatedOperationResult = OperatorCalculFactory.calculateOperationResult(lowPriorityOperators.get(i), calculatedOperationResult, updatedOperands.get(i + 1));
    }

    return this;
  }

  private void setOperationGeneratedInformation() {
    this.operandInCalcul = MENTAL_CALCUL_INSTANCE_PROVIDER.provideOperandInCalculInstance();
    this.operandInCalcul.setGeneratedOperands(initialOperands);
    this.operandInCalcul.setOperationCalculResult(calculatedOperationResult);
    this.operandInCalcul.setProposalResponses(proposalResponses);

  }

  public OperandInCalcul getOperandInCalcul() {
    setOperationGeneratedInformation();
    return this.operandInCalcul;
  }

  public List<Integer> getInitialOperands() {
    return initialOperands;
  }

  public List<OperatorType> getInitialOperators() {
    return initialOperators;
  }
}

