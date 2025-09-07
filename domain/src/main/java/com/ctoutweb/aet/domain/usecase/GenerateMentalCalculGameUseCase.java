package com.ctoutweb.aet.domain.usecase;

import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameOutput;
import com.ctoutweb.aet.domain.usecase.base.IUseCase;

public class GenerateMentalCalculGameUseCase implements IUseCase<GenerateMentalCalculGameUseCase.Input, GenerateMentalCalculGameUseCase.Output> {
  @Override
  public Output execute(Input input) {
    return null;
  }

  public static class Input implements IUseCase.Input {

  }

  public record Output(IGenerateMentalCalculGameOutput generateMentalCalculGameOutput) implements IUseCase.Output {
  }
}
