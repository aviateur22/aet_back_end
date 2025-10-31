package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.validator;

import com.ctoutweb.aet.domain.annotation.InjectConstructorParam;
import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.paramter.CalculParameter;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.IOperation;

public class ValidationManager {
    private final CalculParameter calculParameter;

    public ValidationManager(@InjectConstructorParam CalculParameter calculParameter) {
        this.calculParameter = calculParameter;
    }

    /**
     * Renvoie la validité de la réponse de calcul qui a été généréré
     *
     * @param operation L'opération qui a été générée
     *
     * @return boolean - Si la réponse est valide
     */
    public boolean isGeneratedOperationValid(IOperation operation) {
        return isCalculatedResponseRespectLastDigitContraint(operation.getValidOperationResponse())
                && isCalculatedResponseRespectNegativeConstraint(operation.getValidOperationResponse())
                && isCalculatedResponseRespectValueConstraint(operation.getValidOperationResponse());
    }

    /**
     * Vérification de la validité des calculs intermédiares.
     *
     * @param intermediateCalculResult La valeur du calcul intermédiaire
     *
     * @return True si les calcul intermédiaire peuvent être négatif
     */
    public boolean areIntermediateCalculValid(double intermediateCalculResult) {
        if(intermediateCalculResult <= 0 && calculParameter.areIntermediateCalculPositive)
            return false;
        return intermediateCalculResult <= calculParameter.maxIntermediateCalulResult;
    }

    /**
     * Vérification si le dernier digit est valide
     *
     * @return boolean - Dernier digit valide
     */
    private boolean isCalculatedResponseRespectLastDigitContraint(double calculatedOperationResult) {
        double lastDigit = Math.abs(calculatedOperationResult % 10);
        return this.calculParameter.getLastCalculatedDigitAcceptedList().contains(lastDigit);
    }

    /**
     * Vérification si le dernier digit est valide
     *
     * @return boolean - Dernier digit valide
     */
    private boolean isCalculatedResponseRespectNegativeConstraint(double calculatedOperationResult) {
        if(this.calculParameter.getIsNegativeCalculResultAccepted())
            return true;

        return !(calculatedOperationResult < 0);
    }

    /**
     * Vérificatio sur le résultat de l'opération est compris dans les bornes imposéés
     *
     * @param calculatedOperationResult Le resulate de l'operation à controller
     *
     * @return True si le résultat est dans les bornes imposées sinon False
     */
    private boolean isCalculatedResponseRespectValueConstraint(double calculatedOperationResult) {
        IMinAndMax<Double> minMaxAcceptedCalculResult = calculParameter.minMaxAcceptedCalculResult;

        if(minMaxAcceptedCalculResult == null)
            return true;

        return calculatedOperationResult <= minMaxAcceptedCalculResult.getMax()
                && calculatedOperationResult > minMaxAcceptedCalculResult.getMin();
    }
}
