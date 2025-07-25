package com.ctoutweb.aet.core.entity.memoryCardGame;

import com.ctoutweb.aet.core.entity.IMinAndMax;

public interface ILevelParameter {
  IMinAndMax<Integer> getCardsQuantityToFindBorn(ParameterState parameterState);
  IMinAndMax<Integer> getCardsQuantityInGameBorn(ParameterState parameterState);
  IMinAndMax<Integer> getMaxWrongReturnCardBorn(ParameterState parameterState);
  IMinAndMax<Integer> getTimeInSecToFinishBorn(ParameterState parameterState);
}
