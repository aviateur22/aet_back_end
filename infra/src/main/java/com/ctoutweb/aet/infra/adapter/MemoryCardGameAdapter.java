package com.ctoutweb.aet.infra.adapter;

import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndParameterByLevel;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.CardFace;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.ICardImage;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.ParameterState;
import com.ctoutweb.aet.domain.port.generateMemoryCardGame.IGenerateMemoryCardGameInput;
import com.ctoutweb.aet.domain.port.generateMemoryCardGame.IGenerateMemoryCardGameOutput;
import com.ctoutweb.aet.domain.port.generateMemoryCardGame.IGenerateMemoryCardGameGateway;
import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameRequestDto;
import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameResponseDto;
import com.ctoutweb.aet.infra.exception.CardException;
import com.ctoutweb.aet.infra.mapper.InfraMapper;
import com.ctoutweb.aet.infra.model.gameText.EndGameErrorLevel;
import com.ctoutweb.aet.infra.model.gameText.GameEndParameterByLevel;
import com.ctoutweb.aet.infra.model.gameText.IGameTextInformation;
import com.ctoutweb.aet.infra.model.image.IImageData;
import com.ctoutweb.aet.infra.model.gameText.GamePresentation;
import com.ctoutweb.aet.infra.model.memoryCardGame.*;
import com.ctoutweb.aet.infra.repository.IMemoryCardGameImageFaceRepository;
import com.ctoutweb.aet.infra.repository.IMemoryCardGameImageFamilyRepository;
import com.ctoutweb.aet.infra.repository.IMemoryCardGameImageRepository;
import com.ctoutweb.aet.infra.repository.entity.MemoryCardGameImageFamilyEntity;
import com.ctoutweb.aet.infra.service.imageLoaderService.IImageLoaderService;
import com.ctoutweb.aet.infra.util.ListUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.ctoutweb.aet.infra.constant.memoryCardGame.MemoryCardGameConstant.BACK_IMAGE_FACE_ID;
import static com.ctoutweb.aet.infra.constant.memoryCardGame.MemoryCardGameConstant.FRONT_IMAGE_FACE_ID;
import static com.ctoutweb.aet.infra.provider.InfraFactory.IMAGE_INSTANCE_PROVIDER;
import static com.ctoutweb.aet.infra.provider.InfraFactory.INFRA_MEMORY_CARD_INSTANCE_PROVIDER;

@Service
public class MemoryCardGameAdapter extends InfraMapper implements IGenerateMemoryCardGameGateway {
  private final IImageLoaderService imageLoaderService;
  private final IMemoryCardGameImageRepository memoryCardGameImageRepository;
  private final IMemoryCardGameImageFamilyRepository memoryCardGameImageFamilyRepository;
  private final IMemoryCardGameImageFaceRepository memoryCardGameImageFaceRepository;

  public MemoryCardGameAdapter(
          IImageLoaderService imageLoaderService,
          IMemoryCardGameImageRepository memoryCardGameImageRepository,
          IMemoryCardGameImageFamilyRepository memoryCardGameImageFamilyRepository,
          IMemoryCardGameImageFaceRepository memoryCardGameImageFaceRepository) {
    this.imageLoaderService = imageLoaderService;
    this.memoryCardGameImageRepository = memoryCardGameImageRepository;
    this.memoryCardGameImageFamilyRepository = memoryCardGameImageFamilyRepository;
    this.memoryCardGameImageFaceRepository = memoryCardGameImageFaceRepository;
  }

  public IGenerateMemoryCardGameInput mapToCoreInputBoundary(GenerateMemoryCardGameRequestDto dto) {
    var coreGameLevel = map(dto.gameLevel(), mapToCoreGameLevel());
    var coreGameParameter = map(dto.parameterState(), mapToCoreParameterState());

    var inputBoundary = INFRA_MEMORY_CARD_INSTANCE_PROVIDER.provideGenerateNewGameRequest(coreGameLevel, coreGameParameter);
    return inputBoundary;
  }

  @Override
  public String[] getAvailableCardName(CardFace cardFace) {
    MemoryCardGameImageFamilyEntity family = this.getRadomFamilyCard();
    var images = this.getImageData(cardFace, family);

    return images.stream().map(IImageData::getImageName).toArray(String[]::new);
  }

  /**
   * Selection d'une famille de carte de manière aléatoire
   * Exemple famille de type arbres ou hiboux. Permettra de charger les cartes d'une meme famille
   * @return MemoryCardGameImageFamilyEntity - Les données sur le famille de carte a charger
   */
  private MemoryCardGameImageFamilyEntity getRadomFamilyCard() {
    List<MemoryCardGameImageFamilyEntity> familyCards = this.memoryCardGameImageFamilyRepository.findAll();
    return ListUtil.selectOneRandomItemInList(familyCards);
  }

  /**
   * Récupération des données sur les images nom + path de l'image
   * @param cardFace CardFace - Face de la carte (Face avant ou arriere)
   * @param familyCard MemoryCardGameImageFamilyEntity - Famille de care a charger
   *                   (uniquement pour la face avant de la carte. La face arriere n'a pas besoind de cette donnée)
   * @return List<IImageData>
   */
  private List<IImageData> getImageData(CardFace cardFace, MemoryCardGameImageFamilyEntity familyCard) {
    return switch (cardFace) {
      case FRONT_FACE ->  {
        var cardFaceEntity = memoryCardGameImageFaceRepository
                .findById(FRONT_IMAGE_FACE_ID)
                .orElseThrow(() -> new CardException("Il n'y a pas de famille de disponible"));
        var images = this.memoryCardGameImageRepository.findImageByFaceAndFamilyList(cardFaceEntity, familyCard)
                .stream()
                .map(image-> IMAGE_INSTANCE_PROVIDER.providerImageData(image.getImagePath(), image.getRandomName()))
                .toList();
        yield images;
      }
      case BACK_FACE -> {
        var cardFaceEntity = memoryCardGameImageFaceRepository
                .findById(BACK_IMAGE_FACE_ID)
                .orElseThrow(() -> new CardException("Il n'y a pas de famille de disponible"));
        var images = this.memoryCardGameImageRepository.findImageByFaceList(cardFaceEntity)
                .stream()
                .map(image-> IMAGE_INSTANCE_PROVIDER.providerImageData(image.getImagePath(), image.getRandomName()))
                .toList();
        yield images;
      }
    };
  }

  public GenerateMemoryCardGameResponseDto mapToDto(IGenerateMemoryCardGameOutput response) {
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
  Function<GameLevel, com.ctoutweb.aet.domain.entity.generateMemoryCardGame.GameLevel> mapToCoreGameLevel() {
    return res -> com.ctoutweb.aet.domain.entity.generateMemoryCardGame.GameLevel.loadGameLevel(res.name());
  }
  Function<GameParameter, ParameterState> mapToCoreParameterState() {
    return res -> ParameterState.loadParameterState(res.name());
  }
  Function<IGenerateMemoryCardGameOutput, IGameTextInformation> mapToInfraGameTextInformation() {
    return  res -> {

      // Contenu des messages du jeu
      var coreGameText = res.getGameTextInformation();

      // Données text d'introuction du jeu
      GamePresentation infraGamePresentation = map(coreGameText, mapToGamePresentation());

      // parametres des messages de fin du jeu
      GameEndParameterByLevel[] InfraGameEndParameterByLevels = Arrays.stream(coreGameText.getGameEndParameterByLevels())
              .map(data -> map(data, mapToGameEndParameterByLevel()))
              .toArray(GameEndParameterByLevel[]::new);

      return INFRA_MEMORY_CARD_INSTANCE_PROVIDER.provideGameTextInformation(
              coreGameText.getCongratulationWords(),
              coreGameText.getLoosingWords(),
              infraGamePresentation,
              InfraGameEndParameterByLevels);
    };
  }
  Function<IGenerateMemoryCardGameOutput, CardToFind> mapToInfraCardToFind() {
      return res -> {
        var coreCardToFind = res.getCardToFindInGame();
        var infraCardImage = map(coreCardToFind.cardImage(), mapToInfraCardImage());
        return INFRA_MEMORY_CARD_INSTANCE_PROVIDER.provideCardToFind(coreCardToFind.cardTextExplanation(),infraCardImage);
      };
  }
  Function<ICardImage, CardImage> mapToInfraCardImage() {
    return res -> INFRA_MEMORY_CARD_INSTANCE_PROVIDER.provideCardImage(res.getImageFrontName(), res.getImageBackName());
  }
  Function<IGenerateMemoryCardGameOutput, Card[]> mapToInfraCards() {
    return res -> {
      var coreCards = res.getCards();
      return Arrays.stream(coreCards).map(card -> {
        var coreCardImage = map(card.cardImages(), mapToInfraCardImage());
        return INFRA_MEMORY_CARD_INSTANCE_PROVIDER.provideCard(card.id(), coreCardImage, card.isCardToFind());
      }).collect(Collectors.toList()).toArray(new Card[0]);
    };
  }
  Function<com.ctoutweb.aet.domain.entity.gameText.IGameTextInformation, GamePresentation> mapToGamePresentation() {

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
