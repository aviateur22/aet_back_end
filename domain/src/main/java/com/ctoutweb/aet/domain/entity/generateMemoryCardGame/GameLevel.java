package com.ctoutweb.aet.domain.entity.generateMemoryCardGame;

import com.ctoutweb.aet.domain.entity.LevelType;
import com.ctoutweb.aet.domain.exception.GameParameterException;
import com.ctoutweb.aet.domain.provider.CoreFactory;

import java.util.Arrays;

public enum GameLevel {
  EASY(loadLevelParameter(LevelType.EASY)),
  MEDIUM(loadLevelParameter(LevelType.MEDIUM)),
  DIFFICULT(loadLevelParameter(LevelType.DIFFICULT));

  // Quantité de cartes a trouver
  private ILevelParameter levelParameter;

  private GameLevel(ILevelParameter levelParameter) {
    this.levelParameter= levelParameter;
  }

  public ILevelParameter getLevelParameter() {
    return this.levelParameter;
  }

  private static ILevelParameter loadLevelParameter(LevelType levelType) {
    return CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER.provideLevelParameter(levelType);
  }
  public static GameLevel loadGameLevel(String levelName) {
    return Arrays.stream(GameLevel.values())
            .filter(level -> level.name().equalsIgnoreCase(levelName.trim()))
            .findFirst()
            .orElseThrow(()->new GameParameterException("Le niveau du jeu n'existe pas"));
  }
}
