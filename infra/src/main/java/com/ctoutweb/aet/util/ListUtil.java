package com.ctoutweb.aet.util;

import java.util.List;
import java.util.Random;

public class ListUtil {
  private ListUtil() {
    throw new IllegalStateException("Le classe ListUtil ne peut pas être instanciée");
  }
  public static <T>  T selectRandomItem(List<T> list) {
    if(list.isEmpty())
      return null;

    Random random = new Random();
    return list.get(random.nextInt(list.size()));
  }
}
