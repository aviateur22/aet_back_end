package com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.calculatorParamter.meduimLevel.operator;

import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.MinAndMaxImpl;

public class MeduimLevelMultiplicationOperatorParameter {
public static final IMinAndMax<Integer> MIN_AND_MAX_NUMBER = new MinAndMaxImpl<>(2, 10);
public static final int OPERATOR_PRESENCE_PERCENT = 10;
}
