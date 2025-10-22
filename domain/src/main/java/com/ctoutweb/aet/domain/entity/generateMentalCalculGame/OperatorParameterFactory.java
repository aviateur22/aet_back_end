package com.ctoutweb.aet.domain.entity.generateMentalCalculGame;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.OperatorParameter;
import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.difficultLevel.operator.DifficultLevelAdditionOperatorParameter;
import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.difficultLevel.operator.DifficultLevelDivisionOperatorParameter;
import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.difficultLevel.operator.DifficultLevelMultiplicationOperatorParameter;
import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.difficultLevel.operator.DifficultLevelSoustractionOperatorParameter;
import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.easyLevel.operator.EasyLevelAdditionOperatorParameter;
import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.easyLevel.operator.EasyLevelDivisionOperatorParameter;
import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.easyLevel.operator.EasyLevelMultiplicationOperatorParameter;
import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.easyLevel.operator.EasyLevelSoustractionOperatorParameter;
import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.meduimLevel.operator.MeduimLevelAdditionOperatorParameter;
import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.meduimLevel.operator.MeduimLevelDivisionOperatorParameter;
import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.meduimLevel.operator.MeduimLevelMultiplicationOperatorParameter;
import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.meduimLevel.operator.MeduimLevelSoustractionOperatorParameter;

/**
 * Chargement des parametres des Operator
 */
public class OperatorParameterFactory {

  private static final OperatorParameterFactory instance = new OperatorParameterFactory();

  public static OperatorParameter loadOperatorParameter(OperatorType operatorType, GameLevel gameLevel) {

    return switch (gameLevel) {
      case EASY ->  switch (operatorType) {
        case ADDITION -> instance.loadEasyAdditionOperator();
        case SOUSTRACTION -> instance.loadEasySoustractionOperator();
        case MULTIPLICATION -> instance.loadEasyMultiplicationOperator();
        case DIVISION -> instance.loadEasyDivisionOperator();
      };

      case MEDIUM -> switch (operatorType) {
        case ADDITION -> instance.loadMediumAdditionOperator();
        case SOUSTRACTION -> instance.loadMediumSoustractionOperator();
        case MULTIPLICATION -> instance.loadMediumMultiplicationOperator();
        case DIVISION -> instance.loadMediumDivisionOperator();
      };

      case DIFFICULT -> switch (operatorType) {
        case ADDITION -> instance.loadDifficultAdditionOperator();
        case SOUSTRACTION -> instance.loadDifficultSoustractionOperator();
        case MULTIPLICATION -> instance.loadDifficultMultiplicationOperator();
        case DIVISION -> instance.loadDifficultDivisionOperator();
      };
    };
  }

  public OperatorParameter loadEasyAdditionOperator() {
    return new OperatorParameter(
            OperatorType.ADDITION,
            EasyLevelAdditionOperatorParameter.MIN_AND_MAX_NUMBER,
            EasyLevelAdditionOperatorParameter.OPERATOR_PRESENCE_PERCENT);
  }

  public OperatorParameter loadMediumAdditionOperator() {
    return new OperatorParameter(
            OperatorType.ADDITION,
            MeduimLevelAdditionOperatorParameter.MIN_AND_MAX_NUMBER,
            MeduimLevelAdditionOperatorParameter.OPERATOR_PRESENCE_PERCENT);
  }
  public OperatorParameter loadDifficultAdditionOperator() {
    return new OperatorParameter(
            OperatorType.ADDITION,
            DifficultLevelAdditionOperatorParameter.MIN_AND_MAX_NUMBER,
            DifficultLevelAdditionOperatorParameter.OPERATOR_PRESENCE_PERCENT);
  }

  public OperatorParameter loadEasySoustractionOperator() {
    return new OperatorParameter(
            OperatorType.SOUSTRACTION,
            EasyLevelSoustractionOperatorParameter.MIN_AND_MAX_NUMBER,
            EasyLevelSoustractionOperatorParameter.OPERATOR_PRESENCE_PERCENT);
  }

  public OperatorParameter loadMediumSoustractionOperator() {
    return new OperatorParameter(
            OperatorType.SOUSTRACTION,
            MeduimLevelSoustractionOperatorParameter.MIN_AND_MAX_NUMBER,
            MeduimLevelSoustractionOperatorParameter.OPERATOR_PRESENCE_PERCENT);
  }

  public OperatorParameter loadDifficultSoustractionOperator() {
    return new OperatorParameter(
            OperatorType.SOUSTRACTION,
            DifficultLevelSoustractionOperatorParameter.MIN_AND_MAX_NUMBER,
            DifficultLevelSoustractionOperatorParameter.OPERATOR_PRESENCE_PERCENT);
  }

  public OperatorParameter loadEasyMultiplicationOperator() {
    return new OperatorParameter(
            OperatorType.MULTIPLICATION,
            EasyLevelMultiplicationOperatorParameter.MIN_AND_MAX_NUMBER,
            EasyLevelMultiplicationOperatorParameter.OPERATOR_PRESENCE_PERCENT);
  }
  public OperatorParameter loadMediumMultiplicationOperator() {
    return new OperatorParameter(
            OperatorType.MULTIPLICATION,
            MeduimLevelMultiplicationOperatorParameter.MIN_AND_MAX_NUMBER,
            MeduimLevelMultiplicationOperatorParameter.OPERATOR_PRESENCE_PERCENT);
  }
  public OperatorParameter loadDifficultMultiplicationOperator() {
    return new OperatorParameter(
            OperatorType.MULTIPLICATION,
            DifficultLevelMultiplicationOperatorParameter.MIN_AND_MAX_NUMBER,
            DifficultLevelMultiplicationOperatorParameter.OPERATOR_PRESENCE_PERCENT);
  }
  public OperatorParameter loadEasyDivisionOperator() {
    return new OperatorParameter(
            OperatorType.DIVISION,
            EasyLevelDivisionOperatorParameter.MIN_AND_MAX_NUMBER,
            EasyLevelDivisionOperatorParameter.OPERATOR_PRESENCE_PERCENT);
  }

  public OperatorParameter loadMediumDivisionOperator() {
    return new OperatorParameter(
            OperatorType.DIVISION,
            MeduimLevelDivisionOperatorParameter.MIN_AND_MAX_NUMBER,
            MeduimLevelDivisionOperatorParameter.OPERATOR_PRESENCE_PERCENT);
  }

  public OperatorParameter loadDifficultDivisionOperator() {
    return new OperatorParameter(
            OperatorType.DIVISION,
            DifficultLevelDivisionOperatorParameter.MIN_AND_MAX_NUMBER,
            DifficultLevelDivisionOperatorParameter.OPERATOR_PRESENCE_PERCENT);
  }
}
