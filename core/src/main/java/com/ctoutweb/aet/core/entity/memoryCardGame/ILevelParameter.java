package com.ctoutweb.aet.core.entity.memoryCardGame;

public interface ILevelParameter {
  IBornRange getCardsQuantityToFindBorn(ParameterState parameterState);
  IBornRange getCardsQuantityInGameBorn(ParameterState parameterState);
  IBornRange getMaxWrongReturnCardBorn(ParameterState parameterState);
  IBornRange getTimeInSecToFinishBorn(ParameterState parameterState);
}
