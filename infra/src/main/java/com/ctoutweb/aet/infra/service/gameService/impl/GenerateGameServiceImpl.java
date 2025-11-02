package com.ctoutweb.aet.infra.service.gameService.impl;

import com.ctoutweb.aet.domain.provider.CoreFactory;
import com.ctoutweb.aet.domain.provider.generateMemoryCardGame.IBusinessInstanceProvider;
import com.ctoutweb.aet.domain.provider.generateMemoryCardGame.IDomainModelInstanceProvider;
import com.ctoutweb.aet.domain.usecase.GenerateMentalCalculGameUseCase;
import com.ctoutweb.aet.infra.adapter.MemoryCardGameAdapter;
import com.ctoutweb.aet.infra.adapter.MentalCalculGameAdapter;
import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameRequestDto;
import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameResponseDto;
import com.ctoutweb.aet.infra.dto.GenerateMentalGameRequestDto;
import com.ctoutweb.aet.infra.dto.GenerateMentalGameResponseDto;
import com.ctoutweb.aet.infra.service.gameService.IGenerateGameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class GenerateGameServiceImpl implements IGenerateGameService {
  private static final Logger LOGGER = LogManager.getLogger();
  private final IDomainModelInstanceProvider coreDomainInstanceProvider = CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER;
  private final IBusinessInstanceProvider coreBusinessInstanceProvider = CoreFactory.MEMORY_CARD_BUSINESS_INSTANCE_PROVIDER;
  private final MemoryCardGameAdapter memoryCardGameAdapter;

  private final GenerateMentalCalculGameUseCase generateMentalCalculGameUseCase;
  private final MentalCalculGameAdapter mentalCalculGameAdapter;

  public GenerateGameServiceImpl(
          MemoryCardGameAdapter boundaryAdapter,
          GenerateMentalCalculGameUseCase generateMentalCalculGameUseCase,
          MentalCalculGameAdapter mentalCalculGameAdapter) {
    this.memoryCardGameAdapter = boundaryAdapter;
      this.generateMentalCalculGameUseCase = generateMentalCalculGameUseCase;
      this.mentalCalculGameAdapter = mentalCalculGameAdapter;
  }
  @Override
  public GenerateMemoryCardGameResponseDto generateMemoryCardGameData(GenerateMemoryCardGameRequestDto dto) {

    var inputBoundary = memoryCardGameAdapter.mapToCoreInputBoundary(dto);

    LOGGER.info(()->String.format("[GenerateGameServiceImpl] - [GenerateMemoryCardGameResponseDto] dto: %s", dto));
    var input = coreDomainInstanceProvider.provideInput(inputBoundary);
    var usecase = coreBusinessInstanceProvider.provideUseCase(memoryCardGameAdapter);
    var outpout = usecase.execute(input);

    var res = outpout.getMemoryGameCardData();

    var dtos =  memoryCardGameAdapter.mapToDto(res);

    LOGGER.info(()->String.format("[MemoryCardGameServiceImpl] - [GenerateMemoryCardGameResponseDto] dtoResponse: %S", dtos));
    return dtos;

  }

  @Override
  public GenerateMentalGameResponseDto generateMentalGame(GenerateMentalGameRequestDto dto) {
    LOGGER.info(()->String.format("[GenerateGameServiceImpl] - [generateMentalGame] dto: %s", dto));
    GenerateMentalCalculGameUseCase.Output output = this.generateMentalCalculGameUseCase.execute(this.mentalCalculGameAdapter.loadUseCaseInput(dto));
    return mentalCalculGameAdapter.mapToInfra(output.generateMentalCalculGameOutput());
  }
}
