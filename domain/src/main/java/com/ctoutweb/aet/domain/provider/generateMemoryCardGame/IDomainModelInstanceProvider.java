package com.ctoutweb.aet.domain.provider.generateMemoryCardGame;

import com.ctoutweb.aet.domain.entity.gameText.ILoadGameEndLevelParameter;
import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.domain.entity.gameText.IGameTextPresentation;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.EndErrorLevel;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndParameterByLevel;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndText;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.*;
import com.ctoutweb.aet.domain.port.generateMemoryCardGame.IGenerateMemoryCardGameInput;
import com.ctoutweb.aet.domain.port.generateMemoryCardGame.IGenerateMemoryCardGameOutput;
import com.ctoutweb.aet.domain.usecase.GenerateMemoryCardGameUseCase;

public interface IDomainModelInstanceProvider {
  GenerateMemoryCardGameUseCase.Input provideInput(IGenerateMemoryCardGameInput generateNewGameRequest);
  GenerateMemoryCardGameUseCase.Output provideOutput(IGenerateMemoryCardGameOutput memmoryCardGameData);
  IGameCardData provideGameCardData();
  IGenerateMemoryCardGameOutput provideMemoryCardDataImpl(IGameCardData generateNewGameResponseSetter);
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
