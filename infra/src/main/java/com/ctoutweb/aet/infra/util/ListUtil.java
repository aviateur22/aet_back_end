package com.ctoutweb.aet.infra.util;

import java.util.List;
import java.util.Random;

public class ListUtil {
  private ListUtil() {
    throw new IllegalStateException("Static class");
  }

  public static <T> T selectOneItem(List<T> listToFilter) {
    if(listToFilter.isEmpty())
      return null;

    int size = listToFilter.size();

    Random random = new Random();
    int randomIndex = random.nextInt(size);
    return listToFilter.get(randomIndex);
  }
}
