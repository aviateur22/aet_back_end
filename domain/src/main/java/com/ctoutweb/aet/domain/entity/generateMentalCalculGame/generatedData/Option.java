package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData;

import com.ctoutweb.aet.domain.entity.LevelType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.GameLevel;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;

public class Option implements IOption {
    private String gameLevel;
    private boolean isMultipleChoiceVisible;

    public Option loadOptionGame(CalculParameter calculParameter) {
        GameLevel level = calculParameter.getGameLevel();

        this.gameLevel = level.name();
        this.isMultipleChoiceVisible = level == GameLevel.EASY;

        return this;
    }

    public static Option getOption() {
        return new Option();
    }

    @Override
    public String getGameLevel() {
        return this.gameLevel;
    }

    @Override
    public boolean getIsMultipleChoiceVisible() {
        return this.isMultipleChoiceVisible;
    }
}
