package com.ctoutweb.aet.domain.entity.generateMentalCalculGame;

import com.ctoutweb.aet.domain.exception.GameParameterException;

import java.util.Arrays;

/**
 * Niveau de jeu disponible.
 */
public enum GameLevel {
  EASY,
  MEDIUM,
  DIFFICULT;

  /**
   * Chargement du niveau requis à partir d'un String
   *
   * @param inputRequestLevel Le niveau requis en format text
   *
   * @return Le niveau de jeu
   */
  public static GameLevel loadGameLevel(String inputRequestLevel) {

    for(GameLevel gameLevel: GameLevel.values()) {
      if( inputRequestLevel.equalsIgnoreCase(gameLevel.name().toLowerCase()))
        return gameLevel;
    }
    throw new GameParameterException("Le niveau de jeu requis n'est pas valide: " + inputRequestLevel);
  }
}
