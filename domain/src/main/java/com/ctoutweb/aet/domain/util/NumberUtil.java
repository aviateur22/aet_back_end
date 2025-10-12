package com.ctoutweb.aet.domain.util;

import java.util.Random;

public class NumberUtil {
  private static final double EPSILON = 1e-9;

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

  public static double generateRandomNumberBetweenMinAndMax(double min, double max) {
    Random rand = new Random();
    return rand.nextDouble((max - min) + 1) + min;
  }

  public static boolean areEqual(double a, double b) {
    return Math.abs(a - b) < EPSILON;
  }
}
