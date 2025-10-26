package com.ctoutweb.aet.domain.provider.generateMentalCalculGame.impl;

import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.LevelType;
import com.ctoutweb.aet.domain.entity.MinAndMaxImpl;
import com.ctoutweb.aet.domain.entity.gameText.IGameTextPresentation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.MentalCalculGame;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.card.CardManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand.*;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operation.OperationManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operator.OperatorManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.proposalResponse.ProposalResponses;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.validator.ValidationManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText.GamePresentationImpl;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText.GameTextManager;
import com.ctoutweb.aet.domain.port.RandomProvider;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameGateway;
import com.ctoutweb.aet.domain.provider.generateMentalCalculGame.IMentalCalculDomainInstanceProvider;
import com.ctoutweb.aet.domain.util.IEventBus;

public class MentalCalculDomainInstanceProviderImpl implements IMentalCalculDomainInstanceProvider {

  @Override
  public MentalCalculGame provideMentalCalculGameInstance(
          IEventBus eventBus,
          CalculParameter calculParameter,
          RandomProvider randomProvider,
          IGenerateMentalCalculGameGateway gateway) {
    OperationManager operationManager = provideOperationManagerInstance(randomProvider, gateway);
    OperatorManager operatorManager = provideOperatorManagerInstance(calculParameter);
    OperandManager operandManager = provideOperandManagerInstance(calculParameter, eventBus, randomProvider);
    ValidationManager validationManager = provideValidationManagerInstance(calculParameter);
    GameTextManager gameTextManager = provideGameTextManagerInstance();
    return new MentalCalculGame(
            operationManager,
            calculParameter,
            operatorManager,
            operandManager,
            validationManager,
            gameTextManager);
  }

  @Override
  public CalculParameter provideCalculParameterInstance() {
    return null;
  }

  @Override
  public IGameTextPresentation provideGameTextPresentationInstance(String gamePresentationText, String gameTitle) {
    return new GamePresentationImpl(gamePresentationText, gameTitle);
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
  public OperandManager provideOperandManagerInstance(
          CalculParameter calculParameter,
          IEventBus eventBus,
          RandomProvider randomProvider) {
    OperandAssociatedToPriorityOperator operandAssociatedToPriorityOperator = this.provideOperandAssociatedToPriorityOperatorInstance();
    GenerateRandomOperand generateRandomOperand = this.provideOperandGeneratorFactoryInstance(calculParameter);
    ProposalResponses proposalResponse = this.provideProposalResponseInstance(randomProvider);

    return new OperandManager(
            calculParameter,
            operandAssociatedToPriorityOperator,
            generateRandomOperand,
            proposalResponse,
            eventBus);
  }

  @Override
  public ProposalResponses provideProposalResponseInstance(RandomProvider randomProvider) {
    return new ProposalResponses(randomProvider);
  }

  @Override
  public GameTextManager provideGameTextManagerInstance() {
    return new GameTextManager();
  }

  @Override
  public CardManager provideCardManagerInstance(RandomProvider randomProvider, IGenerateMentalCalculGameGateway gateway) {
    return new CardManager(gateway, randomProvider);
  }

  @Override
  public ValidationManager provideValidationManagerInstance(CalculParameter calculParameter) {
    return new ValidationManager(calculParameter);
  }

  @Override
  public OperatorManager provideOperatorManagerInstance(CalculParameter calculParameter) {
    return new OperatorManager(calculParameter);
  }

  @Override
  public OperationManager provideOperationManagerInstance(RandomProvider randomProvider, IGenerateMentalCalculGameGateway gateway) {
    CardManager cardManager = provideCardManagerInstance(randomProvider, gateway);
    return new OperationManager(cardManager);
  }
}
