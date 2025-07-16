package com.ctoutweb.aet.core.util;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberUtilTest {

  @RepeatedTest(10)
  void generate_random_number_between_number() {

    int min1 = 9;
    int max1 = 12;

    int result = NumberUtil.generateRandomNumberBetweenMinAndMax(min1, max1);
    assertTrue(result >= min1 && result <= max1,"Le chiffre aléatoire doit être comprise entre " + min1 + " et " + max1 + " mais il est = à " + result);

    //

    int min2 = 10;
    int max2 = 10;

    int result2 = NumberUtil.generateRandomNumberBetweenMinAndMax(min2, max2);
    assertEquals(10, result2);


  }
}
