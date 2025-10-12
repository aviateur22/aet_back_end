package com.ctoutweb.aet.domain.entity.generateMentalCalculGame;

import com.ctoutweb.aet.domain.entity.LevelType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;

import static com.ctoutweb.aet.domain.provider.CoreFactory.MENTAL_CALCUL_INSTANCE_PROVIDER;

/**
 * Niveau de jeu disponible.
 */
public enum GameLevel {

  EASY(loadCalculParameterByLevel(LevelType.EASY)),
  MEDIUM(loadCalculParameterByLevel(LevelType.MEDIUM)),
  DIFFICULT(loadCalculParameterByLevel(LevelType.DIFFICULT));

  private CalculParameter calculParameterByLevel;

  private GameLevel(CalculParameter calculParameterByLevel) {
    this.calculParameterByLevel = calculParameterByLevel;
  }

  public CalculParameter getParameter() {
    return this.calculParameterByLevel;
  }
  private static CalculParameter loadCalculParameterByLevel(LevelType level) {
    return MENTAL_CALCUL_INSTANCE_PROVIDER.loadCalculParameterByLevel(level);

  }

}
