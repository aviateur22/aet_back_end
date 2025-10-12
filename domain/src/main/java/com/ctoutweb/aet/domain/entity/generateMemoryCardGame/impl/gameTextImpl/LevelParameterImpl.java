package com.ctoutweb.aet.domain.entity.generateMemoryCardGame.impl.gameTextImpl;

import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.ILevelParameter;
import com.ctoutweb.aet.domain.entity.LevelType;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.ParameterState;
import com.ctoutweb.aet.domain.gameConfiguration.generateMemoryCardGame.LevelParameter;

public class LevelParameterImpl implements ILevelParameter {
  protected LevelType levelType;
  public LevelParameterImpl(LevelType levelType) {
    this.levelType = levelType;
  }

  @Override
  public IMinAndMax getCardsQuantityToFindBorn(ParameterState parameterState) {
    return switch (levelType) {
      case EASY -> switch (parameterState) {

        case RANDOM -> LevelParameter.EASY_LEVEL_CARD_TO_FIND_QUANTITY_RANDOM_BORN;
        case FIX -> LevelParameter.EASY_LEVEL_CARD_TO_FIND_QUANTITY_FIX_BORN;
      };
      case MEDIUM -> switch (parameterState) {

        case RANDOM -> LevelParameter.MEDIUM_LEVEL_CARD_TO_FIND_QUANTITY_RANDOM_BORN;
        case FIX -> LevelParameter.MEDIUM_LEVEL_CARD_TO_FIND_QUANTITY_FIX_BORN;
      };
      case DIFFICULT -> switch (parameterState) {

        case RANDOM -> LevelParameter.DIFFICULT_LEVEL_CARD_TO_FIND_QUANTITY_RANDOM_BORN;
        case FIX -> LevelParameter.DIFFICULT_LEVEL_CARD_TO_FIND_QUANTITY_FIX_BORN;
      };
    };
  }

  @Override
  public IMinAndMax getCardsQuantityInGameBorn(ParameterState parameterState) {
    return switch (levelType) {
      case EASY -> switch (parameterState) {

        case RANDOM -> LevelParameter.EASY_LEVEL_CARDS_QUANTITY_IN_GAME_RANDOM_BORN;
        case FIX -> LevelParameter.EASY_LEVEL_CARDS_QUANTITY_IN_GAME_FIX_BORN;
      };
      case MEDIUM -> switch (parameterState) {

        case RANDOM -> LevelParameter.MEDIUM_LEVEL_CARDS_QUANTITY_IN_GAME_RANDOM_BORN;
        case FIX -> LevelParameter.MEDIUM_LEVEL_CARDS_QUANTITY_IN_GAME_FIX_BORN;
      };
      case DIFFICULT -> switch (parameterState) {

        case RANDOM -> LevelParameter.DIFFICULT_LEVEL_CARDS_QUANTITY_IN_GAME_RANDOM_BORN;
        case FIX -> LevelParameter.DIFFICULT_LEVEL_CARDS_QUANTITY_IN_GAME_FIX_BORN;
      };
    };
  }

  @Override
  public IMinAndMax getMaxWrongReturnCardBorn(ParameterState parameterState) {
    return switch (levelType) {
      case EASY -> switch (parameterState) {

        case RANDOM -> LevelParameter.EASY_LEVEL_MAX_WRONG_RETURN_CARD_RANDOM_BORN;
        case FIX -> LevelParameter.EASY_LEVEL_MAX_WRONG_RETURN_CARD_FIX_BORN;
      };
      case MEDIUM -> switch (parameterState) {

        case RANDOM -> LevelParameter.MEDIUM_LEVEL_MAX_WRONG_RETURN_CARD_RANDOM_BORN;
        case FIX -> LevelParameter.MEDIUM_LEVEL_MAX_WRONG_RETURN_CARD_FIX_BORN;
      };
      case DIFFICULT -> switch (parameterState) {

        case RANDOM -> LevelParameter.DIFFICULT_LEVEL_MAX_WRONG_RETURN_CARD_RANDOM_BORN;
        case FIX -> LevelParameter.DIFFICULT_LEVEL_MAX_WRONG_RETURN_CARD_FIX_BORN;
      };
    };
  }

  @Override
  public IMinAndMax getTimeInSecToFinishBorn(ParameterState parameterState) {
    return switch (levelType) {
      case EASY -> switch (parameterState) {

        case RANDOM -> LevelParameter.EASY_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_RANDOM_BORN;
        case FIX -> LevelParameter.EASY_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_FIX_BORN;
      };
      case MEDIUM -> switch (parameterState) {

        case RANDOM -> LevelParameter.MEDIUM_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_RANDOM_BORN;
        case FIX -> LevelParameter.MEDIUM_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_FIX_BORN;
      };
      case DIFFICULT -> switch (parameterState) {

        case RANDOM -> LevelParameter.DIFFICULT_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_RANDOM_BORN;
        case FIX -> LevelParameter.DIFFICULT_LEVEL_TIME_IN_SEC_TO_FINISH_GAME_FIX_BORN;
      };
    };
  }
}
