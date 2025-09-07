package com.ctoutweb.aet.domain.port.generateMemoryCardGame;

import com.ctoutweb.aet.domain.entity.generateMemoryCardGame.CardFace;

public interface IGenerateMemoryCardGameGateway {

  /**
   * Récupération de tous les paths des images qui seront mis sur les cartes
   * Model CardImage est composé de:
   * 1 image pour le devant qui sera aléatoir
   * 1 image pour le derriere qui sera identique pour toutes les cartes
   * @return CardImage[]
   */
  public String[] getAvailableCardName(CardFace cardFace);

}
