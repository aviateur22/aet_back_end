package com.ctoutweb.aet.domain.util;

import java.util.Random;

public class NumberUtil {
  private NumberUtil() {
    throw new IllegalStateException("Le classe Number ne peut pas être instanciée");
  }

  public static int generateRandomNumberBetweenMinAndMax(int min, int max) {
    Random rand = new Random();
    return rand.nextInt((max - min) + 1) + min;
  }

  public static short generateRandomNumberBetweenMinAndMax(short min, short max) {
    Random rand = new Random();
    return (short) (rand.nextInt((max - min) + 1) + min);
  }
}
