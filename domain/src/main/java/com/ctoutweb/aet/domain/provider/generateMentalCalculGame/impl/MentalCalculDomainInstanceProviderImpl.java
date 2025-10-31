package com.ctoutweb.aet.domain.provider.generateMentalCalculGame.impl;

import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.MinAndMaxImpl;
import com.ctoutweb.aet.domain.entity.gameText.IGameTextPresentation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.card.CardManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand.*;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operation.OperationManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operator.OperatorManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.paramter.CalculParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.proposalResponse.ProposalResponseManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.validator.ValidationManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText.GamePresentationImpl;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText.GameTextManager;
import com.ctoutweb.aet.domain.injector.ConstructorInjectorContainer;
import com.ctoutweb.aet.domain.port.RandomProvider;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameGateway;
import com.ctoutweb.aet.domain.provider.generateMentalCalculGame.IMentalCalculDomainInstanceProvider;
import com.ctoutweb.aet.domain.event.IEventBus;

import java.lang.reflect.InvocationTargetException;

public class MentalCalculDomainInstanceProviderImpl implements IMentalCalculDomainInstanceProvider {

  private final ConstructorInjectorContainer container;

  public MentalCalculDomainInstanceProviderImpl(ConstructorInjectorContainer container) {
    this.container = container;
  }

  @Override
  public void loadContainer(
          IEventBus eventBus,
          CalculParameter calculParameter,
          RandomProvider randomProvider,
          IGenerateMentalCalculGameGateway gateway) throws InvocationTargetException, InstantiationException, IllegalAccessException {
    container.register(IEventBus.class, eventBus);
    container.register(CalculParameter.class, calculParameter);
    container.register(RandomProvider.class, randomProvider);
    container.register(IGenerateMentalCalculGameGateway.class, gateway);

    CardManager cardManager = container.instanciate(CardManager.class);
    container.register(CardManager.class, cardManager);

    ValidationManager validationManager = container.instanciate(ValidationManager.class);
    container.register(ValidationManager.class, validationManager);

    OperationManager operationManager = container.instanciate(OperationManager.class);
    container.register(OperationManager.class, operationManager);

    OperatorManager operatorManager = container.instanciate(OperatorManager.class);
    container.register(OperatorManager.class, operatorManager);

    OperandAssociatedToPriorityOperator operandAssociatedToPriorityOperator = container.instanciate(OperandAssociatedToPriorityOperator.class);
    container.register(OperandAssociatedToPriorityOperator.class, operandAssociatedToPriorityOperator);

    GenerateRandomOperand generateRandomOperand = container.instanciate(GenerateRandomOperand.class);
    container.register(GenerateRandomOperand.class, generateRandomOperand);

    ProposalResponseManager proposalResponseManager = container.instanciate(ProposalResponseManager.class);
    container.register(ProposalResponseManager.class, proposalResponseManager);

    OperandManager operandManager = container.instanciate(OperandManager.class);
    container.register(OperandManager.class, operandManager);

    GameTextManager gameTextManager = container.instanciate(GameTextManager.class);
    container.register(GameTextManager.class, gameTextManager);

  }

  @Override
  public IGameTextPresentation provideGameTextPresentationInstance(String gamePresentationText, String gameTitle) {
    return new GamePresentationImpl(gamePresentationText, gameTitle);
  }

  @Override
  public <T> IMinAndMax<T> provideMinAndMaxInstance(T min, T max) {
    return new MinAndMaxImpl<>(min, max);
  }
  }
