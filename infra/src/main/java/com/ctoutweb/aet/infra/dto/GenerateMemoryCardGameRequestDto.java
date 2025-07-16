package com.ctoutweb.aet.infra.dto;

import com.ctoutweb.aet.infra.model.memoryCardGame.GameLevel;
import com.ctoutweb.aet.infra.model.memoryCardGame.GameParameter;

public record GenerateMemoryCardGameRequestDto(GameLevel gameLevel, GameParameter parameterState) {

}
