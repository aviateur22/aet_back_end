package com.ctoutweb.aet.domain.port.generateMentalCalculGame;

import com.ctoutweb.aet.domain.entity.gameText.IGameTextInformation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.IOperation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.IOption;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.Operation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.Option;

import java.util.List;

public interface IGenerateMentalCalculGameOutput {
  IGameTextInformation getGameTextInformation();
  List<IOperation> getOperations();
  IOption getOption();
}
