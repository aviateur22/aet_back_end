package com.ctoutweb.aet.infra.adapter.memoryCardGame.boundary;

import com.ctoutweb.aet.core.entity.memoryCardGame.CardFace;
import com.ctoutweb.aet.core.entity.memoryCardGame.ParameterState;
import com.ctoutweb.aet.core.usecase.memoryCardGame.boundary.IGenerateNewGameRequest;
import com.ctoutweb.aet.core.usecase.memoryCardGame.boundary.IGenerateNewGameResponse;
import com.ctoutweb.aet.core.usecase.memoryCardGame.port.IGenerateNewGameGateway;
import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameRequestDto;
import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameResponseDto;
import com.ctoutweb.aet.infra.exception.ImageException;
import com.ctoutweb.aet.infra.mapper.InfraMapper;
import com.ctoutweb.aet.infra.model.IImageData;
import com.ctoutweb.aet.infra.model.gameText.GamePresentation;
import com.ctoutweb.aet.infra.model.memoryCardGame.*;
import com.ctoutweb.aet.infra.service.imageLoaderService.IImageLoaderService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.ctoutweb.aet.infra.provider.InfraFactory.INFRA_MEMORY_CARD_INSTANCE_PROVIDER;
@Service
public class MemoryCardGameAdapter extends InfraMapper implements IGenerateNewGameGateway {
  private final IImageLoaderService imageLoaderService;

  public MemoryCardGameAdapter(IImageLoaderService imageLoaderService) {
    this.imageLoaderService = imageLoaderService;
  }

  public IGenerateNewGameRequest mapToCoreInputBoundary(GenerateMemoryCardGameRequestDto dto) {
    var coreGameLevel = map(dto.gameLevel(), mapToCoreGameLevel());
    var coreGameParameter = map(dto.parameterState(), mapToCoreParameterState());

    var inputBoundary = INFRA_MEMORY_CARD_INSTANCE_PROVIDER.provideGenerateNewGameRequest(coreGameLevel, coreGameParameter);
    return inputBoundary;
  }

  public GenerateMemoryCardGameResponseDto mapToDto(IGenerateNewGameResponse response) {
    var infraTextInformation = map(response , mapToInfraGameTextInformation());
    var infraCardToFind = map(response, mapToInfraCardToFind());
    var infraCards = map(response, mapToInfraCards());

    return INFRA_MEMORY_CARD_INSTANCE_PROVIDER.provideMemoryCardGameResponseDto(
            infraTextInformation,
            infraCardToFind,
            infraCards,
            response.getGameLevel().name(),
            response.getTimeToObserveBeforeStart(),
            response.getCardToFindQuantity(),
            response.getMaxErrorQuantity(),
            response.getTimeInSecToFinish()
    );
  }
  Function<GameLevel, com.ctoutweb.aet.core.entity.memoryCardGame.GameLevel> mapToCoreGameLevel() {
    return res -> com.ctoutweb.aet.core.entity.memoryCardGame.GameLevel.loadGameLevel(res.name());
  }
  Function<GameParameter, ParameterState> mapToCoreParameterState() {
    return res -> ParameterState.loadParameterState(res.name());
  }
  Function<IGenerateNewGameResponse, IGameTextInformation> mapToInfraGameTextInformation() {
    return  res -> {
      var coreGameText = res.getGameTextInformation();
      return INFRA_MEMORY_CARD_INSTANCE_PROVIDER.provideGameTextInformation(
              coreGameText.getCongratulationWords(),
              coreGameText.getLoosingWords(),
              coreGameText.getGameLostText(),
              coreGameText.getGameVictoryText(),
              map(coreGameText, mapToGamePresentation())
      );
    };
  }
  Function<IGenerateNewGameResponse, CardToFind> mapToInfraCardToFind() {
      return res -> {
        var coreCardToFind = res.getCardToFindInGame();
        var infraCardImage = map(coreCardToFind.cardImage(), mapToInfraCardImage());
        return INFRA_MEMORY_CARD_INSTANCE_PROVIDER.provideCardToFind(coreCardToFind.cardTextExplanation(),infraCardImage);
      };
  }
  Function<com.ctoutweb.aet.core.entity.memoryCardGame.ICardImage, CardImage> mapToInfraCardImage() {
    return res -> INFRA_MEMORY_CARD_INSTANCE_PROVIDER.provideCardImage(res.getCardFrontImagePath(), res.getCardBackImagePath());
  }
  Function<IGenerateNewGameResponse, Card[]> mapToInfraCards() {
    return res -> {
      var coreCards = res.getCards();
      return Arrays.stream(coreCards).map(card -> {
        var coreCardImage = map(card.cardImages(), mapToInfraCardImage());
        return INFRA_MEMORY_CARD_INSTANCE_PROVIDER.provideCard(coreCardImage, card.isCardToFind());
      }).collect(Collectors.toList()).toArray(new Card[0]);
    };
  }

  Function<com.ctoutweb.aet.core.entity.gameText.IGameTextInformation, GamePresentation> mapToGamePresentation() {

    return res -> {
      var gameTitle = res.getGamePresentation().gameTitle();
      var gamePresentation = res.getGamePresentation().presentationText();

      return INFRA_MEMORY_CARD_INSTANCE_PROVIDER.provideGamePresentation(gameTitle, gamePresentation);
    };
  }

  @Override
  public String[] getAllAvailableCardPaths(CardFace cardFace) {
    try {
      return switch (cardFace) {
        case FRONT_FACE -> this.imageLoaderService.loadAllMemoryCardImages(ImageFace.FRONT_FACE).stream().map(IImageData::getImagePath).toArray(String[]::new);
        case BACK_FACE -> this.imageLoaderService.loadAllMemoryCardImages(ImageFace.BACK_FACE).stream().map(IImageData::getImagePath).toArray(String[]::new);
      };
    } catch (IOException exception) {
      throw new ImageException("Il y a eut une erreur de chargement des images");
    }
  }
}
