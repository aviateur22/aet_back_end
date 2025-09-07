package com.ctoutweb.aet.domain.usecase.base;

public interface IUseCase<T extends IUseCase.Input, U extends IUseCase.Output> {
  U execute(T input);
  public interface Output{};
  public interface Input {};
}
