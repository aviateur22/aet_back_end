package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand;

import com.ctoutweb.aet.domain.annotation.InjectConstructorParam;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operator.OperatorManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.validator.ValidationManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.paramter.CalculParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.paramter.OperatorParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.proposalResponse.ProposalResponseManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.ProposalResponse;
import com.ctoutweb.aet.domain.event.logger.LogEvent;
import com.ctoutweb.aet.domain.event.IEventBus;
import com.ctoutweb.aet.domain.event.logger.LogLevel;

import java.util.*;


/**
 * Generation des operandes ainsi que determination du resltat du calcul pour une operation *
 */
public class OperandManager {

  private final ValidationManager validationManager;
  private final CalculParameter calculParameter;
  private final OperandAssociatedToPriorityOperator operandAssociatedToPriorityOperator;
  private final GenerateRandomOperand generateRandomOperand;
  private final ProposalResponseManager generateProposalResponse;
  private final IEventBus eventBus;

  /**
   * Liste des operandes générés
   */
  private final List<Integer> initialOperands = new ArrayList<>();

  public OperandManager(
          @InjectConstructorParam ValidationManager validationManager,
          @InjectConstructorParam CalculParameter calculParameter,
          @InjectConstructorParam OperandAssociatedToPriorityOperator operandAssociatedToPriorityOperator,
          @InjectConstructorParam GenerateRandomOperand generateRandomOperand,
          @InjectConstructorParam ProposalResponseManager generateProposalResponse,
          @InjectConstructorParam IEventBus eventBus) {
      this.validationManager = validationManager;
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
  public CalculOperandResult calculateOperandResult(List<OperatorType> initialOperator, List<Integer> initialOperands) {

    // Calcul des resultats lié aux opérateur prioritaire
    PriorityOperatorResult priorityOperatorResult = operandAssociatedToPriorityOperator
            .calculPriorityOperatorResult(initialOperator, initialOperands)
            .updateInitialOperandsWithPriorityOperatorResult(initialOperands);

    List<Double> updatedOperands = priorityOperatorResult.updatedInitialOperandsWithPriorityOperatorResult();

    boolean arePriorityCaclculResultValid = priorityOperatorResult.arePriorityOperatorCalculResultValid();

    // Calcul final de l'opération
    var calculFinalAggregate = this.finalAggregateResult(initialOperator, updatedOperands);

    boolean areAllIntermediateCalculPositive = arePriorityCaclculResultValid && calculFinalAggregate.areLowPriorityOperatorCalculResultValid();

    return new CalculOperandResult(calculFinalAggregate.finalCalculResult(), areAllIntermediateCalculPositive);
  }

  /**
   * Calcul du résult de l'opération
   *
   * @param initialOperators - List<OperatorType> - Lite des operateurs initiaux
   * @param updatedOperands - List<Double> - Liste des operandes mise a jour avec les calculs des operateurs prioritaires
   *                        Cette liste peut être egale à la initialOperands si il n' ya pas d'operator prioritaire
   *
   * @see OperandAssociatedToPriorityOperator
   *
   * @return CalculateOperandResult
   */
  public LowPriorityOperatorResult finalAggregateResult(List<OperatorType> initialOperators, List<Double> updatedOperands) {

    boolean areLowPriorityCalculValid = true;

    List<OperatorType> lowPriorityOperators = initialOperators
            .stream()
            .filter(operator -> OperatorType.ADDITION == operator || OperatorType.SOUSTRACTION == operator )
            .toList();

    var calculatedOperationResult = updatedOperands.get(0);

    for(int i = 0; i < lowPriorityOperators.size(); i++) {
      calculatedOperationResult = CalculateOperation
              .calculateOperationResult(lowPriorityOperators.get(i), calculatedOperationResult, updatedOperands.get(i + 1));

      if(!validationManager.areIntermediateCalculValid(calculatedOperationResult))
        areLowPriorityCalculValid = false;
    }


    return new LowPriorityOperatorResult(areLowPriorityCalculValid, calculatedOperationResult);
  }

  /**
   * Génération d'une liste de proposition de réponses
   *
   * @param responseQuantity - Integer - nombre de proposition de réponse à générer
   * @return CalculateOperandResult
   */
  public List<ProposalResponse>  generateProposalResponse(int responseQuantity, double operationResult) {
    List<ProposalResponse> proposalResponses = this.generateProposalResponse
            .generateProposalResponses(responseQuantity, operationResult);

    return this.generateProposalResponse.shuffleProposalResponse(proposalResponses);
  }

  public List<Integer> getInitialOperands() {
    return initialOperands;
  }
}

