package io.github.breno.dslist.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.breno.dslist.dto.GameListDTO;
import io.github.breno.dslist.dto.GameMinDTO;
import io.github.breno.dslist.dto.ReplacementDTO;
import io.github.breno.dslist.response.ApiResponse;
import io.github.breno.dslist.service.GameListService;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

	private final GameListService gameListService;

	public GameListController(GameListService gameListService) {
		this.gameListService = gameListService;
	}
	
	@GetMapping(value = "/{gameListId}/games")
	public ResponseEntity<ApiResponse<List<GameMinDTO>>> getGamesInList(@PathVariable Long gameListId) {
		List<GameMinDTO> result = gameListService.findGamesByListId(gameListId);
		ApiResponse<List<GameMinDTO>> response = ApiResponse.ofList(result);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<GameListDTO>>> getAllLists() {
		List<GameListDTO> result = gameListService.findAll();
		ApiResponse<List<GameListDTO>> response = ApiResponse.ofList(result);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/{gameListId}/replacement")
	public ResponseEntity<ApiResponse<Void>> moveGamesInList(@PathVariable Long gameListId, @RequestBody ReplacementDTO body) {
		gameListService.move(gameListId, body.sourceIndex(), body.destinationIndex());
		return ResponseEntity.ok(ApiResponse.successMessage("Move completed."));
	}
}
