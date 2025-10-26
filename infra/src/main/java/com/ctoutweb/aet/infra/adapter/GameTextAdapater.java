package com.ctoutweb.aet.infra.adapter;

import com.ctoutweb.aet.domain.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndParameterByLevel;
import com.ctoutweb.aet.infra.mapper.InfraMapper;
import com.ctoutweb.aet.infra.model.gameText.EndGameErrorLevel;
import com.ctoutweb.aet.infra.model.gameText.GameEndParameterByLevel;
import com.ctoutweb.aet.infra.model.gameText.GamePresentation;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Function;

import static com.ctoutweb.aet.infra.provider.InfraFactory.INFRA_MEMORY_CARD_INSTANCE_PROVIDER;

@Component
public class GameTextAdapater  extends InfraMapper implements IToInfraGameText<IGameTextInformation, com.ctoutweb.aet.infra.model.gameText.IGameTextInformation>{

    @Override
    public com.ctoutweb.aet.infra.model.gameText.IGameTextInformation mapTextToInfra(IGameTextInformation textFromDomainModel) {

        // Données text d'introuction du jeu
        GamePresentation infraGamePresentation = map(textFromDomainModel, mapToGamePresentation());

        // parametres des messages de fin du jeu
        GameEndParameterByLevel[] InfraGameEndParameterByLevels = Arrays.stream(textFromDomainModel.getGameEndParameterByLevels())
                .map(data -> map(data, mapToGameEndParameterByLevel()))
                .toArray(GameEndParameterByLevel[]::new);

        return INFRA_MEMORY_CARD_INSTANCE_PROVIDER.provideGameTextInformation(
                textFromDomainModel.getCongratulationWords(),
                textFromDomainModel.getLoosingWords(),
                infraGamePresentation,
                InfraGameEndParameterByLevels);
    }

    Function<IGameTextInformation, GamePresentation> mapToGamePresentation() {

        return res -> {
            var gameTitle = res.getGamePresentation().getGameTitle();
            var gamePresentation = res.getGamePresentation().getPresentationText();

            return INFRA_MEMORY_CARD_INSTANCE_PROVIDER.provideGamePresentation(gameTitle, gamePresentation);
        };
    }

    Function<IGameEndParameterByLevel, GameEndParameterByLevel> mapToGameEndParameterByLevel() {
        return coreRes -> {
            var minErrorOnLevel = coreRes.getMinErrorLevel();
            var maxErrorOnLevel = coreRes.getMaxErrorLevel();
            var coreEndErrorLevel = coreRes.getEndErrorLevel();

            var endGameText = INFRA_MEMORY_CARD_INSTANCE_PROVIDER.provideEndGameText(
                    coreRes.getGameEndText().getEndTitle(),
                    coreRes.getGameEndText().getEndText()
            );

            EndGameErrorLevel infraEndErrorLevel = switch (coreEndErrorLevel) {
                case EXCELLENT -> EndGameErrorLevel.EXCELLENT;
                case VERY_GOOD -> EndGameErrorLevel.VERY_GOOD;
                case GOOD -> EndGameErrorLevel.GOOD;
                case MEDUIM -> EndGameErrorLevel.MEDUIM;
                case BAD -> EndGameErrorLevel.BAD;
                case VERY_BAD -> EndGameErrorLevel.VERY_BAD;
                case LOOSE -> EndGameErrorLevel.LOOSE;
            };
            return INFRA_MEMORY_CARD_INSTANCE_PROVIDER.provideGameEndParameterByLevel(
                    minErrorOnLevel,
                    maxErrorOnLevel,
                    infraEndErrorLevel,
                    endGameText
            );
        };
    }

}
