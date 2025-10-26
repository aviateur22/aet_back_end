package com.ctoutweb.aet.domain.entity.generateMentalCalculGame;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;
import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.difficultLevel.DifficultLevelCalculParameter;
import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.easyLevel.EasyLevelCalculParameter;
import com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.meduimLevel.MeduimLevelCalculParameter;

/**
 * Chargement des parametre de calcul
 */
public class CalculParameterFactory {

    private final static CalculParameterFactory INSTANCE = new CalculParameterFactory();

    public static CalculParameter loadCalculParameterByLevel(GameLevel gameLevel) {

        CalculParameter calculParameter = switch (gameLevel) {
            case EASY -> INSTANCE.loadEasyLevelCalculParameter();
            case MEDIUM -> INSTANCE.loadMediumLevelCalculParameter();
            case DIFFICULT -> INSTANCE.loadDifficultLevelCalculParameter();
        };

        return calculParameter;
    }

    private CalculParameter loadEasyLevelCalculParameter() {
        return new CalculParameter(
                GameLevel.EASY,
                EasyLevelCalculParameter.CALCUL_QUANTITY,
                EasyLevelCalculParameter.MIN_OPERATOR_BY_CALCUL,
                EasyLevelCalculParameter.MAX_OPERATOR_BY_CALCUL,
                EasyLevelCalculParameter.IS_NEGATIVE_RESULT_ACCEPTED,
                EasyLevelCalculParameter.TIME_AVAIL_BY_CALCUL,
                EasyLevelCalculParameter.ACCPETED_ASSOCIATED_OPERATOR_LIST,
                EasyLevelCalculParameter.LAST_CALCULTED_DIGIT_ACCEPEDTED_LIST,
                EasyLevelCalculParameter.MIN_MAX_ACCEPTED_CALCUL_RESULT
        );
    }

    private CalculParameter loadMediumLevelCalculParameter() {
        return new CalculParameter(
                GameLevel.MEDIUM,
                MeduimLevelCalculParameter.CALCUL_QUANTITY,
                MeduimLevelCalculParameter.MIN_OPERATOR_BY_CALCUL,
                MeduimLevelCalculParameter.MAX_OPERATOR_BY_CALCUL,
                MeduimLevelCalculParameter.IS_NEGATIVE_RESULT_ACCEPTED,
                MeduimLevelCalculParameter.TIME_AVAIL_BY_CALCUL,
                MeduimLevelCalculParameter.ACCPETED_ASSOCIATED_OPERATOR_LIST,
                MeduimLevelCalculParameter.LAST_CALCULTED_DIGIT_ACCEPEDTED_LIST,
                MeduimLevelCalculParameter.MIN_MAX_ACCEPTED_CALCUL_RESULT
        );
    }

    private CalculParameter loadDifficultLevelCalculParameter() {
        return new CalculParameter(
                GameLevel.DIFFICULT,
                DifficultLevelCalculParameter.CALCUL_QUANTITY,
                DifficultLevelCalculParameter.MIN_OPERATOR_BY_CALCUL,
                DifficultLevelCalculParameter.MAX_OPERATOR_BY_CALCUL,
                DifficultLevelCalculParameter.IS_NEGATIVE_RESULT_ACCEPTED,
                DifficultLevelCalculParameter.TIME_AVAIL_BY_CALCUL,
                DifficultLevelCalculParameter.ACCPETED_ASSOCIATED_OPERATOR_LIST,
                DifficultLevelCalculParameter.LAST_CALCULTED_DIGIT_ACCEPEDTED_LIST,
                DifficultLevelCalculParameter.MIN_MAX_ACCEPTED_CALCUL_RESULT
        );
    }
}
