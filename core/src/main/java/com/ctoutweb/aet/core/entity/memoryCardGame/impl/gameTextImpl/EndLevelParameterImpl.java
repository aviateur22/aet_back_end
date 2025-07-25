package com.ctoutweb.aet.core.entity.memoryCardGame.impl.gameTextImpl;

import com.ctoutweb.aet.core.entity.gameText.ILoadGameEndLevelParameter;
import com.ctoutweb.aet.core.entity.IMinAndMax;
import com.ctoutweb.aet.core.entity.gameText.gameEnd.EndErrorLevel;
import com.ctoutweb.aet.core.entity.gameText.gameEnd.IGameEndText;

import static com.ctoutweb.aet.core.paramter.memoryCardGameParameter.GameData.*;
import static com.ctoutweb.aet.core.paramter.memoryCardGameParameter.LevelParameter.*;

public class EndLevelParameterImpl implements ILoadGameEndLevelParameter {
  @Override
  public IMinAndMax loadMinAndMaxBadAnswerEndGame(EndErrorLevel endLevel) {
    return switch (endLevel) {
      case EXCELLENT -> EXCELLENT_END_LEVEL_BORN;
      case VERY_GOOD -> VERY_GOOD_END_LEVEL_BORN;
      case GOOD -> GOOD_END_LEVEL_BORN;
      case MEDUIM -> MEDUIM_END_LEVEL_BORN;
      case BAD -> BAD_END_LEVEL_BORN;
      case VERY_BAD -> VERY_BAD_END_LEVEL_BORN;
      case LOOSE -> LOOSE_END_LEVEL_BORN;
    };
  }

  @Override
  public IGameEndText loadGameEndText(EndErrorLevel endLevel) {
    return switch (endLevel) {
      case EXCELLENT -> END_TEXT_EXCELLENT;
      case VERY_GOOD -> END_TEXT_VERY_GOOD;
      case GOOD -> END_TEXT_GOOD;
      case MEDUIM -> END_TEXT_MEDIUM;
      case BAD -> END_TEXT_BAD;
      case VERY_BAD -> END_TEXT_VERY_BAD;
      case LOOSE -> END_TEXT_LOOSE;
    };
  }
}
