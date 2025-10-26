package com.ctoutweb.aet.domain.port;

import java.util.List;

/**
 * Contrat définissant la génération de chiffre aléatoire
 */
public interface RandomProvider {

    /**
     * Génération d'un nombre aléatoire entre 2 chiffres
     *
     * @param min Le chiffre mini inclu
     * @param max Le chiffre maxi inclu
     *
     * @param <T> Le type de chiffre à générer (INTEGER, LONG, DOUBLE...)
     *
     * @return Le chiffre selectionné de maniere aléatoire
     */
    public <T extends Number >  T generateRandomNumberBetweenMinAndMax(T min, T max);

    /**
     * Selection d'un item aléatoire d'un une liste
     *
     * @param listItems La liste ou un item doit être selectionnée de maniere aléatoire
     *
     * @return L'item selectionné
     *
     * @param <T> Le type de la liste
     */
    public <T> T selectRandomItemInList(List<T> listItems);

    /**
     * Melange de manière aléatoir une liste
     *
     * @param listToShuffle La liste a mélanger
     *
     * @return La liste qui est mélangée
     *
     * @param <T> Le type des éléménts de la liste
     */
    public <T> List<T> shuffleList(List<T> listToShuffle);
}
