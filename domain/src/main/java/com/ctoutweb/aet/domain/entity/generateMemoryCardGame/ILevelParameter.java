package com.ctoutweb.aet.domain.entity.generateMemoryCardGame;

import com.ctoutweb.aet.domain.entity.IMinAndMax;

public interface ILevelParameter {
  IMinAndMax<Integer> getCardsQuantityToFindBorn(ParameterState parameterState);
  IMinAndMax<Integer> getCardsQuantityInGameBorn(ParameterState parameterState);
  IMinAndMax<Integer> getMaxWrongReturnCardBorn(ParameterState parameterState);
  IMinAndMax<Integer> getTimeInSecToFinishBorn(ParameterState parameterState);
}
