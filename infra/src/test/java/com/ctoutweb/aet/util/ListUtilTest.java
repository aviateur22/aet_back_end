package com.ctoutweb.aet.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ListUtilTest {
  @Test
  void select_random_item_in_list() {

    /**
     * given
     */
    List<Integer> list1 = List.of(1, 2, 3);
    List<Integer> list2 = List.of(1);
    List<Integer> list3 = List.of();

    /**
     * when
     */
    var result1 = ListUtil.selectRandomItem(list1);
    var result2 = ListUtil.selectRandomItem(list2);
    var result3 = ListUtil.selectRandomItem(list3);

    /**
     * then
     */
    Assertions.assertTrue(list1.contains(result1));
    Assertions.assertEquals(1, result2);
    Assertions.assertNull(result3);

  }
}
