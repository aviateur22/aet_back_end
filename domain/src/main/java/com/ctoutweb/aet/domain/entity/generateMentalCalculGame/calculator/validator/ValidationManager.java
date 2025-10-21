package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.validator;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.paramter.CalculParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.IOperation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.Operation;

public class ValidationManager {
    private final CalculParameter calculParameter;

    public ValidationManager(CalculParameter calculParameter) {
        this.calculParameter = calculParameter;
    }

    /**
     * Renvoie la validité de la réponse de calcul qui a été généréré
     *
     * @param operation L'opération qui a été générée
     *
     * @return boolean - Si la réponse est valide
     */
    public boolean isOperationResultValid(IOperation operation) {
        return isCalculatedResponseRespectLastDigitContraint(operation.getValidOperationResponse())
                && isCalculatedResponseRespectNegativeConstraint(operation.getValidOperationResponse());
    }

    /**
     * Vérification si le dernier digit est valide
     * @return boolean - Dernier digit valide
     */
    private boolean isCalculatedResponseRespectLastDigitContraint(double calculatedOperationResult) {
        double lastDigit = Math.abs(calculatedOperationResult % 10);
        return this.calculParameter.getLastCalculatedDigitAcceptedList().contains(lastDigit);
    }

    /**
     * Vérification si le dernier digit est valide
     * @return boolean - Dernier digit valide
     */
    private boolean isCalculatedResponseRespectNegativeConstraint(double calculatedOperationResult) {
        if(this.calculParameter.getIsNegativeCalculResultAccepted())
            return true;

        return !(calculatedOperationResult < 0);
    }
}
