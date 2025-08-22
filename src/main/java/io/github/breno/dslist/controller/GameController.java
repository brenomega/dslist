package io.github.breno.dslist.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.breno.dslist.dto.GameDTO;
import io.github.breno.dslist.service.GameService;

@RestController
@RequestMapping(value = "/games")
public class GameController {
	
	private final GameService gameService;

	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	@GetMapping
	public ResponseEntity<List<GameDTO>> findAll() {
		List<GameDTO> games = gameService.findAll();
		return ResponseEntity.ok(games);
	}
}
