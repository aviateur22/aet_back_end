package com.ctoutweb.aet.infra.dto;

import com.ctoutweb.aet.infra.model.gameText.IGameTextInformation;
import com.ctoutweb.aet.infra.model.mentalCalculGame.Operation;
import com.ctoutweb.aet.infra.model.mentalCalculGame.Option;

import java.util.List;

public record GenerateMentalGameResponseDto(
        IGameTextInformation gameTextInformation,
        Option option,
        List<Operation> operations

) {
}
