package com.ctoutweb.aet.entity.memoryCardGame;

import com.ctoutweb.aet.entity.memoryCardGame.impl.CardImageImpl;

public record Card(CardImageImpl cardImages, boolean isCardToFind) {
}
