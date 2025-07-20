package com.ctoutweb.aet.infra.provider.memoryCardGame;

import com.ctoutweb.aet.core.entity.memoryCardGame.ParameterState;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.boundary.IGenerateNewGameRequest;
import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameRequestDto;
import com.ctoutweb.aet.infra.model.gameText.GamePresentation;
import com.ctoutweb.aet.infra.model.gameText.GameTextInformationImpl;
import com.ctoutweb.aet.infra.model.memoryCardGame.adapter.GenerateNewGameRequestImpl;
import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameResponseDto;
import com.ctoutweb.aet.infra.model.memoryCardGame.*;

public class MemoryCardInstanceProviderImpl implements IMemoryCardInstanceProvider{
  @Override
  public GenerateMemoryCardGameRequestDto provideMemoryCardGameRequestDto(GameLevel level, GameParameter parameter) {
    return new GenerateMemoryCardGameRequestDto(level, parameter);
  }

  @Override
  public GenerateMemoryCardGameResponseDto provideMemoryCardGameResponseDto(
          IGameTextInformation gameTextInformation,
          CardToFind cardToFindInGame,
          Card[] cards,
          String gameLevel,
          short timeToObserveBeforeStart,
          short cardToFindQuantity,
          short errorQuantity,
          int timeInSecToFinis
  ) {
    return new GenerateMemoryCardGameResponseDto(
            gameTextInformation,
            cardToFindInGame,
            cards,
            gameLevel,
            timeToObserveBeforeStart,
            cardToFindQuantity,
            errorQuantity,
            timeInSecToFinis
    );
  }

  @Override
  public IGenerateNewGameRequest provideGenerateNewGameRequest(com.ctoutweb.aet.core.entity.memoryCardGame.GameLevel gameLevel, ParameterState parameterState) {
    return new GenerateNewGameRequestImpl(gameLevel, parameterState);
  }

  @Override
  public GamePresentation provideGamePresentation(String gameTitle, String presentationText) {
    return new com.ctoutweb.aet.infra.model.gameText.GamePresentation(gameTitle, presentationText);
  }

  @Override
  public IGameTextInformation provideGameTextInformation(
          String[] congratulationWords,
          String[] loosingWords,
          String gameLostText,
          String gameVictoryText,
          GamePresentation gamePresentation) {
    return new GameTextInformationImpl(
            congratulationWords, loosingWords, gameLostText, gameVictoryText, gamePresentation);
  }

  @Override
  public CardImage provideCardImage(String cardFrontImagePath, String cardBackImagePath) {
    return new CardImage(cardFrontImagePath, cardBackImagePath);
  }

  @Override
  public CardToFind provideCardToFind(String cardTextExplanation, CardImage cardImage) {
    return new CardToFind(cardTextExplanation, cardImage);
  }

  @Override
  public Card provideCard(int id, CardImage cardImage, boolean isCardToFind) {
    return new Card(id, cardImage, isCardToFind);
  }
}
