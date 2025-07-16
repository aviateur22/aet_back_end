package com.ctoutweb.aet.infra.model.memoryCardGame;

import java.util.Arrays;

public enum GameLevel {
  EASY, MEDIUM, DIFFICULT;

  public static GameLevel loadGameLevel(String inputGameLevel) {
    return Arrays.stream(GameLevel.values())
            .filter(level -> level.name().equalsIgnoreCase(inputGameLevel))
            .findFirst()
            .orElseThrow();
  }
}
