package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText;

import com.ctoutweb.aet.domain.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.domain.entity.gameText.IGameTextPresentation;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndParameterByLevel;

public record GameTextInformation(IGameTextPresentation gamePresentation, IGameEndParameterByLevel[] gameEndParameterByLevels) implements IGameTextInformation {
    @Override
    public String[] getCongratulationWords() {
        return new String[0];
    }

    @Override
    public String[] getLoosingWords() {
        return new String[0];
    }

    @Override
    public IGameEndParameterByLevel[] getGameEndParameterByLevels() {
        return gameEndParameterByLevels;
    }

    @Override
    public IGameTextPresentation getGamePresentation() {
        return gamePresentation;
    }
}
