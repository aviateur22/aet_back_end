package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText;

import com.ctoutweb.aet.domain.entity.gameText.gameEnd.EndErrorLevel;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndParameterByLevel;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndText;

public record GameEndLevelParameterImpl(
        int minErrorLevel,
        int maxErrorLevel,
        EndErrorLevel endErrorLevel,
        IGameEndText gameEndText
) implements IGameEndParameterByLevel {
  @Override
  public int getMinErrorLevel() {
    return minErrorLevel;
  }

  @Override
  public int getMaxErrorLevel() {
    return maxErrorLevel;
  }

  @Override
  public EndErrorLevel getEndErrorLevel() {
    return endErrorLevel;
  }

  @Override
  public IGameEndText getGameEndText() {
    return gameEndText;
  }
}
