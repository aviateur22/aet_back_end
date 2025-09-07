package com.ctoutweb.aet.domain.entity.gameText;

import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndParameterByLevel;

public interface IGameTextInformation {
  /**
   * Mot pouvant apparaitre a chaque bonne action (Bonne réponse)  *
   * @return
   */
  String[] getCongratulationWords();

  /**
   * Mot pouvant apparaitre a chaque mauvaise action (Réponse sur le jeu)
   * @return
   */
  String[] getLoosingWords();

  /**
   * Regroupre les données relative de fin d'un jeu.
   * Chaque ligne de ce tableau représente un nombre d'erreur cumulé par le joueur dans un jeu.
   * A chaque nombre d'erreur, un titre et message customisé pourra être renvoyé au joueur
   * @return
   */
  IGameEndParameterByLevel[] getGameEndParameterByLevels();

  /**
   * Données de présentation du jeu comportant un titre et description du jeu
   * @return
   */
  IGameTextPresentation getGamePresentation();
}
