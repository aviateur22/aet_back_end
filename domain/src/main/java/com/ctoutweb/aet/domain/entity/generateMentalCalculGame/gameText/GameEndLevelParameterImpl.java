package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText;

import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.gameText.ILoadGameEndLevelParameter;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.EndErrorLevel;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndText;

public record GameEndLevelParameterImpl(
        int minErrorLevel,
        int maxErrorLevel,
        EndErrorLevel resultLevel,
        IGameEndText gameEndText
) implements ILoadGameEndLevelParameter {
  @Override
  public IMinAndMax loadMinAndMaxBadAnswerEndGame(EndErrorLevel endLevel) {
    return null;
  }

  @Override
  public IGameEndText loadGameEndText(EndErrorLevel endLevel) {
    return null;
  }
}
