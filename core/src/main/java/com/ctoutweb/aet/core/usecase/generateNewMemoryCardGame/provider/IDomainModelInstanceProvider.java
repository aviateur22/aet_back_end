package com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.provider;

import com.ctoutweb.aet.core.entity.gameText.GamePresentation;
import com.ctoutweb.aet.core.entity.gameText.GameTextInformation;
import com.ctoutweb.aet.core.entity.memoryCardGame.*;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.GenerateNewMemoryCardGameUseCase;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.boundary.IGenerateNewGameRequest;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.boundary.IGenerateNewGameResponse;

public interface IDomainModelInstanceProvider {
  GenerateNewMemoryCardGameUseCase.Input provideInput(IGenerateNewGameRequest generateNewGameRequest);
  GenerateNewMemoryCardGameUseCase.Output provideOutput(IGenerateNewGameResponse memmoryCardGameData);
  IGameCardData provideGameCardData();
  IGenerateNewGameResponse provideMemoryCardDataImpl(IGameCardData generateNewGameResponseSetter);
  ILevelParameter provideLevelParameter(LevelType levelType);
  IBornRange provideBornRangeImpl(int min, int max);
  ICardImage provideCardImageImpl(String cardFrontImagePath, String cardBackImagePath);
  CardToFind provideCardToFind(ICardImage cardToFindInGame, String cardPresentation);
  Card provideCard(int id, ICardImage cardImage, boolean isCardToFind);
  GamePresentation provideGamePresentation(String gameTitle, String presentationText);
  GameTextInformation provideGameTextInformation(
          String[] congratulationWords,
          String[] loosingWords,
          String gameLostText,
          String gameVictoryText,
          GamePresentation gamePresentation
  );
  ImageSelect provideImageSelect(String imagePath, boolean isToFind);



}
