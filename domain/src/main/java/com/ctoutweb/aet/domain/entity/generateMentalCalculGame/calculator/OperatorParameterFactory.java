package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator;

import com.ctoutweb.aet.domain.entity.LevelType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.OperatorParameter;
import com.ctoutweb.aet.domain.provider.helper.LoadOperatorHelper;

public class OperatorParameterFactory {
  public static OperatorParameter loadOperatorParamter(OperatorType operatorType, LevelType level) {
    LoadOperatorHelper loadOperatorHelper = new LoadOperatorHelper();

    return switch (level) {
      case EASY ->  switch (operatorType) {
        case ADDITION -> loadOperatorHelper.loadEasyAdditionOperator();
        case SOUSTRACTION -> loadOperatorHelper.loadEasySoustractionOperator();
        case MULTIPLICATION -> loadOperatorHelper.loadEasyMultiplicationOperator();
        case DIVISION -> loadOperatorHelper.loadEasyDivisionOperator();
      };

      case MEDIUM -> switch (operatorType) {
        case ADDITION -> loadOperatorHelper.loadMediumAdditionOperator();
        case SOUSTRACTION -> loadOperatorHelper.loadMediumSoustractionOperator();
        case MULTIPLICATION -> loadOperatorHelper.loadMediumMultiplicationOperator();
        case DIVISION -> loadOperatorHelper.loadMediumDivisionOperator();
      };

      case DIFFICULT -> switch (operatorType) {
        case ADDITION -> loadOperatorHelper.loadDifficultAdditionOperator();
        case SOUSTRACTION -> loadOperatorHelper.loadDifficultSoustractionOperator();
        case MULTIPLICATION -> loadOperatorHelper.loadDifficultMultiplicationOperator();
        case DIVISION -> loadOperatorHelper.loadDifficultDivisionOperator();
      };
    };
  }
}
