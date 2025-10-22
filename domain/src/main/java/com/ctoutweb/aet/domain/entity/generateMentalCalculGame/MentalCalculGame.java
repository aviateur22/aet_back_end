package com.ctoutweb.aet.domain.entity.generateMentalCalculGame;

import com.ctoutweb.aet.domain.entity.JobGame;
import com.ctoutweb.aet.domain.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand.OperandManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operator.OperatorInCalcul;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operator.OperatorManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.validator.ValidationManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText.GameTextInformation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText.GameTextManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.IOperation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.IOption;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.Option;
import com.ctoutweb.aet.domain.injector.MethodInjectorContainer;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.ICardFaceIdent;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameOutput;
import com.ctoutweb.aet.domain.usecase.GenerateMentalCalculGameUseCase;
import com.ctoutweb.aet.domain.util.IEventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Génération d'un jeu de calcul mental
 */
public class MentalCalculGame extends JobGame<GenerateMentalCalculGameUseCase.Input, GenerateMentalCalculGameUseCase.Output> {

  private final CalculParameter calculParameter;
  private final IEventBus eventBus;
  private final OperatorManager operatorManager;
  private final OperandManager operandManager;
  private final ValidationManager validationManager;
  private final GameTextManager gameTextManager;

  /**
   * Liste des operateurs qui sont générés
   */
  private List<OperatorInCalcul> operatorsInCalculs;

  /**
   * Liste des opérations générées
   */
  private List<IOperation> operations;

  /**
   * Option sur le paramétrage du jeu
   */
  private IOption gameOption;

  /**
   * text de presentation et de fin de jeu
   */
  private GameTextInformation gameTextInformation;

  public MentalCalculGame(
          IEventBus eventBus,
          CalculParameter parameter,
          OperatorManager operatorManager,
          OperandManager operandManager,
          ValidationManager validationManager,
          GameTextManager gameTextManager) {
    this.eventBus = eventBus;
    this.calculParameter = parameter;
    this.operatorManager = operatorManager;
    this.operandManager = operandManager;
    this.validationManager = validationManager;
    this.gameTextManager = gameTextManager;
  }

  @Override
  public MentalCalculGame generateGame(GenerateMentalCalculGameUseCase.Input inputData) {
    registerDependencies(inputData);
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

    this.operatorsInCalculs = operatorManager
            .determineOperatorQuantityInCalculGame()
            .loadOperatorInCalculGame()
            .getOperationInCalculs();

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


    this.operations = new ArrayList<>();

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
    return operandManager
            .generateOperands(operatorInCalcul.getOperators(), operatorInCalcul.getOperationPosition())
            .calculateOperandResult(operandManager.getInitialOperators(), operandManager.getInitialOperands())
            .generateProposalResponse(4)
            .getGeneratedOperation();
  }

  private void registerDependencies(GenerateMentalCalculGameUseCase.Input inputData) {
    MethodInjectorContainer
            .getInstance()
            .register(ICardFaceIdent.class, inputData.generateMentalCalculGameInput().getCardFaceId());
  }

}
