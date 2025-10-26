package com.ctoutweb.aet.domain.port.generateMentalCalculGame;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.CardFaceIdent;

import java.util.List;

public interface IGenerateMentalCalculGameGateway {

    /**
     * Renvoie une liste des cartes disponible
     *
     * @return La liste des cartes
     */
    List<CardFaceIdent> getBackCardsAvails();
}
