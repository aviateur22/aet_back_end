package com.ctoutweb.aet.core.util;

import java.lang.reflect.Array;
import java.util.Random;

public class ArrayUtil {
  private ArrayUtil() {
    throw new IllegalStateException("Le classe ListUtil ne peut pas être instanciée");
  }
  public static class RandomItem<T> {
    private final T item;
    private final int index;

    public RandomItem(T item, int index) {
      this.item = item;
      this.index = index;
    }

    public T getItem() {
      return item;
    }

    public int getIndex() {
      return index;
    }
  }
  public static <T> RandomItem<T> selectOneRandomItem(T[] array) {
    if (array == null || array.length == 0) {
      return null;
    }

    Random random = new Random();
    int randomIndex = random.nextInt(array.length);
    return new RandomItem<>(array[randomIndex], randomIndex);
  }
  public static <T> T[] selectMultipleRandomItem(T[] initialArray, int requestedArrayLength) {
    if (initialArray == null || initialArray.length == 0) {
      return null;
    }

    if (requestedArrayLength < 0 )
      return initialArray;

    var selectMultipleItemArray = (T[]) Array.newInstance(initialArray.getClass().getComponentType(), requestedArrayLength);

    for(int i = 0; i <= requestedArrayLength - 1; i++ ){
      Random random = new Random();
      int randomIndex = random.nextInt(initialArray.length);
      T selectedElement = initialArray[randomIndex];
      shuffle(initialArray);
      selectMultipleItemArray[i] = selectedElement;
    }

    return selectMultipleItemArray;
  }
  public static <T> T[] selectSameItemMultipleTime(T[] initialArray,int indexToSelect, int requestedArrayLength) {
    if (initialArray == null || initialArray.length == 0) {
      return null;
    }

    if (requestedArrayLength < 0 || indexToSelect < 0)
      return initialArray;

    var selectSameItemArrayCopy = (T[]) Array.newInstance(initialArray.getClass().getComponentType(), requestedArrayLength);

    for(int i = 0; i <= requestedArrayLength - 1; i++ ){
      T selectedElement = initialArray[indexToSelect];
      selectSameItemArrayCopy[i] = selectedElement;
    }

    return selectSameItemArrayCopy;
  }
  public static <T> void shuffle(T[] array) {
    if (array == null || array.length <= 1) return;

    Random random = new Random();
    for (int i = array.length - 1; i > 0; i--) {
      int j = random.nextInt(i + 1);
      T temp = array[i];
      array[i] = array[j];
      array[j] = temp;
    }
  }
  public static <T> T[] combineArrays(T[] firstArray, T[] secondArray) {
    if (firstArray == null) return secondArray;
    if (secondArray == null) return firstArray;

    var combineArray = (T[]) Array.newInstance(firstArray.getClass().getComponentType(), firstArray.length + secondArray.length);

    System.arraycopy(firstArray, 0, combineArray, 0, firstArray.length);
    System.arraycopy(secondArray, 0, combineArray, firstArray.length, secondArray.length);

    return combineArray;
  }
  public static <T> T[] removeItem(T[] arrayWithElementToRemove, int indexToRemove) {
    if (arrayWithElementToRemove == null || arrayWithElementToRemove.length == 0) {
      return arrayWithElementToRemove;
    }

    if (indexToRemove < 0 || indexToRemove >= arrayWithElementToRemove.length) {
      return arrayWithElementToRemove;
    }

    T[] updatedArray = (T[]) Array.newInstance(arrayWithElementToRemove.getClass().getComponentType(), arrayWithElementToRemove.length - 1);

    int j = 0;
    for (int i = 0; i < arrayWithElementToRemove.length; i++) {
      if (i == indexToRemove) continue;
      updatedArray[j++] = arrayWithElementToRemove[i];
    }

    return updatedArray;
  }
}
