package com.ctoutweb.aet.infra.model.gameText;

public record GameEndParameterByLevel(int minError, int maxError, EndGameErrorLevel endResultLevel, EndGameText endGameText) {
}
