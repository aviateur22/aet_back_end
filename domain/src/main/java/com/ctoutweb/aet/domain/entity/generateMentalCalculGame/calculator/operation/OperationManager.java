package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operation;

import com.ctoutweb.aet.domain.annotation.InjectConstructorParam;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.card.CardManager;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.IOperation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.Operation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.ProposalResponse;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.CardFaceIdent;

import java.util.List;

public class OperationManager {

    private static final double DEFAULT_OPERATION_RESULT = 10.0;
    private static final List<OperatorType> DEFAULT_OPERATOR_LIST = List.of(OperatorType.ADDITION);
    private static final List<Integer> DEFAULT_OPERAND_LIST = List.of(6, 4);
    private static final List<ProposalResponse> DEFAULT_PROPOSAL_RESPONSE_LIST = List.of(
            new ProposalResponse(1, 5),
            new ProposalResponse(2, 12),
            new ProposalResponse(3, 10),
            new ProposalResponse(4, 19)

    );

    private final CardManager cardManager;

    public OperationManager(@InjectConstructorParam CardManager cardManager) {
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

    public IOperation generateDefaultOperation(int operationIdentification, int timeAvailableToCalculate) {
        CardFaceIdent cardIdent = getCardForOperation();
        return new Operation()
                .setTimeToCalculate(timeAvailableToCalculate)
                .setOperationIdent(operationIdentification)
                .setValidOperationResponse(DEFAULT_OPERATION_RESULT)
                .loadListOfOperator(DEFAULT_OPERATOR_LIST)
                .loadListOfMentalNumber(DEFAULT_OPERAND_LIST, cardIdent)
                .loadProposalResponses(DEFAULT_PROPOSAL_RESPONSE_LIST);
        }
}

