package com.ctoutweb.aet.domain.entity.generateMemoryCardGame;

/**
 * Carte composée avec le path de l'image de face et arrière
 */
public interface ICardImage {
  /**
   * Nom unique de l'image de la face avant
   * @return String
   */
  String getImageFrontName();

  /**
   * Nom unique de l'image composant la face arriere.
   * Toutes les carte d'une partie ont la même face arriere
   * @return String
   */
  String getImageBackName();
}
