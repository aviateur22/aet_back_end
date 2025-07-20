package com.ctoutweb.aet.core.entity.memoryCardGame;

/**
 * Carte présent dans le jeu
 * @param cardImages ICardImage - Cartes avec le path des  images avt et arr
 * @param isCardToFind boolean - Indique si cette carte fait partie des cartes a trouver
 */
public record Card(int id, ICardImage cardImages, boolean isCardToFind) {
}
