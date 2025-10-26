package com.ctoutweb.aet.domain.usecase;

import com.ctoutweb.aet.domain.annotation.DomainService;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.CalculParameterFactory;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.GameLevel;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.MentalCalculGame;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;
import com.ctoutweb.aet.domain.port.RandomProvider;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameGateway;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameInput;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameOutput;

import com.ctoutweb.aet.domain.usecase.base.IUseCase;
import com.ctoutweb.aet.domain.util.IEventBus;

import static com.ctoutweb.aet.domain.provider.CoreFactory.MENTAL_CALCUL_INSTANCE_PROVIDER;

@DomainService
public class GenerateMentalCalculGameUseCase implements IUseCase<GenerateMentalCalculGameUseCase.Input, GenerateMentalCalculGameUseCase.Output> {

  private final IEventBus eventBus;
  private final RandomProvider randomProvider;
  private final IGenerateMentalCalculGameGateway gateway;

    public GenerateMentalCalculGameUseCase(
            IEventBus eventBus,
            RandomProvider randomProvider,
            IGenerateMentalCalculGameGateway gateway) {
        this.eventBus = eventBus;
        this.randomProvider = randomProvider;
        this.gateway = gateway;
    }

    @Override
    public Output execute(Input input) {
      String inputRequestedGameLevel = input.generateMentalCalculGameInput().getGameLevel();
      GameLevel gameLevel = GameLevel.loadGameLevel(inputRequestedGameLevel);

      return loadMentalGameInstance(gameLevel)
            .generateGame(input)
            .getGeneratedGameData();
  }

  public record Input(IGenerateMentalCalculGameInput generateMentalCalculGameInput) implements IUseCase.Input {
  }

  public record Output(IGenerateMentalCalculGameOutput generateMentalCalculGameOutput) implements IUseCase.Output {
  }

  private MentalCalculGame loadMentalGameInstance(GameLevel gameLevel) {
    CalculParameter calculParameter = CalculParameterFactory.loadCalculParameterByLevel(gameLevel);

    return MENTAL_CALCUL_INSTANCE_PROVIDER.provideMentalCalculGameInstance(
            eventBus,
            calculParameter,
            randomProvider,
            gateway);
  }
}
