package com.ctoutweb.aet.domain.provider.generateMemoryCardGame.impl;

import com.ctoutweb.aet.domain.entity.gameText.ILoadGameEndLevelParameter;
import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.domain.entity.gameText.IGameTextPresentation;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.EndErrorLevel;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndParameterByLevel;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndText;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.*;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.impl.CardImageImpl;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.impl.GameCardDataImpl;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.impl.MinAndMaxImpl;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.impl.gameTextImpl.*;
import com.ctoutweb.aet.domain.usecase.GenerateMemoryCardGameUseCase;
import com.ctoutweb.aet.domain.port.generateMemoryCardGame.IGenerateMemoryCardGameInput;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.impl.port.GenerateMemoryCardGameOutputImpl;
import com.ctoutweb.aet.domain.port.generateMemoryCardGame.IGenerateMemoryCardGameOutput;
import com.ctoutweb.aet.domain.provider.generateMemoryCardGame.IDomainModelInstanceProvider;

/**
 * Renvoie des implementation
 */
public class DomainModelInstanceProviderImpl implements IDomainModelInstanceProvider {

  @Override
  public GenerateMemoryCardGameUseCase.Input provideInput(IGenerateMemoryCardGameInput generateNewGameRequest) {
    return new GenerateMemoryCardGameUseCase.Input(generateNewGameRequest);
  }
  @Override
  public IGenerateMemoryCardGameOutput provideMemoryCardDataImpl(IGameCardData generateNewGameResponseSetter) {
    return new GenerateMemoryCardGameOutputImpl(
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
  public GenerateMemoryCardGameUseCase.Output provideOutput(IGenerateMemoryCardGameOutput memmoryCardGameData) {
    return new GenerateMemoryCardGameUseCase.Output(memmoryCardGameData);
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
