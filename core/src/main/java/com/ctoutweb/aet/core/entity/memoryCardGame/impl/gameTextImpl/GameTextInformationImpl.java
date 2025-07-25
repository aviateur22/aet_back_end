package com.ctoutweb.aet.core.entity.memoryCardGame.impl.gameTextImpl;

import com.ctoutweb.aet.core.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.core.entity.gameText.IGameTextPresentation;
import com.ctoutweb.aet.core.entity.gameText.gameEnd.IGameEndParameterByLevel;

public record GameTextInformationImpl(
        String[] congratulationWords,
        String[] loosingWords,
        IGameTextPresentation presentationText,
        IGameEndParameterByLevel[] gameEndTextByLevels
) implements IGameTextInformation {
  @Override
  public String[] getCongratulationWords() {
    return congratulationWords;
  }

  @Override
  public String[] getLoosingWords() {
    return loosingWords;
  }
  @Override
  public IGameTextPresentation getGamePresentation() {
    return presentationText;
  }

  @Override
  public IGameEndParameterByLevel[] getGameEndParameterByLevels() {
    return gameEndTextByLevels;
  }

}
