package io.github.breno.dslist.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.breno.dslist.dto.GameDTO;
import io.github.breno.dslist.dto.GameMinDTO;
import io.github.breno.dslist.response.ApiResponse;
import io.github.breno.dslist.service.GameService;

@RestController
@RequestMapping(value = "/games")
public class GameController {
	
	private final GameService gameService;

	public GameController(GameService gameService) {
		this.gameService = gameService;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ApiResponse<GameDTO>> getGame(@PathVariable Long id) {
		GameDTO result = gameService.findById(id);
		ApiResponse<GameDTO> response = ApiResponse.ofSingle(result);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<GameMinDTO>>> getAllGames() {
		List<GameMinDTO> result = gameService.findAll();
		ApiResponse<List<GameMinDTO>> response = ApiResponse.ofList(result);
		return ResponseEntity.ok(response);
	}
}
