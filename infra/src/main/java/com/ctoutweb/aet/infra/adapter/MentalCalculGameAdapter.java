package com.ctoutweb.aet.infra.adapter;

import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.CardFaceIdent;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.IOperation;
import com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.IOption;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameGateway;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameInput;
import com.ctoutweb.aet.domain.port.generateMentalCalculGame.IGenerateMentalCalculGameOutput;
import com.ctoutweb.aet.domain.usecase.GenerateMentalCalculGameUseCase;
import com.ctoutweb.aet.infra.dto.GenerateMentalGameRequestDto;
import com.ctoutweb.aet.infra.dto.GenerateMentalGameResponseDto;
import com.ctoutweb.aet.infra.model.gameText.IGameTextInformation;
import com.ctoutweb.aet.infra.model.mentalCalculGame.*;
import com.ctoutweb.aet.infra.repository.IMentalCalculGameRepository;
import com.ctoutweb.aet.infra.repository.entity.MentalCalculGameEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentalCalculGameAdapter implements
        IToDomain <GenerateMentalGameRequestDto, IGenerateMentalCalculGameInput>,
        IToInfra<IGenerateMentalCalculGameOutput, GenerateMentalGameResponseDto>,
        IGenerateMentalCalculGameGateway {

    private final IMentalCalculGameRepository repository;
    private final IToInfraGameText<com.ctoutweb.aet.domain.entity.gameText.IGameTextInformation, com.ctoutweb.aet.infra.model.gameText.IGameTextInformation> toInfraGameText;

    public MentalCalculGameAdapter(
            IMentalCalculGameRepository repository,
            IToInfraGameText<com.ctoutweb.aet.domain.entity.gameText.IGameTextInformation, IGameTextInformation> toInfraGameText) {
        this.repository = repository;
        this.toInfraGameText = toInfraGameText;
    }

    @Override
    public IGenerateMentalCalculGameInput mapToInputBoundary(GenerateMentalGameRequestDto infraModel) {
        return new IGenerateMentalCalculGameInput() {
            @Override
            public String getGameLevel() {
                return infraModel.gameLevel();
            }
        };
    }

    @Override
    public GenerateMentalCalculGameUseCase.Input loadUseCaseInput(GenerateMentalGameRequestDto generateMentalGameDto) {
        return new GenerateMentalCalculGameUseCase.Input(mapToInputBoundary(generateMentalGameDto));
    }

    @Override
    public List<CardFaceIdent> getBackCardsAvails() {
        var mentalCalculGameCards =  this.repository.findAll();
        return mentalCalculGameCards.stream().map(this::mapToCardFaceIdent).toList();
    }

    CardFaceIdent mapToCardFaceIdent(MentalCalculGameEntity mentalCalculGame) {
        return new CardFaceIdent() {
            @Override
            public String getCardIdent() {
                return mentalCalculGame.getImage().getRandomName();
            }
        };
    }

    @Override
    public GenerateMentalGameResponseDto mapToInfra(IGenerateMentalCalculGameOutput domainResult) {
        return new GenerateMentalGameResponseDto(
                toInfraGameText.mapTextToInfra(domainResult.getGameTextInformation()),
                mapToOption(domainResult.getOption()),
                domainResult.getOperations().stream().map(this::mapToOperation).toList()
        );
    }

    private Operation mapToOperation(IOperation operation) {
        return new Operation(
                operation.getId(),
                mapToTimeToCalculate(operation.getTimeToCalculate()),
                operation.getMentalNumbers().stream().map(this::mapToMentalNumber).toList(),
                operation.getMathOperations(),
                operation.getProposalResponses().stream().map(this::mapToProposalResponse).toList(),
                operation.getValidOperationResponse()
        );
    }

    private Option mapToOption(IOption option) {
        return new Option(
                option.getGameLevel(),
                option.getIsMultipleChoiceVisible()
        );
    }

    private MentalNumber mapToMentalNumber(com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.MentalNumber mentalNumber) {
        return new MentalNumber(
                mentalNumber.id(),
                mentalNumber.number(),
                mentalNumber.cardBackImageName()
        );
    }

    private TimeToCalculate mapToTimeToCalculate(com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.TimeToCalculate timeToCalculate) {
        return new TimeToCalculate(
                timeToCalculate.unit(),
                timeToCalculate.time()
        );
    }

    private ProposalResponse mapToProposalResponse(com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData.ProposalResponse proposalResponse) {
        return new ProposalResponse(
                proposalResponse.id(),
                proposalResponse.proposalResponse()
        );
    }

}
