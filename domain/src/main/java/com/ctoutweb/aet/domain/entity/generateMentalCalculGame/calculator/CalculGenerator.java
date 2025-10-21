package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator;

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

import java.util.ArrayList;
import java.util.List;

public class CalculGenerator {

  private final OperatorManager operatorManager;
  private final OperandManager operandManager;
  private final CalculParameter calculParameter;
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

  public CalculGenerator(
          CalculParameter calculParameter,
          OperatorManager operatorManager,
          OperandManager calculateOperandResult,
          ValidationManager validationManager,
          GameTextManager gameTextManager) {
    this.operatorManager = operatorManager;
    this.calculParameter = calculParameter;
    this.operandManager = calculateOperandResult;
    this.validationManager = validationManager;
    this.gameTextManager = gameTextManager;
  }

  /**
   *
   *
   * @return Liste comportants les données générées pour le jeu de clacul
   */
  public CalculGenerator generateCalculGame() {
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
  private CalculGenerator generateGameText() {
    this.gameTextInformation = this.gameTextManager.loadGameTextInformation();
    return this;
  }

  /**
   * Chargement des option du jeu
   *
   * @return CalculGenerator
   */
  private CalculGenerator generateOptionForCalculGame() {
    this.gameOption = Option.getOption().loadOptionGame(calculParameter);
    return this;
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
   * Génération d'une liste d'opérandes.
   * Cette methodes charges toutes les données relatif aux operations du jeu à partir de la liste des operateurs
   *
   * @see #generateOperatorForCalculGame() Generation d'une liste d'operateur pour le jeu
   * @see #operations La liste regroupant les données des operétion
   *
   * @return CalculGenerator
   */
  private CalculGenerator generateOperandForCalculGame() {
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
   * @param operatorInCalcul OperatorInCalcul -
   * @return OperationInformation - Les données générées sur l'operation
   */
  private IOperation generateSingleOperation(OperatorInCalcul operatorInCalcul) {
    return operandManager
            .generateOperands(operatorInCalcul.getOperators(), operatorInCalcul.getOperationPosition())
            .calculateOperandResult(operandManager.getInitialOperators(), operandManager.getInitialOperands())
            .generateProposalResponse(4)
            .getGeneratedOperation();
  }

  //////////////////////////////////////////////////////////////////////////
  ///
  public List<IOperation> getOperations() {
    return operations;
  }

  public IOption getGameOption() {
    return gameOption;
  }

  public GameTextInformation getGameTextInformation() {
    return gameTextInformation;
  }
}
