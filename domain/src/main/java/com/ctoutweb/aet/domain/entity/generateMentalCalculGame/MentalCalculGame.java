package com.ctoutweb.aet.domain.entity.generateMentalCalculGame;

import com.ctoutweb.aet.domain.entity.JobGame;
import com.ctoutweb.aet.domain.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.Operation;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameOutput;
import com.ctoutweb.aet.domain.usecase.GenerateMentalCalculGameUseCase;

import java.util.List;

public class MentalCalculGame extends JobGame<GenerateMentalCalculGameUseCase.Input, GenerateMentalCalculGameUseCase.Output> {

  private IGenerateMentalCalculGameOutput generateMentalCalculGameOutput;
  private CalculParameter calculParameter;
  @Override
  public MentalCalculGame generateGame(GenerateMentalCalculGameUseCase.Input inputData) {
    GameLevel level =  inputData.generateMentalCalculGameInput().getGameLevel();
    this.calculParameter = level.getParameter();

    calculParameter.getAritmeticOperatorNumberForCalcul();

    return this;

  }

  @Override
  public GenerateMentalCalculGameUseCase.Output getGeneratedGameData() {
    IGenerateMentalCalculGameOutput output = new IGenerateMentalCalculGameOutput() {
      @Override
      public IGameTextInformation getGameTextInformation() {
        return null;
      }

      @Override
      public List<Operation> getOperations() {
        return calculParameter.getGeneratedCalculOperations();
      }
    };
    return new GenerateMentalCalculGameUseCase.Output(output);
  }


}
