package com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.difficultLevel.operator;

import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.MinAndMaxImpl;

import static com.ctoutweb.aet.domain.provider.CoreFactory.MENTAL_CALCUL_INSTANCE_PROVIDER;

public class DifficultLevelSoustractionOperatorParameter {
public static final IMinAndMax<Integer> MIN_AND_MAX_NUMBER = MENTAL_CALCUL_INSTANCE_PROVIDER.provideMinAndMaxInstance(2, 100);
public static final int OPERATOR_PRESENCE_PERCENT = 40;
}
