package com.ctoutweb.aet.infra.util;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ListUtil {
  private ListUtil() {
    throw new IllegalStateException("Static class");
  }

  /**
   * Selection d'un item aléatoire dans une liste
   *
   * @param itemsList La liste ou on doit récupérer un item de maniere aléatoire
   *
   * @return L'item selectionné
   *
   * @param <T> Le type de la liste
   */
  public static <T> T selectOneRandomItemInList(List<T> itemsList) {
    if(itemsList.isEmpty())
      return null;

    int size = itemsList.size();

    Random random = new Random();
    int randomIndex = random.nextInt(size);
    return itemsList.get(randomIndex);
  }

  /**
   * Melange de manière aléatoir une liste
   *
   * @param itemListToShuffle La liste a méla,ger
   *
   * @return La liste qui est mélangée
   *
   * @param <T> Le type des éléménts de la liste
   */
  public static <T> List<T> shuffledList(List<T> itemListToShuffle) {
    List<T> itemListToShuffleCopy = new java.util.ArrayList<>(itemListToShuffle);
    Collections.shuffle(itemListToShuffleCopy);
    return itemListToShuffleCopy;
  }
}
