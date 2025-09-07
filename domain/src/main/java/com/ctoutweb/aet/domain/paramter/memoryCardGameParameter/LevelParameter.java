package com.ctoutweb.aet.domain.paramter.memoryCardGameParameter;

import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.provider.CoreFactory;
import com.ctoutweb.aet.domain.provider.generateMemoryCardGame.IDomainModelInstanceProvider;

public class LevelParameter {
  private static final IDomainModelInstanceProvider PROVIDER = CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER;
  public static final IMinAndMax<Integer> EASY_LEVEL_CARD_TO_FIND_QUANTITY_RANDOM_BORN = PROVIDER.provideMinAndMaxImpl(2,5);
  public static final IMinAndMax<Integer> EASY_LEVEL_CARDS_QUANTITY_IN_GAME_RANDOM_BORN = PROVIDER.provideMinAndMaxImpl(9,13);
  public static final IMinAndMax<Integer> EASY_LEVEL_MAX_WRONG_RETURN_CARD_RANDOM_BORN = PROVIDER.provideMinAndMaxImpl(3,5);
  public static final IMinAndMax<Integer> EASY_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_RANDOM_BORN = PROVIDER.provideMinAndMaxImpl(40,55);
  public static final IMinAndMax<Integer> EASY_LEVEL_CARD_TO_FIND_QUANTITY_FIX_BORN = PROVIDER.provideMinAndMaxImpl(4,4);
  public static final IMinAndMax<Integer> EASY_LEVEL_CARDS_QUANTITY_IN_GAME_FIX_BORN = PROVIDER.provideMinAndMaxImpl(11,11);
  public static final IMinAndMax<Integer> EASY_LEVEL_MAX_WRONG_RETURN_CARD_FIX_BORN = PROVIDER.provideMinAndMaxImpl(4,4);
  public static final IMinAndMax<Integer> EASY_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_FIX_BORN = PROVIDER.provideMinAndMaxImpl(50,50);

  // Medium level
  public static final IMinAndMax<Integer> MEDIUM_LEVEL_CARD_TO_FIND_QUANTITY_RANDOM_BORN = PROVIDER.provideMinAndMaxImpl(4,8);
  public static final IMinAndMax<Integer> MEDIUM_LEVEL_CARDS_QUANTITY_IN_GAME_RANDOM_BORN = PROVIDER.provideMinAndMaxImpl(12,18);
  public static final IMinAndMax<Integer> MEDIUM_LEVEL_MAX_WRONG_RETURN_CARD_RANDOM_BORN = PROVIDER.provideMinAndMaxImpl(2,4);
  public static final IMinAndMax<Integer> MEDIUM_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_RANDOM_BORN = PROVIDER.provideMinAndMaxImpl(30,40);
  public static final IMinAndMax<Integer> MEDIUM_LEVEL_CARD_TO_FIND_QUANTITY_FIX_BORN = PROVIDER.provideMinAndMaxImpl(6,6);
  public static final IMinAndMax<Integer> MEDIUM_LEVEL_CARDS_QUANTITY_IN_GAME_FIX_BORN = PROVIDER.provideMinAndMaxImpl(15,15);
  public static final IMinAndMax<Integer> MEDIUM_LEVEL_MAX_WRONG_RETURN_CARD_FIX_BORN = PROVIDER.provideMinAndMaxImpl(3,3);
  public static final IMinAndMax<Integer> MEDIUM_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_FIX_BORN = PROVIDER.provideMinAndMaxImpl(35,35);

  // Difficult level
  public static final IMinAndMax<Integer> DIFFICULT_LEVEL_CARD_TO_FIND_QUANTITY_RANDOM_BORN = PROVIDER.provideMinAndMaxImpl(7,11);
  public static final IMinAndMax<Integer> DIFFICULT_LEVEL_CARDS_QUANTITY_IN_GAME_RANDOM_BORN = PROVIDER.provideMinAndMaxImpl(15,25);
  public static final IMinAndMax<Integer> DIFFICULT_LEVEL_MAX_WRONG_RETURN_CARD_RANDOM_BORN = PROVIDER.provideMinAndMaxImpl(1,2);
  public static final IMinAndMax<Integer> DIFFICULT_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_RANDOM_BORN = PROVIDER.provideMinAndMaxImpl(20,30);
  public static final IMinAndMax<Integer> DIFFICULT_LEVEL_CARD_TO_FIND_QUANTITY_FIX_BORN = PROVIDER.provideMinAndMaxImpl(9,9);
  public static final IMinAndMax<Integer> DIFFICULT_LEVEL_CARDS_QUANTITY_IN_GAME_FIX_BORN = PROVIDER.provideMinAndMaxImpl(20,20);
  public static final IMinAndMax<Integer> DIFFICULT_LEVEL_MAX_WRONG_RETURN_CARD_FIX_BORN = PROVIDER.provideMinAndMaxImpl(2,2);
  public static final IMinAndMax<Integer> DIFFICULT_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_FIX_BORN = PROVIDER.provideMinAndMaxImpl(25,25);


  // End Game Param
  public static final IMinAndMax<Integer> EXCELLENT_END_LEVEL_BORN = PROVIDER.provideMinAndMaxImpl(0,0);
  public static final IMinAndMax<Integer> VERY_GOOD_END_LEVEL_BORN = PROVIDER.provideMinAndMaxImpl(1,1);
  public static final IMinAndMax<Integer> GOOD_END_LEVEL_BORN = PROVIDER.provideMinAndMaxImpl(2,3);
  public static final IMinAndMax<Integer> MEDUIM_END_LEVEL_BORN = PROVIDER.provideMinAndMaxImpl(4,5);
  public static final IMinAndMax<Integer> BAD_END_LEVEL_BORN = PROVIDER.provideMinAndMaxImpl(6,7);
  public static final IMinAndMax<Integer> VERY_BAD_END_LEVEL_BORN = PROVIDER.provideMinAndMaxImpl(8, 9999999);
  public static final IMinAndMax<Integer> LOOSE_END_LEVEL_BORN = PROVIDER.provideMinAndMaxImpl(-1,-1);
}
