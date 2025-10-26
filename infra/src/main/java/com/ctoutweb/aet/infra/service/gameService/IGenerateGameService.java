package com.ctoutweb.aet.infra.service.gameService;

import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameRequestDto;
import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameResponseDto;
import com.ctoutweb.aet.infra.dto.GenerateMentalGameRequestDto;
import com.ctoutweb.aet.infra.dto.GenerateMentalGameResponseDto;

public interface IGenerateGameService {
  GenerateMemoryCardGameResponseDto generateMemoryCardGameData(GenerateMemoryCardGameRequestDto dto);
  GenerateMentalGameResponseDto generateMentalGame(GenerateMentalGameRequestDto dto);
}
