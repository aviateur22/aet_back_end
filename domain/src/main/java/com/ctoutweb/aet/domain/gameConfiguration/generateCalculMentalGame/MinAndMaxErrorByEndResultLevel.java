package com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame;

import com.ctoutweb.aet.domain.entity.IMinAndMax;

import static com.ctoutweb.aet.domain.provider.CoreFactory.MENTAL_CALCUL_INSTANCE_PROVIDER;

public class MinAndMaxErrorByEndResultLevel {
  public static final IMinAndMax<Integer> EXCELLENT_END_LEVEL_ERROR_BORN = MENTAL_CALCUL_INSTANCE_PROVIDER.provideMinAndMaxInstance(0,0);
  public static final IMinAndMax<Integer> VERY_GOOD_END_LEVEL_ERROR_BORN = MENTAL_CALCUL_INSTANCE_PROVIDER.provideMinAndMaxInstance(1,1);
  public static final IMinAndMax<Integer> GOOD_END_LEVEL_BORN = MENTAL_CALCUL_INSTANCE_PROVIDER.provideMinAndMaxInstance(2,3);
  public static final IMinAndMax<Integer> MEDUIM_END_LEVEL_BORN = MENTAL_CALCUL_INSTANCE_PROVIDER.provideMinAndMaxInstance(4,5);
  public static final IMinAndMax<Integer> BAD_END_LEVEL_BORN = MENTAL_CALCUL_INSTANCE_PROVIDER.provideMinAndMaxInstance(6,7);
  public static final IMinAndMax<Integer> VERY_BAD_END_LEVEL_BORN = MENTAL_CALCUL_INSTANCE_PROVIDER.provideMinAndMaxInstance(8, 9999999);
  public static final IMinAndMax<Integer> LOOSE_END_LEVEL_BORN = MENTAL_CALCUL_INSTANCE_PROVIDER.provideMinAndMaxInstance(-1,-1);
}
