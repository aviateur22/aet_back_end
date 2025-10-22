//package com.ctoutweb.aet.domain.provider.helper;
//
//import com.ctoutweb.aet.domain.entity.LevelType;
//import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorParameterFactory;
//import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;
//import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.difficultLevel.DifficultLevelCalculParameter;
//import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.easyLevel.EasyLevelCalculParameter;
//import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.meduimLevel.MeduimLevelCalculParameter;
//
//public class LoadCalculHelper {
//
//  private OperatorParameterFactory loadOperatorHelper = new OperatorParameterFactory();
//
//  public CalculParameter loadEasyLevelCalculParameter() {
//    return new CalculParameter(
//            LevelType.EASY,
//            EasyLevelCalculParameter.CALCUL_QUANTITY,
//            EasyLevelCalculParameter.MIN_OPERATOR_BY_CALCUL,
//            EasyLevelCalculParameter.MAX_OPERATOR_BY_CALCUL,
//            EasyLevelCalculParameter.IS_NEGATIVE_RESULT_ACCEPTED,
//            EasyLevelCalculParameter.TIME_AVAIL_BY_CALCUL,
//            EasyLevelCalculParameter.ACCPETED_ASSOCIATED_OPERATOR_LIST,
//            EasyLevelCalculParameter.LAST_CALCULTED_DIGIT_ACCEPEDTED_LIST
//    );
//  }
//
//  public CalculParameter loadMediumLevelCalculParameter() {
//    return new CalculParameter(
//            LevelType.MEDIUM,
//            MeduimLevelCalculParameter.CALCUL_QUANTITY,
//            MeduimLevelCalculParameter.MIN_OPERATOR_BY_CALCUL,
//            MeduimLevelCalculParameter.MAX_OPERATOR_BY_CALCUL,
//            MeduimLevelCalculParameter.IS_NEGATIVE_RESULT_ACCEPTED,
//            MeduimLevelCalculParameter.TIME_AVAIL_BY_CALCUL,
//            MeduimLevelCalculParameter.ACCPETED_ASSOCIATED_OPERATOR_LIST,
//            MeduimLevelCalculParameter.LAST_CALCULTED_DIGIT_ACCEPEDTED_LIST
//    );
//  }
//
//  public CalculParameter loadDifficultLevelCalculParameter() {
//    return new CalculParameter(
//            LevelType.DIFFICULT,
//            DifficultLevelCalculParameter.CALCUL_QUANTITY,
//            DifficultLevelCalculParameter.MIN_OPERATOR_BY_CALCUL,
//            DifficultLevelCalculParameter.MAX_OPERATOR_BY_CALCUL,
//            DifficultLevelCalculParameter.IS_NEGATIVE_RESULT_ACCEPTED,
//            DifficultLevelCalculParameter.TIME_AVAIL_BY_CALCUL,
//            DifficultLevelCalculParameter.ACCPETED_ASSOCIATED_OPERATOR_LIST,
//            DifficultLevelCalculParameter.LAST_CALCULTED_DIGIT_ACCEPEDTED_LIST
//    );
//  }
//}
