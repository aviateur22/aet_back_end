package com.ctoutweb.aet.infra.service.gameService.memoryCardGameService.impl;

import com.ctoutweb.aet.core.provider.CoreFactory;
import com.ctoutweb.aet.core.usecase.memoryCardGame.provider.IBusinessInstanceProvider;
import com.ctoutweb.aet.core.usecase.memoryCardGame.provider.IDomainModelInstanceProvider;
import com.ctoutweb.aet.infra.adapter.memoryCardGame.boundary.MemoryCardGameAdapter;
import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameRequestDto;
import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameResponseDto;
import com.ctoutweb.aet.infra.service.gameService.memoryCardGameService.IMemoryCardGameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MemoryCardGameServiceImpl implements IMemoryCardGameService {
  private static final Logger LOGGER = LogManager.getLogger();
  private final IDomainModelInstanceProvider coreDomainInstanceProvider = CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER;
  private final IBusinessInstanceProvider coreBusinessInstanceProvider = CoreFactory.MEMORY_CARD_BUSINESS_INSTANCE_PROVIDER;
  private final MemoryCardGameAdapter memoryCardGameAdapter;

  public MemoryCardGameServiceImpl(MemoryCardGameAdapter boundaryAdapter) {
    this.memoryCardGameAdapter = boundaryAdapter;
  }
  @Override
  public GenerateMemoryCardGameResponseDto generateMemoryCardGameData(GenerateMemoryCardGameRequestDto dto) {

    var inputBoundary = memoryCardGameAdapter.mapToCoreInputBoundary(dto);

    LOGGER.info(()->String.format("[MemoryCardGameServiceImpl] - [GenerateMemoryCardGameResponseDto] dto: %s", dto));
    var input = coreDomainInstanceProvider.provideInput(inputBoundary);
    var usecase = coreBusinessInstanceProvider.provideUseCase(memoryCardGameAdapter);
    var outpout = usecase.execute(input);

    var res = outpout.getMemoryGameCardData();

    var dtos =  memoryCardGameAdapter.mapToDto(res);

    LOGGER.info(()->String.format("[MemoryCardGameServiceImpl] - [GenerateMemoryCardGameResponseDto] dtoResponse: %S", dtos));
    return dtos;

  }
}
