package com.ctoutweb.aet.domain.port.generateMentalCalculGame;

import com.ctoutweb.aet.domain.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.Operation;

import java.util.List;

public interface IGenerateMentalCalculGameOutput {
  IGameTextInformation getGameTextInformation();
  List<Operation> getOperations();

}
