package com.ctoutweb.aet.domain.usecase;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.MentalCalculGame;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.TimeToCalculate;
import com.ctoutweb.aet.domain.injector.MethodInjectorContainer;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.ICardFaceIdent;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameInput;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameOutput;

import com.ctoutweb.aet.domain.usecase.base.IUseCase;
import com.ctoutweb.aet.domain.util.IEventBus;

import static com.ctoutweb.aet.domain.provider.CoreFactory.MENTAL_CALCUL_INSTANCE_PROVIDER;

public class GenerateMentalCalculGameUseCase implements IUseCase<GenerateMentalCalculGameUseCase.Input, GenerateMentalCalculGameUseCase.Output> {

  private final MentalCalculGame mentalCalculGame;

    public GenerateMentalCalculGameUseCase(IEventBus eventBus) {
        this.mentalCalculGame = MENTAL_CALCUL_INSTANCE_PROVIDER.provideMentalCalculGameInstance(eventBus);
    }

    @Override
  public Output execute(Input input) {

    return mentalCalculGame
            .generateGame(input)
            .getGeneratedGameData();
  }

  public record Input(IGenerateMentalCalculGameInput generateMentalCalculGameInput) implements IUseCase.Input {
  }

  public record Output(IGenerateMentalCalculGameOutput generateMentalCalculGameOutput) implements IUseCase.Output {
  }
}
