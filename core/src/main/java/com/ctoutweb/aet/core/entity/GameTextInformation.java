package com.ctoutweb.aet.core.entity;

public record GameTextInformation(
        String[] congratulationWords,
        String[] loosingWords,
        String gameLostText,
        String gameVictoryText,
        String presentationText
) {
}
