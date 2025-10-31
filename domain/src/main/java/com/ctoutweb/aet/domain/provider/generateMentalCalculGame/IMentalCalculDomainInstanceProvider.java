package com.ctoutweb.aet.domain.provider.generateMentalCalculGame;

import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.gameText.IGameTextPresentation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.paramter.CalculParameter;
import com.ctoutweb.aet.domain.port.RandomProvider;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameGateway;
import com.ctoutweb.aet.domain.event.IEventBus;

import java.lang.reflect.InvocationTargetException;

public interface IMentalCalculDomainInstanceProvider {

  /**
   * Chargement des instances dans le container du domaine
   */

  void loadContainer(
          IEventBus eventBus,
          CalculParameter calculParameter,
          RandomProvider randomProvider,
          IGenerateMentalCalculGameGateway gateway
  ) throws InvocationTargetException, InstantiationException, IllegalAccessException;

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
}
