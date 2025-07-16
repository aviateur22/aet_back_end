package com.ctoutweb.aet.core.usecase.memoryCardGame.businessRules;

import com.ctoutweb.aet.core.usecase.memoryCardGame.boundary.IGenerateNewGameResponse;
public interface IMemoryCardGameRules {
  /**
   * Sélection de une image composant la face arriere d'une carte
   * @return MemoryCardGameRules
   */
  public MemoryCardGameRules selectOneBackImagePath();

  /**
   * Selection de toutes les image disponible pour la face avant
   * @return MemoryCardGameRules
   */
  public MemoryCardGameRules loadAllFrontImagePaths();

  /**
   * Génération aléatoire:
   * - Des cartes qui seront a retourner
   * - De la carte à trouver
   * @return
   */
  public MemoryCardGameRules generateCardsForGame();

  /**
   * Données du bouveau jeu
   * @return IGenerateNewGameResponse
   */
  public IGenerateNewGameResponse getData();
}
