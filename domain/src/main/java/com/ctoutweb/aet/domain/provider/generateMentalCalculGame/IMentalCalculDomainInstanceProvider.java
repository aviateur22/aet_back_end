package com.ctoutweb.aet.domain.provider.generateMentalCalculGame;

import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.LevelType;
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
import com.ctoutweb.aet.domain.util.IEventBus;

public interface IMentalCalculDomainInstanceProvider {
  /**
   *
   * @return MentalCalculGame
   */
  MentalCalculGame provideMentalCalculGameInstance();

  /**
   * Chargement des parametre du calcul mental suivant le LevelType
   * @param levelType - LeveType
   * @return CalculParameter
   */
  CalculParameter loadCalculParameterByLevel(LevelType levelType);

  /**
   * Text de presentation
   * @param gamePresentationText - Text de presentation
   * @param gameTitle - Tite du jeu
   * @return IGameTextPresentation
   */
  IGameTextPresentation provideGameTextPresentationInstance(String gamePresentationText, String gameTitle);

  /**
   * Text de fin de jeu
   * @param endTitle Titre de fin
   * @param endGameText - Text de fin
   * @return IGameEndText
   */
  IGameEndText provideEndTextIntance(String endTitle, String endGameText);

  /**
   *
   * @param minErrorLevel - Nombre d'erreur mini
   * @param maxErrorLevel - Nombre d'erreur max
   * @param resultLevel - Resultat en niveau d'erreur (Bien , moyen , pas bien...)
   * @param gameEndText - Text de fin (voir provideEndTextIntance)
   *
   * @return ILoadGameEndLevelParameter
   */
  ILoadGameEndLevelParameter provideLoadGameEndLevelParameterInstance(
          int minErrorLevel,
          int maxErrorLevel,
          EndErrorLevel resultLevel,
          IGameEndText gameEndText);

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

  OperandInCalcul provideOperandInCalculInstance();

  CalculGenerator provideCalculGeneratorInstance(CalculParameter calculParameter, IEventBus eventBus);

  OperationGeneratedInformation provideOperationGeneratedInformationInstance();
}
