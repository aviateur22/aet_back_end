package com.ctoutweb.aet.domain.entity.generateMentalCalculGame;

import com.ctoutweb.aet.domain.entity.JobGame;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameOutput;
import com.ctoutweb.aet.domain.usecase.GenerateMentalCalculGameUseCase;

public class MentalCalculGame extends JobGame<GenerateMentalCalculGameUseCase.Input, GenerateMentalCalculGameUseCase.Output> {

  private IGenerateMentalCalculGameOutput generateMentalCalculGameOutput;

  @Override
  public void generateGame(GenerateMentalCalculGameUseCase.Input inputData) {

  }

  @Override
  public GenerateMentalCalculGameUseCase.Output getGeneratedGameData() {
    return null;
  }
}
