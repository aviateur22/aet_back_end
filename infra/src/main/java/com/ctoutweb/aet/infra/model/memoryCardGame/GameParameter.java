package com.ctoutweb.aet.infra.model.memoryCardGame;

import java.util.Arrays;

public enum GameParameter {
  FIX, RANDOM;

  public static GameParameter loadGameParamter(String inputParameter) {
    return Arrays.stream(GameParameter.values())
            .filter(parameter -> parameter.name().equalsIgnoreCase(inputParameter))
            .findFirst()
            .orElseThrow();
  }
}
