package com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.provider.impl;

import com.ctoutweb.aet.core.entity.gameText.GamePresentation;
import com.ctoutweb.aet.core.entity.gameText.GameTextInformation;
import com.ctoutweb.aet.core.entity.memoryCardGame.*;
import com.ctoutweb.aet.core.entity.memoryCardGame.impl.GameCardDataImpl;
import com.ctoutweb.aet.core.entity.memoryCardGame.impl.LevelParameterImpl;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.GenerateNewMemoryCardGameUseCase;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.boundary.IGenerateNewGameRequest;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.boundary.impl.MemoryCardDataImpl;
import com.ctoutweb.aet.core.entity.memoryCardGame.impl.BornRangeImpl;
import com.ctoutweb.aet.core.entity.memoryCardGame.impl.CardImageImpl;
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
  public IBornRange provideBornRangeImpl(int min, int max) {
    return new BornRangeImpl(min, max);
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
  public GamePresentation provideGamePresentation(String gameTitle, String presentationText) {
    return new GamePresentation(gameTitle, presentationText);
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
  public GameTextInformation provideGameTextInformation(
          String[] congratulationWords,
          String[] loosingWords,
          String gameLostText,
          String gameVictoryText,
          GamePresentation gamePresentation
  ) {
    return new GameTextInformation(
           congratulationWords,
           loosingWords,
            gameLostText,
            gameVictoryText,
            gamePresentation
    );
  }

  @Override
  public ImageSelect provideImageSelect(String imagePath, boolean isToFind) {
    return new ImageSelect(imagePath, isToFind);
  }
}
