package com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.provider.impl;

import com.ctoutweb.aet.core.entity.gameText.ILoadGameEndLevelParameter;
import com.ctoutweb.aet.core.entity.IMinAndMax;
import com.ctoutweb.aet.core.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.core.entity.gameText.IGameTextPresentation;
import com.ctoutweb.aet.core.entity.gameText.gameEnd.EndErrorLevel;
import com.ctoutweb.aet.core.entity.gameText.gameEnd.IGameEndParameterByLevel;
import com.ctoutweb.aet.core.entity.memoryCardGame.impl.gameTextImpl.*;
import com.ctoutweb.aet.core.entity.gameText.gameEnd.IGameEndText;
import com.ctoutweb.aet.core.entity.memoryCardGame.*;
import com.ctoutweb.aet.core.entity.memoryCardGame.impl.*;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.GenerateNewMemoryCardGameUseCase;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.boundary.IGenerateNewGameRequest;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.boundary.impl.MemoryCardDataImpl;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.boundary.IGenerateNewGameResponse;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.provider.IDomainModelInstanceProvider;

/**
 * Renvoie des implementation
 */
public class DomainModelInstanceProviderImpl implements IDomainModelInstanceProvider {

  @Override
  public GenerateNewMemoryCardGameUseCase.Input provideInput(IGenerateNewGameRequest generateNewGameRequest) {
    return new GenerateNewMemoryCardGameUseCase.Input(generateNewGameRequest);
  }
  @Override
  public IGenerateNewGameResponse provideMemoryCardDataImpl(IGameCardData generateNewGameResponseSetter) {
    return new MemoryCardDataImpl(
            generateNewGameResponseSetter.getGameTextInformation(),
            generateNewGameResponseSetter.getCardToFindInGame(),
            generateNewGameResponseSetter.getCards(),
            generateNewGameResponseSetter.getGameLevel(),
            generateNewGameResponseSetter.getTimeToObserveBeforeStart(),
            generateNewGameResponseSetter.getCardToFindQuantity(),
            generateNewGameResponseSetter.getMaxErrorQuantity(),
            generateNewGameResponseSetter.getTimeInSecToFinish());
  }

  @Override
  public ILevelParameter provideLevelParameter(LevelType levelType) {
     return new LevelParameterImpl(levelType);
  }

  @Override
  public ILoadGameEndLevelParameter provideEndLevelParameter() {
    return new EndLevelParameterImpl();
  }

  @Override
  public <T> IMinAndMax provideMinAndMaxImpl(T min, T max) {
    return new MinAndMaxImpl(min, max);
  }


  @Override
  public ICardImage provideCardImageImpl(String cardFrontImagePath, String cardBackImagePath) {
    return new CardImageImpl(cardFrontImagePath, cardBackImagePath);
  }
  @Override
  public CardToFind provideCardToFind(ICardImage cardToFindInGame, String cardPresentation) {
    return new CardToFind(cardPresentation, cardToFindInGame);
  }
  @Override
  public Card provideCard(int id, ICardImage cardImage, boolean isCardToFind) {
    return new Card(id, cardImage, isCardToFind);
  }

  @Override
  public GamePresentationImpl provideGamePresentation(String gameTitle, String presentationText) {
    return new GamePresentationImpl(gameTitle, presentationText);
  }

  @Override
  public GenerateNewMemoryCardGameUseCase.Output provideOutput(IGenerateNewGameResponse memmoryCardGameData) {
    return new GenerateNewMemoryCardGameUseCase.Output(memmoryCardGameData);
  }
  @Override
  public IGameCardData provideGameCardData() {
    return new GameCardDataImpl();
  }

  @Override
  public IGameTextInformation provideGameTextInformation(
          String[] congratulationWords,
          String[] loosingWords,
         IGameTextPresentation gamePresentation,
          IGameEndParameterByLevel[] gameEndParameterByLevels
  ) {
    return new GameTextInformationImpl(
           congratulationWords,
           loosingWords,
            gamePresentation,
            gameEndParameterByLevels
    );
  }

  @Override
  public IGameEndText provideEndText(String endTitle, String endGameText) {
    return new EndTextImpl(endTitle, endGameText);
  }

  @Override
  public IGameEndParameterByLevel provideGameEndParameterByLevel(
          int minErrorLevel,
          int maxErrorLevel,
          EndErrorLevel resultLevel,
          IGameEndText gameEndText) {
    return new GameEndParamterByLevelImpl(minErrorLevel, maxErrorLevel, resultLevel, gameEndText);
  }

  @Override
  public ImageSelect provideImageSelect(String imagePath, boolean isToFind) {
    return new ImageSelect(imagePath, isToFind);
  }
}
