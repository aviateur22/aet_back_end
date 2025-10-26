package com.ctoutweb.aet.infra.adapter;

import com.ctoutweb.aet.domain.usecase.base.IUseCase;

/**
 * Contrat definissant les mapping pour passer de l'infra au useCase du domain
 *
 * @param <I> Le type du model provenant de l'infra
 * @param <D> Le type InputBoundary du domain
 */
public interface IToDomain<I, D> {

    /**
     * Methode permettant de mapper un model infra vers l'InputBoundary du domain
     *
     * @param infraModel Le model a mapper
     *
     * @return Inputboundary du domain
     */
    public D mapToInputBoundary(I infraModel);

    /**
     * Methode permettant de renvoyer Input du useCase
     *
     * @param infraModel Données a envoyer dans le domain qui sera mapper en InputBoundary
     *
     * @return Input du useCase avec son InputBoundary
     */
    IUseCase.Input loadUseCaseInput(I infraModel);

}
