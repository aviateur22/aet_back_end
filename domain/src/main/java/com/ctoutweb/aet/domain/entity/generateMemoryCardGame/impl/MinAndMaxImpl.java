package com.ctoutweb.aet.domain.entity.generateMemoryCardGame.impl;

import com.ctoutweb.aet.domain.entity.IMinAndMax;

public  record  MinAndMaxImpl<T>(T min, T max)  implements IMinAndMax<T> {
  @Override
  public T getMin() {
    return min;
  }

  @Override
  public T getMax() {
    return max;
  }
}
