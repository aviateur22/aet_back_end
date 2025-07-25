package com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.provider;

import com.ctoutweb.aet.core.entity.gameText.ILoadGameEndLevelParameter;
import com.ctoutweb.aet.core.entity.IMinAndMax;
import com.ctoutweb.aet.core.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.core.entity.gameText.IGameTextPresentation;
import com.ctoutweb.aet.core.entity.gameText.gameEnd.EndErrorLevel;
import com.ctoutweb.aet.core.entity.gameText.gameEnd.IGameEndParameterByLevel;
import com.ctoutweb.aet.core.entity.gameText.gameEnd.IGameEndText;
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
  ILoadGameEndLevelParameter provideEndLevelParameter();
  <T> IMinAndMax provideMinAndMaxImpl(T min, T max);
  ICardImage provideCardImageImpl(String cardFrontImagePath, String cardBackImagePath);
  CardToFind provideCardToFind(ICardImage cardToFindInGame, String cardPresentation);
  Card provideCard(int id, ICardImage cardImage, boolean isCardToFind);

  // Données text du jeu //
  IGameTextPresentation provideGamePresentation(String gameTitle, String presentationText);
  IGameTextInformation provideGameTextInformation(
          String[] congratulationWords,
          String[] loosingWords,
          IGameTextPresentation gamePresentation,
          IGameEndParameterByLevel[] gameEndParameterByLevels
  );
  IGameEndText provideEndText(String endTitle, String endGameText);
  IGameEndParameterByLevel provideGameEndParameterByLevel(
          int minErrorLevel,
          int maxErrorLevel,
          EndErrorLevel resultLevel,
          IGameEndText gameEndText
  );

  ImageSelect provideImageSelect(String imagePath, boolean isToFind);



}
