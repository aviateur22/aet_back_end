package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.MentalNumber;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.ProposalResponse;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.TimeToCalculate;

import java.util.List;

public record Operation(
        int id,
        TimeToCalculate timeToCalculate,
        List<MentalNumber> mentalNumbers,
        List<String> mathOperations,
        List<ProposalResponse> proposalResponses,
        int validOperationResponse
) {
}
