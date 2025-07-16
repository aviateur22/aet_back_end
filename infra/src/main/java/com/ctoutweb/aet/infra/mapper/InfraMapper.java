package com.ctoutweb.aet.infra.mapper;

import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class InfraMapper {
  protected <T,U> U map(T data, Function<T, U> mapFunction) {
    return mapFunction.apply(data);
  }

  protected <T,U> U map(T data1, T data2, BiFunction<T, T, U> mapFunction) {
    return mapFunction.apply(data1, data2);
  }
}
