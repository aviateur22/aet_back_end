package com.ctoutweb.aet.domain.usecase;

import com.ctoutweb.aet.domain.annotation.DomainService;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.CalculParameterFactory;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.GameLevel;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.MentalCalculGame;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.paramter.CalculParameter;
import com.ctoutweb.aet.domain.event.logger.LogEvent;
import com.ctoutweb.aet.domain.event.logger.LogLevel;
import com.ctoutweb.aet.domain.injector.ConstructorInjectorContainer;
import com.ctoutweb.aet.domain.port.RandomProvider;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameGateway;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameInput;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameOutput;

import com.ctoutweb.aet.domain.usecase.base.IUseCase;
import com.ctoutweb.aet.domain.event.IEventBus;

import java.lang.reflect.InvocationTargetException;

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

      try{
        CalculParameter calculParameter = CalculParameterFactory.loadCalculParameterByLevel(gameLevel);
        MENTAL_CALCUL_INSTANCE_PROVIDER.loadContainer(eventBus, calculParameter, randomProvider, gateway);
        return ConstructorInjectorContainer.getInstance().instanciate(MentalCalculGame.class);

      } catch (InvocationTargetException | InstantiationException | IllegalAccessException ex) {
        eventBus.publish(new LogEvent(LogLevel.ERROR, ex.getMessage()));
        return null;
      }
  }
}
