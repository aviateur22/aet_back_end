package com.ctoutweb.aet.domain.entity.generateMemoryCardGame;

import com.ctoutweb.aet.domain.exception.GameParameterException;

import java.util.Arrays;

public enum ParameterState {
  RANDOM,
  FIX;
  public static ParameterState loadParameterState(String parameterState) {
    return Arrays.stream(ParameterState.values())
            .filter(parameter -> parameter.name().equalsIgnoreCase(parameterState))
            .findFirst()
            .orElseThrow(() -> new GameParameterException("Le parametre du jeu n'est pas référencé"));

  }
}
