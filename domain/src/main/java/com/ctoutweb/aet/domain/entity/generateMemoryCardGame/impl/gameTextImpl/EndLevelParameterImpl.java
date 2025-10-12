package com.ctoutweb.aet.domain.entity.generateMemoryCardGame.impl.gameTextImpl;

import com.ctoutweb.aet.domain.entity.gameText.ILoadGameEndLevelParameter;
import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.EndErrorLevel;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndText;
import com.ctoutweb.aet.domain.gameConfiguration.generateMemoryCardGame.GameData;
import com.ctoutweb.aet.domain.gameConfiguration.generateMemoryCardGame.LevelParameter;

public class EndLevelParameterImpl implements ILoadGameEndLevelParameter {
  @Override
  public IMinAndMax loadMinAndMaxBadAnswerEndGame(EndErrorLevel endLevel) {
    return switch (endLevel) {
      case EXCELLENT -> LevelParameter.EXCELLENT_END_LEVEL_BORN;
      case VERY_GOOD -> LevelParameter.VERY_GOOD_END_LEVEL_BORN;
      case GOOD -> LevelParameter.GOOD_END_LEVEL_BORN;
      case MEDUIM -> LevelParameter.MEDUIM_END_LEVEL_BORN;
      case BAD -> LevelParameter.BAD_END_LEVEL_BORN;
      case VERY_BAD -> LevelParameter.VERY_BAD_END_LEVEL_BORN;
      case LOOSE -> LevelParameter.LOOSE_END_LEVEL_BORN;
    };
  }

  @Override
  public IGameEndText loadGameEndText(EndErrorLevel endLevel) {
    return switch (endLevel) {
      case EXCELLENT -> GameData.END_TEXT_EXCELLENT;
      case VERY_GOOD -> GameData.END_TEXT_VERY_GOOD;
      case GOOD -> GameData.END_TEXT_GOOD;
      case MEDUIM -> GameData.END_TEXT_MEDIUM;
      case BAD -> GameData.END_TEXT_BAD;
      case VERY_BAD -> GameData.END_TEXT_VERY_BAD;
      case LOOSE -> GameData.END_TEXT_LOOSE;
    };
  }
}
