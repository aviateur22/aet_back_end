package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operation;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.card.CardManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.Operation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.ProposalResponse;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.CardFaceIdent;

import java.util.List;

public class OperationManager {

    private final CardManager cardManager;

    public OperationManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }

    public Operation getGeneratedOperation(
            int operationIdentification,
            int timeAvailableToCalculate,
            double calculatedOperationResult,
            List<OperatorType> initialOperators,
            List<Integer> initialOperands,
            List<ProposalResponse> proposalResponses) {
        CardFaceIdent cardIdent = getCardForOperation();

        return new Operation()
                .setTimeToCalculate(timeAvailableToCalculate)
                .setOperationIdent(operationIdentification)
                .setValidOperationResponse(calculatedOperationResult)
                .loadListOfOperator(initialOperators)
                .loadListOfMentalNumber(initialOperands, cardIdent)
                .loadProposalResponses(proposalResponses);
    }

    public CardFaceIdent getCardForOperation() {
        return cardManager.selectBackCard();
    }
}
