package com.ctoutweb.aet.infra.provider.memoryCardGame;

import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.ParameterState;
import com.ctoutweb.aet.domain.port.generateMemoryCardGame.IGenerateMemoryCardGameInput;
import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameRequestDto;
import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameResponseDto;
import com.ctoutweb.aet.infra.model.gameText.EndGameErrorLevel;
import com.ctoutweb.aet.infra.model.gameText.EndGameText;
import com.ctoutweb.aet.infra.model.gameText.GameEndParameterByLevel;
import com.ctoutweb.aet.infra.model.gameText.GamePresentation;
import com.ctoutweb.aet.infra.model.memoryCardGame.*;

public interface IMemoryCardInstanceProvider {
  GenerateMemoryCardGameRequestDto provideMemoryCardGameRequestDto(GameLevel level, GameParameter parameter) ;
  GenerateMemoryCardGameResponseDto provideMemoryCardGameResponseDto(
          IGameTextInformation gameTextInformation,
          CardToFind cardToFindInGame,
          Card[] cards,
          String gameLevel,
          int timeToObserveBeforeStart,
          int cardToFindQuantity,
          int errorQuantity,
          int timeInSecToFinis
  );
  IGenerateMemoryCardGameInput provideGenerateNewGameRequest(com.ctoutweb.aet.domain.entity.generateMemoryCardGame.GameLevel gameLevel, ParameterState parameterState);

  GamePresentation provideGamePresentation(String gameTitle, String presentationText);

  IGameTextInformation provideGameTextInformation(
          String[] congratulationWords,
          String[] loosingWords,
          GamePresentation gamePresentation,
          GameEndParameterByLevel[] gameEndParameterByLevels
  );

  GameEndParameterByLevel provideGameEndParameterByLevel(
          int minError,
          int maxError,
          EndGameErrorLevel endResultLevel,
          EndGameText endGameText);
  EndGameText provideEndGameText(String EndTitle, String endGameText);
  CardImage provideCardImage(String cardFrontImagePath, String  cardBackImagePath);

  CardToFind provideCardToFind(String cardTextExplanation, CardImage cardImage);

  Card provideCard(int id, CardImage cardImage, boolean isCardToFind);
}


