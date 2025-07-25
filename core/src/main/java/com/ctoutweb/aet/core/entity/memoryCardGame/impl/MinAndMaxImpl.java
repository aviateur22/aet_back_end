package com.ctoutweb.aet.core.entity.memoryCardGame.impl;

import com.ctoutweb.aet.core.entity.IMinAndMax;

public  record  MinAndMaxImpl<T>(T min, T max)  implements IMinAndMax<T>  {
  @Override
  public T getMin() {
    return min;
  }

  @Override
  public T getMax() {
    return max;
  }
}
