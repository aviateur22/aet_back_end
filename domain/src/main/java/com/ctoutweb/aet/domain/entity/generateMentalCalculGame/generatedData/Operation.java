package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.OperatorType;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.TimeUnit;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.proposalResponse.ProposalResponses;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.ICardFaceIdent;

import java.util.ArrayList;
import java.util.List;

/**
 * Model représentant le contenu pour un calcul operation
 */
public class Operation implements IOperation {
    private int id;
    private TimeToCalculate timeToCalculate;
    private List<MentalNumber> mentalNumbers;
    private List<String> mathOperations;
    private List<ProposalResponse> proposalResponses;
    private double validOperationResponse;

    /**
     * Chargement de la liste de proposition de reponse
     *
     * @param proposalResponses Liste générée de proposition de réponse
     *
     * @see ProposalResponses
     *
     * @return Operation
     */
    public Operation loadProposalResponses(List<ProposalResponse> proposalResponses) {
        this.proposalResponses = proposalResponses;
        return this;
    }

    /**
     * Chargement de la liste des MentalNumber à partir  des operands qui ont été générées
     *
     * @param generatedOperands Liste des opérand généré
     * @param cardFaceIdent Identifiant de l'image qui sera utilisé pour le recto de la carte
     *
     * @see com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand.OperandManager
     *
     * @return Operation
     */
    public Operation loadListOfMentalNumber(List<Integer> generatedOperands, ICardFaceIdent cardFaceIdent) {
        if(generatedOperands == null)
            return this;

        this.mentalNumbers = new ArrayList<>();

        for (int i = 0; i < generatedOperands.size(); i++ ) {
            int number = generatedOperands.get(i);
            this.mentalNumbers.add(new MentalNumber(i + 1, number, cardFaceIdent));
        }

        return this;
    }

    /**
     * Chargement de la liste menthOperation à partir de la liste des opérateurs qui a été générée
     *
     * @param generatedOperators Liste des opérator généré pour cette opération
     *
     * @see com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operator.OperatorManager
     *
     * @return Operation
     */
    public Operation loadListOfOperator(List<OperatorType> generatedOperators) {
        if(generatedOperators == null || generatedOperators.isEmpty())
            return this;

        this.mathOperations = new ArrayList<>();

        this.mathOperations = generatedOperators
                .stream()
                .map(OperatorType::getOperationSign)
                .toList();

        return this;
    }

    /**
     * Attribution du résulta de calcul pour l'opération
     *
     * @param validResponse La résultat du calcul
     *
     * @see com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand.OperandManager
     *
     * @return Operation
     */
    public Operation setValidOperationResponse(double validResponse) {
        this.validOperationResponse = validResponse;
        return this;
    }

    /**
     * Attribution du numéro de l'opération
     *
     * @param operationId Numéro de l'opération
     *
     * @return Operation
     */
    public Operation setOperationIdent(int operationId) {
        this.id = operationId;
        return this;
    }

    /**
     * Gestion du temps pour le calcul de l'opration
     *
     * @param timeToCalculate Temps de calcul
     *
     * @return Operation
     */
    public Operation setTimeToCalculate(int timeToCalculate) {
        this.timeToCalculate = new TimeToCalculate(TimeUnit.SEC.name(), timeToCalculate);
        return this;
    }

    /// ///////////////

    public int getId() {
        return id;
    }

    public TimeToCalculate getTimeToCalculate() {
        return timeToCalculate;
    }

    public List<MentalNumber> getMentalNumbers() {
        return mentalNumbers;
    }

    public List<String> getMathOperations() {
        return mathOperations;
    }

    public List<ProposalResponse> getProposalResponses() {
        return proposalResponses;
    }

    public double getValidOperationResponse() {
        return validOperationResponse;
    }
}
