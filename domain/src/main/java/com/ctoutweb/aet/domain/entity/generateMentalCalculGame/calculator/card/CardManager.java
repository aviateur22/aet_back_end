package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.calculator.card;

import com.ctoutweb.aet.domain.annotation.InjectConstructorParam;
import com.ctoutweb.aet.domain.exception.MentalGameException;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameGateway;
import com.ctoutweb.aet.domain.port.RandomProvider;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.CardFaceIdent;

import java.util.List;

/**
 * Récupération d'un carte
 */
public class CardManager {
    private final IGenerateMentalCalculGameGateway IGenerateMentalCalculGameGateway;
    private final RandomProvider randomProvider;

    public CardManager(
            @InjectConstructorParam IGenerateMentalCalculGameGateway IGenerateMentalCalculGameGateway,
            @InjectConstructorParam RandomProvider randomProvider) {
        this.IGenerateMentalCalculGameGateway = IGenerateMentalCalculGameGateway;
        this.randomProvider = randomProvider;
    }

    /**
     * Renvoie la carte qui a été selectionnée en random
     *
     * @return La carte selectionnée
     * @throws MentalGameException
     */
    public CardFaceIdent selectBackCard() {
        List<CardFaceIdent> backCardList = getAllAvailableBackCards();

        if(backCardList == null || backCardList.isEmpty())
            throw new MentalGameException("Il n'y a pas de carte disponible");

        return randomProvider.selectRandomItemInList(backCardList);
    }

    /**
     * Selection de toutes les cartes disponible
     *
     * @return CardManager
     */
    private  List<CardFaceIdent>  getAllAvailableBackCards() {
        return IGenerateMentalCalculGameGateway.getBackCardsAvails();
    }
}
