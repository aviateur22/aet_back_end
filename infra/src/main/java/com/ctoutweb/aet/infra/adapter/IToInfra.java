package com.ctoutweb.aet.infra.adapter;

/**
 * Contrat definissant le passage entre le domaine et l'infra
 *
 * @param <D> Le type provenat du domain et qui sera a mapper
 * @param <I> Le type attendu coté infra apres le mapping
 */
public interface IToInfra <D, I>{
    /**
     * Mapping d'in objet du domain vers un objet de l'infra
     *
     * @param domainResult L'objet a mappoer
     *
     * @return L'objet du infra issue du mapping
     */
    I mapToInfra(D domainResult);

}
