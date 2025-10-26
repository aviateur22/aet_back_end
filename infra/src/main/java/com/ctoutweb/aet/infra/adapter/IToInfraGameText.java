package com.ctoutweb.aet.infra.adapter;

/**
 * Contrat permettant de mapper le text d'un jeu provanant du domain vers l'infra
 *
 * @param <D> Le type provenant du domain
 * @param <I> Le type cotés infra
 */
public interface IToInfraGameText<D, I> {

    /**
     * Map le text du domaine vers l'infra
     *
     * @param textFromdomainModel Le text qui provient du domain
     * @return Le text mapper pour la partie infra
     */
    I mapTextToInfra(D textFromdomainModel);
}
