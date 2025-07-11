package com.ctoutweb.aet.core.usecase.memoryCardGame.port;

import com.ctoutweb.aet.core.entity.memoryCardGame.CardFace;

public interface IPortMemoryCardService {

  /**
   * Récupération de tous les paths des images qui seront mis sur les cartes
   * Model CardImage est composé de:
   * 1 image pour le devant qui sera aléatoir
   * 1 image pour le derriere qui sera identique pour toutes les cartes
   * @return CardImage[]
   */
  public String[] getAllAvailableCardPaths(CardFace cardFace);

}
