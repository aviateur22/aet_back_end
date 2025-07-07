package com.ctoutweb.aet.entity.memoryCardGame.impl;

import com.ctoutweb.aet.entity.memoryCardGame.IBornRange;

/**
 * Class representant les bornes min et max du jeux de carte
 */
public class BornRangeImpl implements IBornRange {
  short min;
  short max;

  public BornRangeImpl(int min, int max) {
    this.min = (short) min;
    this.max = (short) max;
  }

  public short getMin() {
    return min;
  }

  public short getMax() {
    return max;
  }
}
