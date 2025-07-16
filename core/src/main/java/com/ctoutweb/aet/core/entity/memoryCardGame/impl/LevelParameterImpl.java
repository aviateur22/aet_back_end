package com.ctoutweb.aet.core.entity.memoryCardGame.impl;

import com.ctoutweb.aet.core.entity.memoryCardGame.IBornRange;
import com.ctoutweb.aet.core.entity.memoryCardGame.ILevelParameter;
import com.ctoutweb.aet.core.entity.memoryCardGame.LevelType;
import com.ctoutweb.aet.core.entity.memoryCardGame.ParameterState;

import static com.ctoutweb.aet.core.usecase.memoryCardGame.gameParameter.LevelParameter.*;

public class LevelParameterImpl implements ILevelParameter {
  protected LevelType levelType;
  public LevelParameterImpl(LevelType levelType) {
    this.levelType = levelType;
  }

  @Override
  public IBornRange getCardsQuantityToFindBorn(ParameterState parameterState) {
    return switch (levelType) {
      case EASY -> switch (parameterState) {

        case RANDOM -> EASY_LEVEL_CARD_TO_FIND_QUANTITY_RANDOM_BORN;
        case FIX -> EASY_LEVEL_CARD_TO_FIND_QUANTITY_FIX_BORN;
      };
      case MEDIUM -> switch (parameterState) {

        case RANDOM -> MEDIUM_LEVEL_CARD_TO_FIND_QUANTITY_RANDOM_BORN;
        case FIX -> MEDIUM_LEVEL_CARD_TO_FIND_QUANTITY_FIX_BORN;
      };
      case DIFFICULT -> switch (parameterState) {

        case RANDOM -> DIFFICULT_LEVEL_CARD_TO_FIND_QUANTITY_RANDOM_BORN;
        case FIX -> DIFFICULT_LEVEL_CARD_TO_FIND_QUANTITY_FIX_BORN;
      };
    };
  }

  @Override
  public IBornRange getCardsQuantityInGameBorn(ParameterState parameterState) {
    return switch (levelType) {
      case EASY -> switch (parameterState) {

        case RANDOM -> EASY_LEVEL_CARDS_QUANTITY_IN_GAME_RANDOM_BORN;
        case FIX -> EASY_LEVEL_CARDS_QUANTITY_IN_GAME_FIX_BORN;
      };
      case MEDIUM -> switch (parameterState) {

        case RANDOM -> MEDIUM_LEVEL_CARDS_QUANTITY_IN_GAME_RANDOM_BORN;
        case FIX -> MEDIUM_LEVEL_CARDS_QUANTITY_IN_GAME_FIX_BORN;
      };
      case DIFFICULT -> switch (parameterState) {

        case RANDOM -> DIFFICULT_LEVEL_CARDS_QUANTITY_IN_GAME_RANDOM_BORN;
        case FIX -> DIFFICULT_LEVEL_CARDS_QUANTITY_IN_GAME_FIX_BORN;
      };
    };
  }

  @Override
  public IBornRange getMaxWrongReturnCardBorn(ParameterState parameterState) {
    return switch (levelType) {
      case EASY -> switch (parameterState) {

        case RANDOM -> EASY_LEVEL_MAX_WRONG_RETURN_CARD_RANDOM_BORN;
        case FIX -> EASY_LEVEL_MAX_WRONG_RETURN_CARD_FIX_BORN;
      };
      case MEDIUM -> switch (parameterState) {

        case RANDOM -> MEDIUM_LEVEL_MAX_WRONG_RETURN_CARD_RANDOM_BORN;
        case FIX -> MEDIUM_LEVEL_MAX_WRONG_RETURN_CARD_FIX_BORN;
      };
      case DIFFICULT -> switch (parameterState) {

        case RANDOM -> DIFFICULT_LEVEL_MAX_WRONG_RETURN_CARD_RANDOM_BORN;
        case FIX -> DIFFICULT_LEVEL_MAX_WRONG_RETURN_CARD_FIX_BORN;
      };
    };
  }

  @Override
  public IBornRange getTimeInSecToFinishBorn(ParameterState parameterState) {
    return switch (levelType) {
      case EASY -> switch (parameterState) {

        case RANDOM -> EASY_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_RANDOM_BORN;
        case FIX -> EASY_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_FIX_BORN;
      };
      case MEDIUM -> switch (parameterState) {

        case RANDOM -> MEDIUM_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_RANDOM_BORN;
        case FIX -> MEDIUM_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_FIX_BORN;
      };
      case DIFFICULT -> switch (parameterState) {

        case RANDOM -> DIFFICULT_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_RANDOM_BORN;
        case FIX -> DIFFICULT_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_FIX_BORN;
      };
    };
  }
}
