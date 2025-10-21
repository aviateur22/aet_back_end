package com.ctoutweb.aet.domain.provider.generateMentalCalculGame.impl;

import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.LevelType;
import com.ctoutweb.aet.domain.entity.MinAndMaxImpl;
import com.ctoutweb.aet.domain.entity.gameText.IGameTextPresentation;
import com.ctoutweb.aet.domain.entity.gameText.ILoadGameEndLevelParameter;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.EndErrorLevel;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndText;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.MentalCalculGame;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.CalculGenerator;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand.*;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operator.OperatorManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.proposalResponse.ProposalResponses;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.validator.ValidationManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText.GamePresentationImpl;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText.GameTextManager;
import com.ctoutweb.aet.domain.provider.common.ICommonInstanceProvider;
import com.ctoutweb.aet.domain.provider.generateMentalCalculGame.IMentalCalculDomainInstanceProvider;
import com.ctoutweb.aet.domain.provider.helper.LoadCalculHelper;
import com.ctoutweb.aet.domain.util.IEventBus;

import static com.ctoutweb.aet.domain.provider.CoreFactory.COMMON_INSTANCE_PROVIDER;


public class MentalCalculDomainInstanceProviderImpl implements IMentalCalculDomainInstanceProvider {
  private final LoadCalculHelper loadCalculHelper = new LoadCalculHelper();
   private final ICommonInstanceProvider commonInstanceProvider = COMMON_INSTANCE_PROVIDER;

  @Override
  public MentalCalculGame provideMentalCalculGameInstance(IEventBus eventBus) {
    return new MentalCalculGame(eventBus);
  }

  @Override
  public CalculParameter loadCalculParameterByLevel(LevelType levelType) {

    CalculParameter calculParameter = switch (levelType) {
      case EASY -> loadCalculHelper.loadEasyLevelCalculParameter();
      case MEDIUM -> loadCalculHelper.loadMediumLevelCalculParameter();
      case DIFFICULT -> loadCalculHelper.loadDifficultLevelCalculParameter();
    };

    return calculParameter;
  }

  @Override
  public IGameTextPresentation provideGameTextPresentationInstance(String gamePresentationText, String gameTitle) {
    return new GamePresentationImpl(gamePresentationText, gameTitle);
  }

  @Override
  public IGameEndText provideEndTextIntance(String endTitle, String endGameText) {
    return null;
  }

  @Override
  public ILoadGameEndLevelParameter provideLoadGameEndLevelParameterInstance(int minErrorLevel, int maxErrorLevel, EndErrorLevel resultLevel, IGameEndText gameEndText) {
    return null;
  }

  @Override
  public <T> IMinAndMax<T> provideMinAndMaxInstance(T min, T max) {
    return new MinAndMaxImpl<>(min, max);
  }

  @Override
  public OperandAssociatedToPriorityOperator provideOperandAssociatedToPriorityOperatorInstance() {
    return new OperandAssociatedToPriorityOperator();
  }

  @Override
  public GenerateRandomOperand provideOperandGeneratorFactoryInstance(CalculParameter calculParameter) {
    return new GenerateRandomOperand(calculParameter);
  }

  @Override
  public OperandManager provideOperandManagerInstance(CalculParameter calculParameter, IEventBus eventBus) {
    OperandAssociatedToPriorityOperator operandAssociatedToPriorityOperator = this.provideOperandAssociatedToPriorityOperatorInstance();
    GenerateRandomOperand generateRandomOperand = this.provideOperandGeneratorFactoryInstance(calculParameter);
    ProposalResponses proposalResponse = this.provideProposalResponseInstance();

    return new OperandManager(
            operandAssociatedToPriorityOperator,
            generateRandomOperand,
            proposalResponse,
            eventBus);
  }

  @Override
  public ProposalResponses provideProposalResponseInstance() {
    return new ProposalResponses();
  }

  @Override
  public GameTextManager provideGameTextManagerInstance() {
    return new GameTextManager();
  }


  @Override
  public CalculGenerator provideCalculGeneratorInstance(CalculParameter calculParameter, IEventBus eventBus) {
    OperatorManager operatorManager = provideOperatorManagerInstance(calculParameter);
    OperandManager calculateOperandResult = provideOperandManagerInstance(calculParameter, eventBus);
    ValidationManager validationManager = provideValidationManagerInstance(calculParameter);
    GameTextManager gameTextManager = provideGameTextManagerInstance();
    return new CalculGenerator(calculParameter, operatorManager, calculateOperandResult, validationManager, gameTextManager);
  }

  @Override
  public ValidationManager provideValidationManagerInstance(CalculParameter calculParameter) {
    return new ValidationManager(calculParameter);
  }

  @Override
  public OperatorManager provideOperatorManagerInstance(CalculParameter calculParameter) {
    return new OperatorManager(calculParameter);
  }
}
