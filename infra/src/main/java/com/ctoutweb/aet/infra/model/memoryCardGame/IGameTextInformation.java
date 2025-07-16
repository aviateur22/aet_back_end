package com.ctoutweb.aet.infra.model.memoryCardGame;

import com.ctoutweb.aet.infra.model.gameText.GamePresentation;

public interface IGameTextInformation {
    String[] getCongratulationWords();
    String[] getLoosingWords();
    String getGameLostText();
    String getGameVictoryText();
    GamePresentation getGamePresentation();

}
