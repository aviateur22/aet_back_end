package com.ctoutweb.aet.infra.provider.memoryCardGame;

import com.ctoutweb.aet.core.entity.memoryCardGame.ParameterState;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.boundary.IGenerateNewGameRequest;
import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameRequestDto;
import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameResponseDto;
import com.ctoutweb.aet.infra.model.gameText.GamePresentation;
import com.ctoutweb.aet.infra.model.memoryCardGame.*;

public interface IMemoryCardInstanceProvider {
  GenerateMemoryCardGameRequestDto provideMemoryCardGameRequestDto(GameLevel level, GameParameter parameter) ;
  GenerateMemoryCardGameResponseDto provideMemoryCardGameResponseDto(
          IGameTextInformation gameTextInformation,
          CardToFind cardToFindInGame,
          Card[] cards,
          String gameLevel,
          short timeToObserveBeforeStart,
          short cardToFindQuantity,
          short errorQuantity,
          int timeInSecToFinis
  );
  IGenerateNewGameRequest provideGenerateNewGameRequest(com.ctoutweb.aet.core.entity.memoryCardGame.GameLevel gameLevel, ParameterState parameterState);

  GamePresentation provideGamePresentation(String gameTitle, String presentationText);

  IGameTextInformation provideGameTextInformation(
          String[] congratulationWords,
          String[] loosingWords,
          String gameLostText,
          String gameVictoryText,
          GamePresentation gamePresentation
  );
  CardImage provideCardImage(String cardFrontImagePath, String  cardBackImagePath);

  CardToFind provideCardToFind(String cardTextExplanation, CardImage cardImage);

  Card provideCard(int id, CardImage cardImage, boolean isCardToFind);
}


