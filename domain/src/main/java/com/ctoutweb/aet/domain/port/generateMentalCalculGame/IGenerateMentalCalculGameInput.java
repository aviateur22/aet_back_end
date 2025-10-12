package com.ctoutweb.aet.domain.port.generateMentalCalculGame;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.GameLevel;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.Operation;

import java.util.List;

public interface IGenerateMentalCalculGameInput {
  GameLevel getGameLevel();
  List<Operation> getOpertionCalculs();
}
