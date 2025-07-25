package com.ctoutweb.aet.core.entity.memoryCardGame.impl.gameTextImpl;

import com.ctoutweb.aet.core.entity.gameText.gameEnd.EndErrorLevel;
import com.ctoutweb.aet.core.entity.gameText.gameEnd.IGameEndParameterByLevel;
import com.ctoutweb.aet.core.entity.gameText.gameEnd.IGameEndText;

public record GameEndParamterByLevelImpl(int minError, int maxError, EndErrorLevel endResultLevel, IGameEndText endGameText)
        implements IGameEndParameterByLevel {
  @Override
  public int getMinErrorLevel() {
    return minError;
  }

  @Override
  public int getMaxErrorLevel() {
    return maxError;
  }

  @Override
  public EndErrorLevel getEndErrorLevel() {
    return endResultLevel;
  }

  @Override
  public IGameEndText getGameEndText() {
    return endGameText;
  }
}
