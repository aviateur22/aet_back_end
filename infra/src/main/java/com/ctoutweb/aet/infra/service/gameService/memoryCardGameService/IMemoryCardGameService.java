package com.ctoutweb.aet.infra.service.gameService.memoryCardGameService;

import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameRequestDto;
import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameResponseDto;

public interface IMemoryCardGameService {
  GenerateMemoryCardGameResponseDto generateMemoryCardGameData(GenerateMemoryCardGameRequestDto dto);
}
