package com.ctoutweb.aet.infra.service;

import com.ctoutweb.aet.core.exception.ImageException;
import com.ctoutweb.aet.infra.adapter.memoryCardGame.MemoryCardGameAdapter;
import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameRequestDto;
import com.ctoutweb.aet.infra.model.image.ImageDataImpl;
import com.ctoutweb.aet.infra.model.memoryCardGame.Card;
import com.ctoutweb.aet.infra.model.memoryCardGame.GameLevel;
import com.ctoutweb.aet.infra.model.memoryCardGame.GameParameter;
import com.ctoutweb.aet.infra.model.memoryCardGame.ImageFace;
import com.ctoutweb.aet.infra.repository.IMemoryCardGameImageFaceRepository;
import com.ctoutweb.aet.infra.repository.IMemoryCardGameImageFamilyRepository;
import com.ctoutweb.aet.infra.repository.IMemoryCardGameImageRepository;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.gameParameter.GameData.*;
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

    when(imageLoaderService.loadAllMemoryCardImages(ImageFace.FRONT_FACE))
            .thenReturn(
                    List.of(
                            new ImageDataImpl("path/image/bb.png", "bb.png"),
                            new ImageDataImpl("path/image/dd.png", "dd.png")));

    when(imageLoaderService.loadAllMemoryCardImages(ImageFace.BACK_FACE))
            .thenReturn(
                    List.of(new ImageDataImpl("path/image/back1.png", "path/image/back1.png")));

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

    Assertions.assertEquals(GAME_TEXT_PRESENTATION, gameText.getGamePresentation().presentationText());
    Assertions.assertEquals(GAME_LOOSE_TEXT, gameText.getGameLostText());
    Assertions.assertEquals(GAME_VICTORY_GAME, gameText.getGameVictoryText());
    Assertions.assertEquals(CONGRATULATION_WORDS, gameText.getCongratulationWords());
    Assertions.assertEquals(LOOSING_WORDS, gameText.getLoosingWords());
    Assertions.assertEquals(GAME_TITLE, gameText.getGamePresentation().gameTitle());

    Assertions.assertEquals(gameLevel.name(),result.gameLevel());
    Assertions.assertTrue(result.timeToObserveBeforeStart() > 0);
   Assertions.assertTrue(result.timeInSecToFinish() > 0);
   Assertions.assertTrue(result.errorQuantity() > 0);

  }

  @ParameterizedTest
  @MethodSource("provideRequestDtoParameter")
  void generateMemoryCardGameData_should_throw_when_no_front_images_avail(GameParameter gameParameter, GameLevel gameLevel) throws IOException {
    /**
     * given
     */
    GenerateMemoryCardGameRequestDto dto = new GenerateMemoryCardGameRequestDto(gameLevel, gameParameter);

    when(imageLoaderService.loadAllMemoryCardImages(ImageFace.FRONT_FACE))
            .thenReturn(List.of());

    when(imageLoaderService.loadAllMemoryCardImages(ImageFace.BACK_FACE))
            .thenReturn(
                    List.of(new ImageDataImpl("path/image/back1.png", "path/image/back1.png")));

    /**
     * then
     */
    Exception exception =  Assertions.assertThrows(ImageException.class, ()->memoryCardGameService.generateMemoryCardGameData(dto));
    Assertions.assertEquals("Aucune image de disponible pour créer la face avant de la carte", exception.getMessage());
  }

  @ParameterizedTest
  @MethodSource("provideRequestDtoParameter")
  void generateMemoryCardGameData_should_throw_when_no_back_images_avail(GameParameter gameParameter, GameLevel gameLevel) throws IOException {
    /**
     * given
     */
    GenerateMemoryCardGameRequestDto dto = new GenerateMemoryCardGameRequestDto(gameLevel, gameParameter);

    when(imageLoaderService.loadAllMemoryCardImages(ImageFace.FRONT_FACE))
            .thenReturn(List.of(
                    new ImageDataImpl("path/image/bb.png", "bb.png"),
                    new ImageDataImpl("path/image/dd.png", "dd.png")));

    when(imageLoaderService.loadAllMemoryCardImages(ImageFace.BACK_FACE))
            .thenReturn(
                    List.of());

    /**
     * then
     */
    Exception exception =  Assertions.assertThrows(ImageException.class, ()->memoryCardGameService.generateMemoryCardGameData(dto));
    Assertions.assertEquals("Aucune image de disponible pour créer la face arriere de la carte", exception.getMessage());
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
