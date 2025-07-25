package com.ctoutweb.aet.infra.model.memoryCardGame;

import com.ctoutweb.aet.infra.model.gameText.GameEndParameterByLevel;
import com.ctoutweb.aet.infra.model.gameText.GamePresentation;

public interface IGameTextInformation {
    String[] getCongratulationWords();
    String[] getLoosingWords();;
    GamePresentation getGamePresentation();
    GameEndParameterByLevel[] getGameEndParameterByLevels();

}
