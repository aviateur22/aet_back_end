package com.ctoutweb.aet.util;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberUtilTest {

  @RepeatedTest(10)
  void generate_random_number_between_number() {

    int min = 9;
    int max = 12;

    int result = NumberUtil.generateRandomNumberBetweenMinAndMax(min, max);
    assertTrue(result >= min && result <= max,"Le chiffre aléatoire doit être comprise entre " + min + " et " + max + " mais il est = à " + result);

  }
}
