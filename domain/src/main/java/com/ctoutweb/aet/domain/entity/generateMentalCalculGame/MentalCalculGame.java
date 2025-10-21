package com.ctoutweb.aet.domain.entity.generateMentalCalculGame;

import com.ctoutweb.aet.domain.entity.JobGame;
import com.ctoutweb.aet.domain.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.CalculGenerator;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.IOperation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.IOption;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.TimeToCalculate;
import com.ctoutweb.aet.domain.injector.MethodInjectorContainer;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.ICardFaceIdent;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameOutput;
import com.ctoutweb.aet.domain.usecase.GenerateMentalCalculGameUseCase;
import com.ctoutweb.aet.domain.util.IEventBus;

import java.util.List;

import static com.ctoutweb.aet.domain.provider.CoreFactory.MENTAL_CALCUL_INSTANCE_PROVIDER;

public class MentalCalculGame extends JobGame<GenerateMentalCalculGameUseCase.Input, GenerateMentalCalculGameUseCase.Output> {

  private CalculParameter calculParameter;
  private final IEventBus eventBus;
  private CalculGenerator calculGenerator;

  public MentalCalculGame(IEventBus eventBus) {
        this.eventBus = eventBus;
  }

  @Override
  public MentalCalculGame generateGame(GenerateMentalCalculGameUseCase.Input inputData) {


    GameLevel level =  inputData.generateMentalCalculGameInput().getGameLevel();
    this.calculParameter = level.getParameter();

    int timeToCalculate = calculParameter.getTimeAvailableToCalculate();

    MethodInjectorContainer
            .getInstance()
            .register(ICardFaceIdent.class, inputData.generateMentalCalculGameInput().getCardFaceId());

    MethodInjectorContainer
            .getInstance()
            .register(TimeToCalculate.class, new TimeToCalculate(TimeUnit.SEC.name(), timeToCalculate));

    calculGenerator = MENTAL_CALCUL_INSTANCE_PROVIDER.provideCalculGeneratorInstance(calculParameter, eventBus);
    calculGenerator.generateCalculGame();

    return this;
  }

  @Override
  public GenerateMentalCalculGameUseCase.Output getGeneratedGameData() {
    IGenerateMentalCalculGameOutput output = new IGenerateMentalCalculGameOutput() {
      @Override
      public IGameTextInformation getGameTextInformation() {
        return calculGenerator.getGameTextInformation();
      }

      @Override
      public List<IOperation> getOperations() {
        return calculGenerator.getOperations();
      }

      @Override
      public IOption getOption() {
        return calculGenerator.getGameOption();
      }
    };
    return new GenerateMentalCalculGameUseCase.Output(output);
  }


}
