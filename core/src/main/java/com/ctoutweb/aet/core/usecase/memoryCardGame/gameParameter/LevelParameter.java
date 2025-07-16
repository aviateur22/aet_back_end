package com.ctoutweb.aet.core.usecase.memoryCardGame.gameParameter;

import com.ctoutweb.aet.core.entity.memoryCardGame.IBornRange;
import com.ctoutweb.aet.core.provider.CoreFactory;
import com.ctoutweb.aet.core.usecase.memoryCardGame.provider.IDomainModelInstanceProvider;

public class LevelParameter {
  private static final IDomainModelInstanceProvider PROVIDER = CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER;
  public static final IBornRange EASY_LEVEL_CARD_TO_FIND_QUANTITY_RANDOM_BORN = PROVIDER.provideBornRangeImpl(2,5);
  public static final IBornRange EASY_LEVEL_CARDS_QUANTITY_IN_GAME_RANDOM_BORN = PROVIDER.provideBornRangeImpl(9,13);
  public static final IBornRange EASY_LEVEL_MAX_WRONG_RETURN_CARD_RANDOM_BORN = PROVIDER.provideBornRangeImpl(3,5);
  public static final IBornRange EASY_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_RANDOM_BORN = PROVIDER.provideBornRangeImpl(40,55);
  public static final IBornRange EASY_LEVEL_CARD_TO_FIND_QUANTITY_FIX_BORN = PROVIDER.provideBornRangeImpl(4,4);
  public static final IBornRange EASY_LEVEL_CARDS_QUANTITY_IN_GAME_FIX_BORN = PROVIDER.provideBornRangeImpl(11,11);
  public static final IBornRange EASY_LEVEL_MAX_WRONG_RETURN_CARD_FIX_BORN = PROVIDER.provideBornRangeImpl(4,4);
  public static final IBornRange EASY_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_FIX_BORN = PROVIDER.provideBornRangeImpl(50,50);

  // Medium level
  public static final IBornRange MEDIUM_LEVEL_CARD_TO_FIND_QUANTITY_RANDOM_BORN = PROVIDER.provideBornRangeImpl(4,8);
  public static final IBornRange MEDIUM_LEVEL_CARDS_QUANTITY_IN_GAME_RANDOM_BORN = PROVIDER.provideBornRangeImpl(12,18);
  public static final IBornRange MEDIUM_LEVEL_MAX_WRONG_RETURN_CARD_RANDOM_BORN = PROVIDER.provideBornRangeImpl(2,4);
  public static final IBornRange MEDIUM_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_RANDOM_BORN = PROVIDER.provideBornRangeImpl(30,40);
  public static final IBornRange MEDIUM_LEVEL_CARD_TO_FIND_QUANTITY_FIX_BORN = PROVIDER.provideBornRangeImpl(6,6);
  public static final IBornRange MEDIUM_LEVEL_CARDS_QUANTITY_IN_GAME_FIX_BORN = PROVIDER.provideBornRangeImpl(15,15);
  public static final IBornRange MEDIUM_LEVEL_MAX_WRONG_RETURN_CARD_FIX_BORN = PROVIDER.provideBornRangeImpl(3,3);
  public static final IBornRange MEDIUM_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_FIX_BORN = PROVIDER.provideBornRangeImpl(35,35);

  // Difficult level
  public static final IBornRange DIFFICULT_LEVEL_CARD_TO_FIND_QUANTITY_RANDOM_BORN = PROVIDER.provideBornRangeImpl(7,11);
  public static final IBornRange DIFFICULT_LEVEL_CARDS_QUANTITY_IN_GAME_RANDOM_BORN = PROVIDER.provideBornRangeImpl(15,25);
  public static final IBornRange DIFFICULT_LEVEL_MAX_WRONG_RETURN_CARD_RANDOM_BORN = PROVIDER.provideBornRangeImpl(1,2);
  public static final IBornRange DIFFICULT_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_RANDOM_BORN = PROVIDER.provideBornRangeImpl(20,30);
  public static final IBornRange DIFFICULT_LEVEL_CARD_TO_FIND_QUANTITY_FIX_BORN = PROVIDER.provideBornRangeImpl(9,9);
  public static final IBornRange DIFFICULT_LEVEL_CARDS_QUANTITY_IN_GAME_FIX_BORN = PROVIDER.provideBornRangeImpl(20,20);
  public static final IBornRange DIFFICULT_LEVEL_MAX_WRONG_RETURN_CARD_FIX_BORN = PROVIDER.provideBornRangeImpl(2,2);
  public static final IBornRange DIFFICULT_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_FIX_BORN = PROVIDER.provideBornRangeImpl(25,25);




}
