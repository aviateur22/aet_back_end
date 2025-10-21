package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText;

import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.EndErrorLevel;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndParameterByLevel;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndText;

import static com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.CalculMentalText.*;
import static com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.MinAndMaxErrorByEndResultLevel.*;

public class EndLevelParameterFactory {
    private static final EndLevelParameterFactory INSTANCE = new EndLevelParameterFactory();

    public static EndLevelParameterFactory getInstance() {
        return INSTANCE;
    }

    private EndLevelParameterFactory() {

    }

    public IGameEndParameterByLevel loadGameEndLevelParameterImpl(EndErrorLevel endStatus) {
        return loadGameEndLevelParam(endStatus);
    }

    private IGameEndParameterByLevel loadGameEndLevelParam(EndErrorLevel endStatus) {
        int minError = loadMinAndMaxErrorByLevel(endStatus).getMin();
        int maxError = loadMinAndMaxErrorByLevel(endStatus).getMax();
        IGameEndText gameEndText = loadGameText(endStatus);
        return new GameEndLevelParameterImpl(minError, maxError, endStatus, gameEndText);
    }

    private IMinAndMax<Integer> loadMinAndMaxErrorByLevel(EndErrorLevel endStatus) {
        return switch (endStatus) {
            case EXCELLENT -> EXCELLENT_END_LEVEL_ERROR_BORN;
            case VERY_GOOD -> VERY_GOOD_END_LEVEL_ERROR_BORN;
            case GOOD -> GOOD_END_LEVEL_BORN;
            case MEDUIM -> MEDUIM_END_LEVEL_BORN;
            case BAD -> BAD_END_LEVEL_BORN;
            case VERY_BAD -> VERY_BAD_END_LEVEL_BORN;
            case LOOSE -> LOOSE_END_LEVEL_BORN;
        };
    }

    private IGameEndText loadGameText(EndErrorLevel endErrorLevel) {
        return switch (endErrorLevel) {
            case EXCELLENT -> END_TEXT_EXCELLENT;
            case VERY_GOOD -> END_TEXT_VERY_GOOD;
            case GOOD -> END_TEXT_GOOD;
            case MEDUIM -> END_TEXT_MEDIUM;
            case BAD -> END_TEXT_BAD;
            case VERY_BAD -> END_TEXT_VERY_BAD;
            case LOOSE -> END_TEXT_LOOSE;
        };
    }

}
