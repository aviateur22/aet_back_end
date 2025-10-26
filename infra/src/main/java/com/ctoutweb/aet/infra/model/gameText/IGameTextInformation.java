package com.ctoutweb.aet.infra.model.gameText;

public interface IGameTextInformation {
    String[] getCongratulationWords();
    String[] getLoosingWords();;
    GamePresentation getGamePresentation();
    GameEndParameterByLevel[] getGameEndParameterByLevels();

}
