package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.proposalResponse;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.ProposalResponse;
import com.ctoutweb.aet.domain.port.RandomProvider;
import com.ctoutweb.aet.domain.util.NumberUtil;

import java.util.ArrayList;
import java.util.List;

public class ProposalResponses {

    private final RandomProvider randomProvider;

    public ProposalResponses(RandomProvider randomProvider) {
        this.randomProvider = randomProvider;
    }

    /**
     * Génération d'une liste de proposition de réponse
     *
     * @param proposalQuantity Nombre de réponse proposé
     * @return ProposalResponse
     */
    public List<ProposalResponse> generateProposalResponses(int proposalQuantity, double calculatedOperationResult) {
        final List<ProposalResponse> proposalResults = new ArrayList<>();

        for(int j = 0; j < proposalQuantity ; j++) {
            if(j==0) {
                proposalResults.add(new ProposalResponse(j + 1, calculatedOperationResult));
                continue;
            }

            double proposalResponse = generateSingleResponse(calculatedOperationResult);

            proposalResults.add(new ProposalResponse(j + 1, proposalResponse));
        }

        return randomProvider.shuffleList(proposalResults);
    }

    /**
     * Génération d'une proposition de réponse
     *
     * @param calculatedOperationResult Résulat de l'opération - La proposition de réponse ne doit pas
     *                                  etre identique
     *
     * @return double La réponse générée
     */
    private double generateSingleResponse(double calculatedOperationResult) {
        double proposalResponse;

        do {
            proposalResponse = NumberUtil.generateRandomNumberBetweenMinAndMax(calculatedOperationResult - 20, calculatedOperationResult + 20);
            proposalResponse = updateProposalResponseDecimal(calculatedOperationResult, proposalResponse);
        } while (NumberUtil.areEqual(proposalResponse,calculatedOperationResult));

        return proposalResponse;
    }

    /**
     * Modification du décimal en copiant le decimal du résulat de calcul sur la proposition de réponse
     *
     * @param calculatedOperationResult résulat du calcul
     * @param proposalAnswer proposition de réponse ayant le décimal a remplacer
     * @return double
     */
    private double updateProposalResponseDecimal(double calculatedOperationResult, double proposalAnswer) {
        double decimalPart = calculatedOperationResult - Math.floor(calculatedOperationResult);
        return Math.floor(proposalAnswer) + decimalPart;
    }
}
