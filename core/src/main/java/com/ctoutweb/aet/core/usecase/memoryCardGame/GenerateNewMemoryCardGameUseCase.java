package com.ctoutweb.aet.core.usecase.memoryCardGame;

import com.ctoutweb.aet.core.entity.memoryCardGame.GameLevel;
import com.ctoutweb.aet.core.entity.memoryCardGame.ParameterState;
import com.ctoutweb.aet.core.provider.CoreFactory;
import com.ctoutweb.aet.core.usecase.memoryCardGame.boundary.IGenerateNewGameResponse;
import com.ctoutweb.aet.core.usecase.IUseCase;
import com.ctoutweb.aet.core.usecase.memoryCardGame.boundary.IGenerateNewGameRequest;
import com.ctoutweb.aet.core.usecase.memoryCardGame.port.IGenerateNewGameGateway;

public class GenerateNewMemoryCardGameUseCase implements IUseCase<GenerateNewMemoryCardGameUseCase.Input, GenerateNewMemoryCardGameUseCase.Output> {
  private final IGenerateNewGameGateway generateNewGameGateway;
  public GenerateNewMemoryCardGameUseCase(IGenerateNewGameGateway generateNewGameGateway) {
    this.generateNewGameGateway = generateNewGameGateway;
  }

  @Override
  public Output execute(Input input) {
    GameLevel gameLevel = input.getGameLevel();

    ParameterState parameterState = input.getParameterState();

    var memoryCardGameRules =  CoreFactory.MEMORY_CARD_BUSINESS_INSTANCE_PROVIDER.provideMemoryCardGameRules(generateNewGameGateway, gameLevel, parameterState);

    var generateNewGameResponse = memoryCardGameRules
            .initialize()
            .loadAllFrontImagePaths()
            .selectOneBackImagePath()
            .generateCardsForGame()
            .getData();

    return CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideOutput(generateNewGameResponse);
  }

  public static class Input implements IUseCase.Input {
    private GameLevel gameLevel;
    private ParameterState parameterState;

    public Input(IGenerateNewGameRequest gameLevelRequest) {
      this.gameLevel = gameLevelRequest.getGameLevel();
      this.parameterState = gameLevelRequest.getParameterState();
    }
    public GameLevel getGameLevel() {
      return gameLevel;
    }
    public ParameterState getParameterState() {
      return this.parameterState;
    }
  }

  public static class Output implements IUseCase.Output {
    private IGenerateNewGameResponse memoryGameCardData;
    public Output(IGenerateNewGameResponse memoryCard) {
      this.memoryGameCardData = memoryCard;
    }
    public IGenerateNewGameResponse getMemoryGameCardData() {
      return memoryGameCardData;
    }
  }

}
