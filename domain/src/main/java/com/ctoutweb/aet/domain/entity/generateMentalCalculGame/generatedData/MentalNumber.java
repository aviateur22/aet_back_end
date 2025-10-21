package com.ctoutweb.aet.domain.entity.generateMentalCalculGame.generatedData;

import com.ctoutweb.aet.domain.port.generateMentalCalculGame.ICardFaceIdent;

public record MentalNumber(
        int id,
        int number,
        ICardFaceIdent cardBackImageName
) {
}
