package com.ctoutweb.aet.domain.provider.generateMentalCalculGame;

import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.gameText.IGameTextPresentation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.MentalCalculGame;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand.*;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operator.OperatorManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.proposalResponse.ProposalResponses;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.validator.ValidationManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.gameText.GameTextManager;
import com.ctoutweb.aet.domain.util.IEventBus;

public interface IMentalCalculDomainInstanceProvider {
  /**
   *
   * @return MentalCalculGame
   */
  MentalCalculGame provideMentalCalculGameInstance(IEventBus eventBus, CalculParameter calculParameter);

  /**
   * Chargement des parametre du calcul mental suivant le LevelType
   *
   * @return CalculParameter
   */
  CalculParameter provideCalculParameterInstance();

  /**
   * Text de presentation
   *
   * @param gamePresentationText - Text de presentation
   * @param gameTitle - Tite du jeu
   *
   * @return IGameTextPresentation
   */
  IGameTextPresentation provideGameTextPresentationInstance(String gamePresentationText, String gameTitle);

  /**
   *  Renvoie une implementation de IMinAndMax
   *
   * @param min La valeur minimum attendue
   * @param max La valeur maximal attendu
   *
   * @return Instance implementant IMinAndMax
   *
   * @param <T> Le type de min et max IMinAndMax
   */
  <T> IMinAndMax<T> provideMinAndMaxInstance(T min, T max);

  /**
   *
   * @return OperandAssociatedToPriorityOperator
   */
  OperandAssociatedToPriorityOperator provideOperandAssociatedToPriorityOperatorInstance();

  OperatorManager provideOperatorManagerInstance(CalculParameter calculParameter);

  GenerateRandomOperand provideOperandGeneratorFactoryInstance(CalculParameter calculParameter);

  OperandManager provideOperandManagerInstance(CalculParameter calculParameter, IEventBus eventBus);

  ProposalResponses provideProposalResponseInstance();

  GameTextManager provideGameTextManagerInstance();

  /**
   * Renvoie une instance de ValidationManager
   *
   * @param calculParameter Les parametres du jeu de calcul
   *
   * @return ValidationManager
   */
  ValidationManager provideValidationManagerInstance(CalculParameter calculParameter);
}
