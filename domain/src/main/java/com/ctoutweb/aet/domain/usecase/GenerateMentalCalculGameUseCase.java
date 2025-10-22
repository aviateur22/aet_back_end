package com.ctoutweb.aet.domain.usecase;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.CalculParameterFactory;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.GameLevel;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.MentalCalculGame;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameInput;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameOutput;

import com.ctoutweb.aet.domain.usecase.base.IUseCase;
import com.ctoutweb.aet.domain.util.IEventBus;

import static com.ctoutweb.aet.domain.provider.CoreFactory.MENTAL_CALCUL_INSTANCE_PROVIDER;

public class GenerateMentalCalculGameUseCase implements IUseCase<GenerateMentalCalculGameUseCase.Input, GenerateMentalCalculGameUseCase.Output> {

  private final IEventBus eventBus;

    public GenerateMentalCalculGameUseCase(IEventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public Output execute(Input input) {
      GameLevel gameLevel = input.generateMentalCalculGameInput().getGameLevel();

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
            calculParameter
    );
  }
}
