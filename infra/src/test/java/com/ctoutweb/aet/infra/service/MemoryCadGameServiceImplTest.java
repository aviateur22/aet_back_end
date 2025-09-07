package com.ctoutweb.aet.infra.service;

import com.ctoutweb.aet.domain.exception.ImageException;
import com.ctoutweb.aet.infra.adapter.memoryCardGame.MemoryCardGameAdapter;
import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameRequestDto;
import com.ctoutweb.aet.infra.exception.CardException;
import com.ctoutweb.aet.infra.model.gameText.GameEndParameterByLevel;
import com.ctoutweb.aet.infra.model.memoryCardGame.Card;
import com.ctoutweb.aet.infra.model.memoryCardGame.GameLevel;
import com.ctoutweb.aet.infra.model.memoryCardGame.GameParameter;
import com.ctoutweb.aet.infra.model.memoryCardGame.IGameTextInformation;
import com.ctoutweb.aet.infra.repository.IMemoryCardGameImageFaceRepository;
import com.ctoutweb.aet.infra.repository.IMemoryCardGameImageFamilyRepository;
import com.ctoutweb.aet.infra.repository.IMemoryCardGameImageRepository;
import com.ctoutweb.aet.infra.repository.entity.ImageEntity;
import com.ctoutweb.aet.infra.repository.entity.MemoryCardGameImageFaceEntity;
import com.ctoutweb.aet.infra.repository.entity.MemoryCardGameImageFamilyEntity;
import com.ctoutweb.aet.infra.service.gameService.memoryCardGameService.impl.MemoryCardGameServiceImpl;
import com.ctoutweb.aet.infra.service.imageLoaderService.IImageLoaderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ctoutweb.aet.domain.paramter.memoryCardGameParameter.GameData.*;
import static com.ctoutweb.aet.infra.constant.memoryCardGame.MemoryCardGameConstant.BACK_IMAGE_FACE_ID;
import static com.ctoutweb.aet.infra.constant.memoryCardGame.MemoryCardGameConstant.FRONT_IMAGE_FACE_ID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MemoryCadGameServiceImplTest {

  private MemoryCardGameAdapter memoryCardGameAdapter;
  @Mock
  private IImageLoaderService imageLoaderService;
  @Mock
  private IMemoryCardGameImageRepository memoryCardGameImageRepository;
  @Mock
  private IMemoryCardGameImageFamilyRepository memoryCardGameImageFamilyRepository;
  @Mock
  private IMemoryCardGameImageFaceRepository memoryCardGameImageFaceRepository;
  private

  MemoryCardGameServiceImpl memoryCardGameService;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    memoryCardGameAdapter = new MemoryCardGameAdapter(
            imageLoaderService,
            memoryCardGameImageRepository,
            memoryCardGameImageFamilyRepository,
            memoryCardGameImageFaceRepository);

    memoryCardGameService = new MemoryCardGameServiceImpl(memoryCardGameAdapter);

  }

 @ParameterizedTest
 @MethodSource("provideRequestDtoParameter")
  void generateMemoryCardGameData_should_generate_new_game(GameParameter gameParameter, GameLevel gameLevel) throws IOException {
    /**
     * given
     */
    GenerateMemoryCardGameRequestDto dto = new GenerateMemoryCardGameRequestDto(gameLevel, gameParameter);

    // Mock une famille de carte
    var cardFamilyMock = new MemoryCardGameImageFamilyEntity();
    cardFamilyMock.setFamilyName("hiboux");
    when(memoryCardGameImageFamilyRepository.findAll()).thenReturn(List.of(cardFamilyMock));

    // Mock face MemoryCardGameImageFaceEntity
    var frontCardFaceMock = new MemoryCardGameImageFaceEntity();
    frontCardFaceMock.setId(1);
    when(memoryCardGameImageFaceRepository.findById(FRONT_IMAGE_FACE_ID)).thenReturn(Optional.of(frontCardFaceMock));

    // Mock face arriere MemoryCardGameImageFaceEntity
    var backCardFaceMock = new MemoryCardGameImageFaceEntity();
    backCardFaceMock.setId(2);
    when(memoryCardGameImageFaceRepository.findById(BACK_IMAGE_FACE_ID)).thenReturn(Optional.of(backCardFaceMock));

    // Mock image
   var imageMock1 = new ImageEntity();
   imageMock1.setImagePath("path/image/bb.png");
   imageMock1.setRandomName("path/image/dd.png");

   var imageMock2 = new ImageEntity();
   imageMock2.setImagePath("abv");
   imageMock2.setRandomName("afg");

   when(memoryCardGameImageRepository.findImageByFaceAndFamilyList(any(), any())).thenReturn(
                    List.of(imageMock1, imageMock2));

   // Mock image Face arrire
   var backImageMock = new ImageEntity();
   backImageMock.setImagePath("arriere");
   backImageMock.setRandomName("fjfjfj");

   // Mock récupération carte arriee
   when(this.memoryCardGameImageRepository.findImageByFaceList(any())).thenReturn(List.of(backImageMock));

    /**
     * when
     */
    var result = memoryCardGameService.generateMemoryCardGameData(dto);

    /**
     * then
     */
    Assertions.assertNotNull(result);

    // Vérification du nombre d'image à trouver et vérification de la validité des carte présente dans le jeu
    var cardsToFindInGame = Arrays.stream(result.cards()).filter(Card::isCardToFind).collect(Collectors.toList());
    var cardToFindQuantity = result.cardToFindQuantity();

    Assertions.assertTrue(cardsToFindInGame.size() > 0);
    Assertions.assertEquals(cardToFindQuantity, cardsToFindInGame.size());

    // Verification du text
    var gameText = result.gameTextInformation();
    // Validation text de fin

    validateGameEndText(gameText);
    Assertions.assertEquals(GAME_TEXT_PRESENTATION, gameText.getGamePresentation().presentationText());
    Assertions.assertEquals(CONGRATULATION_WORDS, gameText.getCongratulationWords());
    Assertions.assertEquals(LOOSING_WORDS, gameText.getLoosingWords());
    Assertions.assertEquals(GAME_TITLE, gameText.getGamePresentation().gameTitle());

    Assertions.assertEquals(gameLevel.name(),result.gameLevel());
    Assertions.assertTrue(result.timeToObserveBeforeStart() > 0);
   Assertions.assertTrue(result.timeInSecToFinish() > 0);
   Assertions.assertTrue(result.errorQuantity() > 0);

  }
  private void validateGameEndText(IGameTextInformation gameText) {
    // Vérification des texts de fin de jeux
    for(GameEndParameterByLevel d : gameText.getGameEndParameterByLevels()) {
      switch (d.endResultLevel()) {
        case EXCELLENT ->  {
          Assertions.assertEquals(END_TEXT_EXCELLENT.getEndText(), d.endGameText().endText());
          Assertions.assertEquals(END_TEXT_EXCELLENT.getEndTitle(), d.endGameText().endTitle());
        }
        case VERY_GOOD -> {
          Assertions.assertEquals(END_TEXT_VERY_GOOD.getEndText(), d.endGameText().endText());
          Assertions.assertEquals(END_TEXT_VERY_GOOD.getEndTitle(), d.endGameText().endTitle());
        }
        case GOOD -> {
          Assertions.assertEquals(END_TEXT_GOOD.getEndText(), d.endGameText().endText());
          Assertions.assertEquals(END_TEXT_GOOD.getEndTitle(), d.endGameText().endTitle());
        }
        case MEDUIM -> {
          Assertions.assertEquals(END_TEXT_MEDIUM.getEndText(), d.endGameText().endText());
          Assertions.assertEquals(END_TEXT_MEDIUM.getEndTitle(), d.endGameText().endTitle());
        }
        case BAD -> {
          Assertions.assertEquals(END_TEXT_BAD.getEndText(), d.endGameText().endText());
          Assertions.assertEquals(END_TEXT_BAD.getEndTitle(), d.endGameText().endTitle());
        }
        case VERY_BAD -> {
          Assertions.assertEquals(END_TEXT_VERY_BAD.getEndText(), d.endGameText().endText());
          Assertions.assertEquals(END_TEXT_VERY_BAD.getEndTitle(), d.endGameText().endTitle());
        }
        case LOOSE -> {
          Assertions.assertEquals(END_TEXT_LOOSE.getEndText(), d.endGameText().endText());
          Assertions.assertEquals(END_TEXT_LOOSE.getEndTitle(), d.endGameText().endTitle());
        }
      }
    }
  }
  @ParameterizedTest
  @MethodSource("provideRequestDtoParameter")
  void generateMemoryCardGameData_should_throw_when_no_images_family_avail(GameParameter gameParameter, GameLevel gameLevel) throws IOException {
    /**
     * given
     */
    // Mock face arriere MemoryCardGameImageFaceEntity
    var backCardFaceMock = new MemoryCardGameImageFaceEntity();
    backCardFaceMock.setId(2);
    when(memoryCardGameImageFaceRepository.findById(BACK_IMAGE_FACE_ID))
            .thenReturn(Optional.of(backCardFaceMock));

    when(memoryCardGameImageRepository.findImageByFaceAndFamilyList(any(), any()))
            .thenReturn(List.of());

    GenerateMemoryCardGameRequestDto dto = new GenerateMemoryCardGameRequestDto(gameLevel, gameParameter);
    /**
     * then
     */
    Exception exception =  Assertions.assertThrows(CardException.class, () -> memoryCardGameService.generateMemoryCardGameData(dto));
    Assertions.assertEquals("Il n'y a pas de famille de disponible", exception.getMessage());
  }

  @ParameterizedTest
  @MethodSource("provideRequestDtoParameter")
  void generateMemoryCardGameData_should_throw_when_no_back_images_family_avail(GameParameter gameParameter, GameLevel gameLevel) throws IOException {
    /**
     * given
     */
    GenerateMemoryCardGameRequestDto dto = new GenerateMemoryCardGameRequestDto(gameLevel, gameParameter);

    // Mock une famille de carte
    var cardFamilyMock = new MemoryCardGameImageFamilyEntity();
    cardFamilyMock.setFamilyName("hiboux");
    when(memoryCardGameImageFamilyRepository.findAll()).thenReturn(List.of(cardFamilyMock));

    // Mock face MemoryCardGameImageFaceEntity
    var frontCardFaceMock = new MemoryCardGameImageFaceEntity();
    frontCardFaceMock.setId(1);
    when(memoryCardGameImageFaceRepository.findById(FRONT_IMAGE_FACE_ID)).thenReturn(Optional.of(frontCardFaceMock));

    // Mock image
    var imageMock1 = new ImageEntity();
    imageMock1.setImagePath("path/image/bb.png");
    imageMock1.setRandomName("path/image/dd.png");

    var imageMock2 = new ImageEntity();
    imageMock2.setImagePath("abv");
    imageMock2.setRandomName("afg");

    when(memoryCardGameImageRepository.findImageByFaceAndFamilyList(any(), any())).thenReturn(
            List.of(imageMock1, imageMock2));

    // Mock face arriere MemoryCardGameImageFaceEntity
    when(memoryCardGameImageFaceRepository.findById(BACK_IMAGE_FACE_ID)).thenReturn(Optional.empty());


    /**
     * then
     */
    Exception exception =  Assertions.assertThrows(CardException.class, ()->memoryCardGameService.generateMemoryCardGameData(dto));
    Assertions.assertEquals("Il n'y a pas de famille de disponible", exception.getMessage());
  }

  @ParameterizedTest
  @MethodSource("provideRequestDtoParameter")
  void generateMemoryCardGameData_should_throw_when_no_images_avail(GameParameter gameParameter, GameLevel gameLevel) throws IOException {
    /**
     * given
     */
    GenerateMemoryCardGameRequestDto dto = new GenerateMemoryCardGameRequestDto(gameLevel, gameParameter);

    // Mock une famille de carte
    var cardFamilyMock = new MemoryCardGameImageFamilyEntity();
    cardFamilyMock.setFamilyName("hiboux");
    when(memoryCardGameImageFamilyRepository.findAll()).thenReturn(List.of(cardFamilyMock));

    // Mock face MemoryCardGameImageFaceEntity
    var frontCardFaceMock = new MemoryCardGameImageFaceEntity();
    frontCardFaceMock.setId(1);
    when(memoryCardGameImageFaceRepository.findById(FRONT_IMAGE_FACE_ID)).thenReturn(Optional.of(frontCardFaceMock));

    when(memoryCardGameImageRepository.findImageByFaceAndFamilyList(any(), any())).thenReturn(
            List.of());

    /**
     * then
     */
    Exception exception =  Assertions.assertThrows(ImageException.class, () -> memoryCardGameService.generateMemoryCardGameData(dto));
    Assertions.assertEquals("Aucune image de disponible pour créer la face avant de la carte", exception.getMessage());
  }
  private static Stream<Arguments> provideRequestDtoParameter() {
    return Stream.of(
            Arguments.of(GameParameter.RANDOM, GameLevel.EASY),
            Arguments.of(GameParameter.RANDOM, GameLevel.MEDIUM),
            Arguments.of(GameParameter.RANDOM, GameLevel.DIFFICULT),
            Arguments.of(GameParameter.FIX, GameLevel.EASY),
            Arguments.of(GameParameter.FIX, GameLevel.MEDIUM),
            Arguments.of(GameParameter.FIX, GameLevel.DIFFICULT)
    );
  }
}
