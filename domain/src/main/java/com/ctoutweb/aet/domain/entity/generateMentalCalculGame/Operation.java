package com.ctoutweb.aet.domain.entity.generateMentalCalculGame;

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
