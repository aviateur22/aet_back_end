package com.ctoutweb.aet.core.util;

import com.ctoutweb.aet.domain.util.ArrayUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ArrayUtilTest {
  @Test
  void select_random_item_in_list() {

    /**
     * given
     */
    Integer[] list1 = Arrays.asList(1, 2, 3).toArray(Integer[]::new);
    Integer[] list2 = List.of(1).toArray(Integer[]::new);
    Integer[] list3 = List.of().toArray(Integer[]::new);

    /**
     * when
     */
    var result1 = ArrayUtil.selectOneRandomItem(list1);
    var result2 = ArrayUtil.selectOneRandomItem(list2);
    var result3 = ArrayUtil.selectOneRandomItem(list3);

    /**
     * then
     */
    Assertions.assertTrue(arrayContains(list1, result1.getItem()));
    Assertions.assertEquals(1, result2.getItem());
    Assertions.assertNull(result3);

  }

  @Test
  void shuffle_test_method() {
    /**
     * given
     */
    Integer[] list1 = Arrays.asList(1, 2, 3, 4, 5, 6).toArray(Integer[]::new);

    /**
     * when
     */
    ArrayUtil.shuffle(list1);

    /**
     * then
     */
    Assertions.assertEquals(6, list1.length);
    Assertions.assertTrue(arrayContains(list1, 1));
    Assertions.assertTrue(arrayContains(list1, 2));
    Assertions.assertTrue(arrayContains(list1, 3));
    Assertions.assertTrue(arrayContains(list1, 4));
    Assertions.assertTrue(arrayContains(list1, 5));
    Assertions.assertTrue(arrayContains(list1, 6));

  }

  @Test
  void removeItem_by_index_test_method() {
    /**
     * given
     */
    Integer[] list1 = Arrays.asList(1, 2, 3, 4, 5, 6).toArray(Integer[]::new);

    /**
     * when
     */
    Integer[] result1 = ArrayUtil.removeItemByIndex(list1, 1);
    Integer[] result2 = ArrayUtil.removeItemByIndex(list1, 7);
    Integer[] result3 = ArrayUtil.removeItemByIndex(list1, -1);
    Integer[] result4 = ArrayUtil.removeItemByIndex(list1, 5);
    Integer[] result5 = ArrayUtil.removeItemByIndex(list1, 0);


    /**
     * then
     */
    Assertions.assertEquals(5, result1.length);
    Assertions.assertEquals(6, result2.length);
    Assertions.assertEquals(6, result3.length);
    Assertions.assertEquals(5, result4.length);
    Assertions.assertEquals(5, result5.length);

    Assertions.assertEquals(1, result1[0]);
    Assertions.assertEquals(3, result1[1]);
    Assertions.assertEquals(4, result1[2]);
    Assertions.assertEquals(5, result1[3]);
    Assertions.assertEquals(6, result1[4]);

    Assertions.assertEquals(1, result4[0]);
    Assertions.assertEquals(2, result4[1]);
    Assertions.assertEquals(3, result4[2]);
    Assertions.assertEquals(4, result4[3]);
    Assertions.assertEquals(5, result4[4]);

    Assertions.assertEquals(2, result5[0]);
    Assertions.assertEquals(3, result5[1]);
    Assertions.assertEquals(4, result5[2]);
    Assertions.assertEquals(5, result5[3]);
    Assertions.assertEquals(6, result5[4]);
  }

  @Test
  void selectMultipleRandomItem_test_method() {
    /**
     * given
     */
    Integer[] list1 = Arrays.asList(1, 2, 3, 4, 5, 6).toArray(Integer[]::new);

    /**
     * when
     */
    Integer[] result1 = ArrayUtil.selectMultipleRandomItem(list1, 4);
    Integer[] result2 = ArrayUtil.selectMultipleRandomItem(list1, 6);
    Integer[] result3 = ArrayUtil.selectMultipleRandomItem(list1, -1);

    /**
     * then
     */
    Assertions.assertEquals(4, result1.length);
    Assertions.assertEquals(6, result2.length);
    Assertions.assertEquals(list1.length, result3.length);

  }

  @Test
  void selectSameItemMultipleTimeByIndex_test_method() {
    /**
     * given
     */
    Integer[] list1 = Arrays.asList(1, 2, 3, 4, 5, 6).toArray(Integer[]::new);

    /**
     * when
     */
    Integer[] result1 = ArrayUtil.selectSameItemMultipleTimeByIndex(list1, 2,4);
    Integer[] result2 = ArrayUtil.selectSameItemMultipleTimeByIndex(list1, -1, 6);

    /**
     * then
     */
    Assertions.assertEquals(4, result1.length);
    for(int item : result1) {
      Assertions.assertEquals(3, item);
    }

    Assertions.assertEquals(6, result2.length);

  }

  @Test
  void selectSameItemMultipleTimeByValue_test_method() {
    /**
     * given
     */
    Integer[] list1 = Arrays.asList(1, 2, 3, 4, 5, 6).toArray(Integer[]::new);

    /**
     * when
     */
    Integer[] result1 = ArrayUtil.selectSameItemMultipleTimeByValue(list1, 2,4);
    Integer[] result2 = ArrayUtil.selectSameItemMultipleTimeByValue(list1, -1, 6);

    /**
     * then
     */
    Assertions.assertEquals(4, result1.length);
    for(int item : result1) {
      Assertions.assertEquals(2, item);
    }

    Assertions.assertEquals(6, result2.length);
  }

  @Test
  void removeItemByItemValue_test_method() {
    /**
     * given
     */
    Integer[] list1 = Arrays.asList(1, 1, 2, 3, 4, 5, 6).toArray(Integer[]::new);

    /**
     * when
     */
    Integer[] result1 = ArrayUtil.removeItemByItemValue(list1, 1);
    Integer[] result2 = ArrayUtil.removeItemByItemValue(list1, 7);
    Integer[] result3 = ArrayUtil.removeItemByItemValue(list1, 2);


    /**
     * then
     */
    Assertions.assertEquals(6, result1.length);
    Assertions.assertEquals(7, result2.length);
    Assertions.assertEquals(6, result3.length);
  }

  void combineArrays_test_method() {
    /**
     * given
     */
    Integer[] list1 = Arrays.asList(1, 2).toArray(Integer[]::new);
    Integer[] list2 = Arrays.asList(1, 2, 3).toArray(Integer[]::new);

    /**
     * when
     */
    Integer[] result1 = ArrayUtil.combineArrays(list1, list2);

    /**
     * then
     */
    Assertions.assertEquals(5, result1.length);
  }
  public static <T> boolean arrayContains(T[] array, T value) {
    if (array == null) return false;
    for (T item : array) {
      if (item.equals(value)) {
        return true;
      }
    }
    return false;
  }
}
