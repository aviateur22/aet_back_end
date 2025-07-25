package com.ctoutweb.aet.infra.dto;

import com.ctoutweb.aet.infra.model.memoryCardGame.Card;
import com.ctoutweb.aet.infra.model.memoryCardGame.CardToFind;
import com.ctoutweb.aet.infra.model.memoryCardGame.IGameTextInformation;

public record GenerateMemoryCardGameResponseDto(
        IGameTextInformation gameTextInformation,
        CardToFind cardToFindInGame,
        Card[] cards,
        String gameLevel,
        int timeToObserveBeforeStart,
        int cardToFindQuantity,
        int errorQuantity,
        int timeInSecToFinish
) {}
