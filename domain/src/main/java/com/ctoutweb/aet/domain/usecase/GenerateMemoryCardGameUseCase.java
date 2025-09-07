package com.ctoutweb.aet.domain.usecase;

import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.GameLevel;
import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.ParameterState;
import com.ctoutweb.aet.domain.provider.CoreFactory;
import com.ctoutweb.aet.domain.port.generateMemoryCardGame.IGenerateMemoryCardGameOutput;
import com.ctoutweb.aet.domain.port.generateMemoryCardGame.IGenerateMemoryCardGameInput;
import com.ctoutweb.aet.domain.port.generateMemoryCardGame.IGenerateMemoryCardGameGateway;
import com.ctoutweb.aet.domain.usecase.base.IUseCase;

public class GenerateMemoryCardGameUseCase implements IUseCase<GenerateMemoryCardGameUseCase.Input, GenerateMemoryCardGameUseCase.Output> {
  private final IGenerateMemoryCardGameGateway generateNewGameGateway;
  public GenerateMemoryCardGameUseCase(IGenerateMemoryCardGameGateway generateNewGameGateway) {
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
            .getCardGameData();

    return CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideOutput(generateNewGameResponse);
  }

  public static class Input implements IUseCase.Input {
    private GameLevel gameLevel;
    private ParameterState parameterState;

    public Input(IGenerateMemoryCardGameInput gameLevelRequest) {
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
    private IGenerateMemoryCardGameOutput memoryGameCardData;
    public Output(IGenerateMemoryCardGameOutput memoryCard) {
      this.memoryGameCardData = memoryCard;
    }
    public IGenerateMemoryCardGameOutput getMemoryGameCardData() {
      return memoryGameCardData;
    }
  }

}
