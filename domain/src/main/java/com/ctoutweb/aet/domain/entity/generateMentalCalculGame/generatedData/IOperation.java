package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData;

import java.util.List;

public interface IOperation {
    int getId();
    TimeToCalculate getTimeToCalculate();
    List<MentalNumber> getMentalNumbers();
    List<String> getMathOperations();
    List<ProposalResponse> getProposalResponses();
    double getValidOperationResponse();
}
