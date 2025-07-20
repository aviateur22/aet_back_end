package com.ctoutweb.aet.infra.controller;

import com.ctoutweb.aet.infra.dto.GenerateMemoryCardGameResponseDto;
import com.ctoutweb.aet.infra.model.memoryCardGame.GameLevel;
import com.ctoutweb.aet.infra.model.memoryCardGame.GameParameter;
import com.ctoutweb.aet.infra.service.gameService.memoryCardGameService.IMemoryCardGameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ctoutweb.aet.infra.provider.InfraFactory.INFRA_MEMORY_CARD_INSTANCE_PROVIDER;

@RestController
@RequestMapping("${api.version.path}/games")
public class GameController {
  private static final Logger LOGGER = LogManager.getLogger();
  private final IMemoryCardGameService memoryCardGameService;

  public GameController(IMemoryCardGameService memoryCardGameService) {
    this.memoryCardGameService = memoryCardGameService;
  }

  @GetMapping("/card-game/level/{gameLevel}/generate-random-memory-card-game")
  public ResponseEntity<GenerateMemoryCardGameResponseDto> generateRandomMemoryCardGame(@PathVariable String gameLevel) {
    final GameParameter gameParameter = GameParameter.RANDOM;
    var dto = INFRA_MEMORY_CARD_INSTANCE_PROVIDER.provideMemoryCardGameRequestDto(GameLevel.loadGameLevel(gameLevel), gameParameter);
    var res = memoryCardGameService.generateMemoryCardGameData(dto);
    LOGGER.info(()->res);
    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  @GetMapping("card-game/level/{gameLevel}/generate-fix-memory-card-game")
  public ResponseEntity<GenerateMemoryCardGameResponseDto> generateFixMemoryCardGame(@PathVariable String gameLevel) {
    final GameParameter gameParameter = GameParameter.FIX;
    var dto = INFRA_MEMORY_CARD_INSTANCE_PROVIDER.provideMemoryCardGameRequestDto(GameLevel.loadGameLevel(gameLevel), gameParameter);
    return new ResponseEntity<>(memoryCardGameService.generateMemoryCardGameData(dto), HttpStatus.OK);
  }
}
