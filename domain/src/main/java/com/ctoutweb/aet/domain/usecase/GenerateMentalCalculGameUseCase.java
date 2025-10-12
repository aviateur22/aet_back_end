package com.ctoutweb.aet.domain.usecase;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.MentalCalculGame;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameInput;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameOutput;

import com.ctoutweb.aet.domain.usecase.base.IUseCase;

import static com.ctoutweb.aet.domain.provider.CoreFactory.MENTAL_CALCUL_INSTANCE_PROVIDER;
public class GenerateMentalCalculGameUseCase implements IUseCase<GenerateMentalCalculGameUseCase.Input, GenerateMentalCalculGameUseCase.Output> {

  private final MentalCalculGame mentalCalculGame = MENTAL_CALCUL_INSTANCE_PROVIDER.provideMentalCalculGameInstance();
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
