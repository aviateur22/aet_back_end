package com.ctoutweb.aet.usecase;

public interface IUseCase<T extends IUseCase.Input, U extends IUseCase.Output> {
  U execute(T input);
  public interface Output{};
  public interface Input {};
}
