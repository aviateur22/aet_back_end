package com.ctoutweb.aet.domain.entity.gameText;

import com.ctoutweb.aet.domain.entity.IMinAndMax;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.EndErrorLevel;
import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndText;

/**
 * Parametre de fin des jeux
 * dont les mauvaises réponses sont compté
 */
public interface ILoadGameEndLevelParameter {
  /**
   * Chargement des fausse reponse min et max par niveau
   * @param endLevel
   * @return
   */
  IMinAndMax loadMinAndMaxBadAnswerEndGame(EndErrorLevel endLevel);

  /**
   * Chargement du text de fin en fonction du niveau des erreurs
   * @param endLevel
   * @return
   */
  IGameEndText loadGameEndText(EndErrorLevel endLevel);
}
