package com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.difficultLevel;

import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.MinAndMaxImpl;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorType;

import java.util.List;

public class DifficultLevelCalculParameter {
public static final IMinAndMax<Integer> MIN_AND_MAX_NUMBER = new MinAndMaxImpl<>(2, 125);
public static final int CALCUL_QUANTITY = 10;
public static final int MIN_OPERATOR_BY_CALCUL = 2;
public static final int MAX_OPERATOR_BY_CALCUL = 3;
public static final boolean IS_NEGATIVE_RESULT_ACCEPTED = false;
public static final int TIME_AVAIL_BY_CALCUL = 10;
public static final List<OperatorType> ACCPETED_ASSOCIATED_OPERATOR_LIST = List.of(
          OperatorType.ADDITION, OperatorType.SOUSTRACTION
);
  public static final List<Double> LAST_CALCULTED_DIGIT_ACCEPEDTED_LIST = List.of(0.0, 2.0, 4.0, 6.0, 8.0);
  public static IMinAndMax<Double> MIN_MAX_ACCEPTED_CALCUL_RESULT = null;
  public static boolean ARE_INTERMEDIATE_CALCUL_POSITIVE = false;
  public static double MAX_INTERMEDIATE_CALCUL_RESULT = 350.0;
}
