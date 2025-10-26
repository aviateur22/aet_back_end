package com.ctoutweb.aet.domain.entity.generateMentalCalculGame;

import com.ctoutweb.aet.domain.entity.JobGame;
import com.ctoutweb.aet.domain.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand.OperandManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operation.OperationManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operator.OperatorInCalcul;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operator.OperatorManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.OperatorParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.validator.ValidationManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText.GameTextInformation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText.GameTextManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.IOperation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.IOption;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.Option;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameOutput;
import com.ctoutweb.aet.domain.usecase.GenerateMentalCalculGameUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Génération d'un jeu de calcul mental
 */
public class MentalCalculGame extends JobGame<GenerateMentalCalculGameUseCase.Input, GenerateMentalCalculGameUseCase.Output> {

  private final OperationManager operationManager;
  private final CalculParameter calculParameter;
  private final OperatorManager operatorManager;
  private final OperandManager operandManager;
  private final ValidationManager validationManager;
  private final GameTextManager gameTextManager;

  /**
   * Liste des operateurs qui sont générés
   */
  private final List<OperatorInCalcul> operatorsInCalculs = new ArrayList<>();

  /**
   * Liste des opérations générées
   */
  private final List<IOperation> operations = new ArrayList<>();

  /**
   * Option sur le paramétrage du jeu
   */
  private IOption gameOption;

  /**
   * text de presentation et de fin de jeu
   */
  private GameTextInformation gameTextInformation;

  public MentalCalculGame(
          OperationManager operationManager,
          CalculParameter parameter,
          OperatorManager operatorManager,
          OperandManager operandManager,
          ValidationManager validationManager,
          GameTextManager gameTextManager) {
    this.operationManager = operationManager;
    this.calculParameter = parameter;
    this.operatorManager = operatorManager;
    this.operandManager = operandManager;
    this.validationManager = validationManager;
    this.gameTextManager = gameTextManager;
  }

  @Override
  public MentalCalculGame generateGame(GenerateMentalCalculGameUseCase.Input inputData) {
    generateCalculGame();
    return this;
  }

  @Override
  public GenerateMentalCalculGameUseCase.Output getGeneratedGameData() {
    IGenerateMentalCalculGameOutput output = new IGenerateMentalCalculGameOutput() {
      @Override
      public IGameTextInformation getGameTextInformation() {
        return gameTextInformation;
      }

      @Override
      public List<IOperation> getOperations() {
        return operations;
      }

      @Override
      public IOption getOption() {
        return gameOption;
      }
    };
    return new GenerateMentalCalculGameUseCase.Output(output);
  }

  /**
   * Génération du jeu de calcul mental
   *
   * @return Liste comportants les données générées pour le jeu de clacul
   */
  public MentalCalculGame generateCalculGame() {
    return generateGameText()
            .generateOperatorForCalculGame()
            .generateOperandForCalculGame()
            .generateOptionForCalculGame();
  }

  /**
   * Chargement du text de presentation et de fin de jeu
   *
   * @return Le text necesaire a l'affichage du jeu
   */
  private MentalCalculGame generateGameText() {
    this.gameTextInformation = this.gameTextManager.loadGameTextInformation();
    return this;
  }

  /**
   * Chargement des option du jeu
   *
   * @return CalculGenerator
   */
  private MentalCalculGame generateOptionForCalculGame() {
    this.gameOption = Option.getOption().loadOptionGame(calculParameter);
    return this;
  }

  /**
   * Génére une liste d'operator pour un calcul
   *
   * @return CalculGenerator
   */
  private MentalCalculGame generateOperatorForCalculGame() {

    var operatorsInCalculs = operatorManager
            .determineOperatorQuantityInCalculGame()
            .loadOperatorInCalculGame()
            .getOperationInCalculs();

    this.operatorsInCalculs.addAll(operatorsInCalculs);

    return this;
  }

  /**
   * Génération d'une liste d'opérandes.
   * Cette methodes charges toutes les données relatif aux operations du jeu à partir de la liste des operateurs
   *
   * @see #generateOperatorForCalculGame() Generation d'une liste d'operateur pour le jeu
   * @see #operations La liste regroupant les données des operétion
   *
   * @return CalculGenerator
   */
  private MentalCalculGame generateOperandForCalculGame() {
    if(operatorsInCalculs.isEmpty())
      return this;

    for(OperatorInCalcul operator : operatorsInCalculs) {
      int attempts = 0;
      IOperation operation;

      do {
        operation = this.generateSingleOperation(operator);
        attempts++;

      } while(attempts < 100 && !validationManager.isOperationResultValid(operation)) ;

      this.operations.add(operation);
    };

    return this;
  }

  /**
   * Génération des données nécéssaire a une opération
   *
   * @param operatorInCalcul OperatorInCalcul -
   *
   * @return OperationInformation - Les données générées sur l'operation
   */
  private IOperation generateSingleOperation(OperatorInCalcul operatorInCalcul) {

    List<OperatorType> initialOperators = operatorInCalcul
            .getOperatorParameters()
            .stream()
            .map(OperatorParameter::getOperatorType)
            .collect(Collectors.toList());


    double operationResult = operandManager
            .reinitializeOperandManager()
            .generateOperands(operatorInCalcul.getOperatorParameters())
            .calculateOperandResult(initialOperators, operandManager.getInitialOperands());

    var proposalResponses = operandManager.generateProposalResponse(4, operationResult);

    return operationManager.getGeneratedOperation(
            operatorInCalcul.getOperationPosition(),
            calculParameter.getTimeAvailableToCalculate(),
            operationResult,
            initialOperators,
            operandManager.getInitialOperands(),
            proposalResponses);
  }
}
