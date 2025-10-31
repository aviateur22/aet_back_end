package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText;

import com.ctoutweb.aet.domain.entity.gameText.IGameTextPresentation;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.EndErrorLevel;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndParameterByLevel;
import com.ctoutweb.aet.domain.provider.CoreFactory;

import java.util.Arrays;

import static com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.CalculMentalText.GAME_TEXT_PRESENTATION;
import static com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame.CalculMentalText.GAME_TITLE;

/**
 * Chargement du text du jeu
 */
public class GameTextManager {

    public GameTextInformation loadGameTextInformation() {

        IGameTextPresentation gameTextPresentation = loadGamePresentation();
        IGameEndParameterByLevel[] gameEndLevelParameters = loadEndLevelParameterText();

        return new GameTextInformation(gameTextPresentation, gameEndLevelParameters);
    }

    /**
     * Text de présentation
     *
     * @return Le text de presentation
     */
    private IGameTextPresentation loadGamePresentation() {
        return CoreFactory.MENTAL_CALCUL_INSTANCE_PROVIDER.provideGameTextPresentationInstance(GAME_TEXT_PRESENTATION, GAME_TITLE);
    }

    /**
     * Chargement du text de fin par nombre d'erreur dans le jeu
     *
     * @return Liste avec le text de fin par niveau d'erreur
     */
    private IGameEndParameterByLevel[] loadEndLevelParameterText() {
        EndLevelParameterFactory endLevelParameterFactory = EndLevelParameterFactory.getInstance();
        return Arrays.stream(EndErrorLevel.values())
                .map(endLevelParameterFactory::loadGameEndLevelParameterImpl)
                .toArray(IGameEndParameterByLevel[]::new);
    }
}
