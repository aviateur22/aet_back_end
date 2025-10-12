package com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.meduimLevel;

import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.MinAndMaxImpl;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorType;

import java.util.List;

public class MeduimLevelCalculParameter {
public static final IMinAndMax<Integer> MIN_AND_MAX_NUMBER = new MinAndMaxImpl<>(2, 125);
public static final int CALCUL_QUANTITY = 10;
public static final int MIN_OPERATOR_BY_CALCUL = 2;
public static final int MAX_OPERATOR_BY_CALCUL = 3;
public static final boolean IS_NEGATIVE_RESULT_ACCEPTED = false;
public static final int TIME_AVAIL_BY_CALCUL = 10;
public static final List<OperatorType> ACCPETED_ASSOCIATED_OPERATOR_LIST = List.of(
          OperatorType.ADDITION, OperatorType.SOUSTRACTION
);
  public static final List<Integer> LAST_CALCULTED_DIGIT_ACCEPEDTED_LIST = List.of(0, 2, 4, 6, 8);
}
