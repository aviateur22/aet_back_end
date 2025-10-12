package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.operand;

import java.util.List;
import java.util.Objects;

/**
 * Données produite lors de la génération des opérand pour un calcul
 */
public class OperandInCalcul {
    private List<Integer> generatedOperands;
    private double operationCalculResult;
    private List<Double> proposalResponses;

    public List<Integer> getGeneratedOperands() {
        return generatedOperands;
    }

    public void setGeneratedOperands(List<Integer> generatedOperands) {
        this.generatedOperands = generatedOperands;
    }

    public double getOperationCalculResult() {
        return operationCalculResult;
    }

    public void setOperationCalculResult(double operationCalculResult) {
        this.operationCalculResult = operationCalculResult;
    }

    public List<Double> getProposalResponses() {
        return proposalResponses;
    }

    public void setProposalResponses(List<Double> proposalResponses) {
        this.proposalResponses = proposalResponses;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OperandInCalcul that = (OperandInCalcul) o;
        return Double.compare(operationCalculResult, that.operationCalculResult) == 0 && Objects.equals(generatedOperands, that.generatedOperands) && Objects.equals(proposalResponses, that.proposalResponses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(generatedOperands, operationCalculResult, proposalResponses);
    }

    @Override
    public String toString() {
        return "OperandInCalcul{" +
                "generatedOperands=" + generatedOperands +
                ", operationCalculResult=" + operationCalculResult +
                ", proposalResponses=" + proposalResponses +
                '}';
    }
}
