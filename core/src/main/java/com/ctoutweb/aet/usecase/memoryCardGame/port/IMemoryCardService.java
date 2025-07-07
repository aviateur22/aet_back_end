package com.ctoutweb.aet.usecase.memoryCardGame.port;

import com.ctoutweb.aet.entity.memoryCardGame.ICardImage;

public interface IMemoryCardService {

  /**
   * Récupération de tous les paths des images qui seront mis sur les cartes
   * Model CardImage est composé de:
   * 1 image pour le devant qui sera aléatoir
   * 1 image pour le derriere qui sera identique pour toutes les cartes
   * @return CardImage[]
   */
  public ICardImage[] generateRandomCards();

}
