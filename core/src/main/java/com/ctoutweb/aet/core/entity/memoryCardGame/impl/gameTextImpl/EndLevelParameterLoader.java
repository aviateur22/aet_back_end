package com.ctoutweb.aet.core.entity.memoryCardGame.impl.gameTextImpl;

import com.ctoutweb.aet.core.entity.IMinAndMax;
import com.ctoutweb.aet.core.entity.gameText.gameEnd.EndErrorLevel;
import com.ctoutweb.aet.core.entity.gameText.gameEnd.IGameEndParameterByLevel;
import com.ctoutweb.aet.core.entity.gameText.gameEnd.IGameEndText;

import static com.ctoutweb.aet.core.provider.CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER;

public enum EndLevelParameterLoader {
  EXCELLENT(loadBadAnswerRange(EndErrorLevel.EXCELLENT), EndErrorLevel.EXCELLENT, loadEndText(EndErrorLevel.EXCELLENT)),
  VERY_GOOD(loadBadAnswerRange(EndErrorLevel.VERY_GOOD), EndErrorLevel.VERY_GOOD, loadEndText(EndErrorLevel.VERY_GOOD)),
  GOOD(loadBadAnswerRange(EndErrorLevel.GOOD), EndErrorLevel.GOOD, loadEndText(EndErrorLevel.GOOD)),
  MEDUIM(loadBadAnswerRange(EndErrorLevel.MEDUIM), EndErrorLevel.MEDUIM, loadEndText(EndErrorLevel.MEDUIM)),
  BAD(loadBadAnswerRange(EndErrorLevel.BAD), EndErrorLevel.BAD, loadEndText(EndErrorLevel.BAD)),
  VER_BAD(loadBadAnswerRange(EndErrorLevel.VERY_BAD), EndErrorLevel.VERY_BAD, loadEndText(EndErrorLevel.VERY_BAD)),
  LOOSE(loadBadAnswerRange(EndErrorLevel.LOOSE), EndErrorLevel.LOOSE, loadEndText(EndErrorLevel.LOOSE));

  private IMinAndMax<Integer> badAnswerRange;
  private IGameEndText endText;
  private EndErrorLevel endLevel;

  private EndLevelParameterLoader(IMinAndMax badAnswerRange, EndErrorLevel endLevel, IGameEndText endText) {
    this.endLevel = endLevel;
    this.badAnswerRange = badAnswerRange;
    this.endText = endText;
  }

  public static IMinAndMax loadBadAnswerRange(EndErrorLevel endLevel){
    return MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER
            .provideEndLevelParameter()
            .loadMinAndMaxBadAnswerEndGame(endLevel);
  }

  public static IGameEndText loadEndText(EndErrorLevel endLevel) {
    return MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER
            .provideEndLevelParameter()
            .loadGameEndText(endLevel);
  }

  public IGameEndText getEndText() {
    return this.endText;
  }
  public IMinAndMax<Integer> getBadAnswerRange() {
    return badAnswerRange;
  }
  public EndErrorLevel getEndLevel() {
    return this.endLevel;
  }
  public IGameEndParameterByLevel getGameEndParameterByLevel() {
    return MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideGameEndParameterByLevel(
            this.badAnswerRange.getMin(), this.badAnswerRange.getMax(), this.endLevel, this.endText
    );
  }

}
