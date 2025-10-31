package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand;

import java.util.List;

public record PriorityOperatorResult(boolean arePriorityOperatorCalculResultValid, List<Double> updatedInitialOperandsWithPriorityOperatorResult) {
}
