package com.ctoutweb.aet.infra.model.mentalCalculGame;

import java.util.List;

public record Operation(
        int id,
        TimeToCalculate timeToCalculate,
        List<MentalNumber> mentalNumbers,
        List<String> mathOperations,
        List<ProposalResponse> proposalResponses,
        double validOperationResponse) {
}
