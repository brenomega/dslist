package io.github.breno.dslist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.breno.dslist.dto.GameListDTO;
import io.github.breno.dslist.dto.GameMinDTO;
import io.github.breno.dslist.exception.GameListNotFoundException;
import io.github.breno.dslist.exception.InvalidGameIndexException;
import io.github.breno.dslist.model.GameList;
import io.github.breno.dslist.repository.GameListRepository;

@Service
public class GameListService {

	private final GameListRepository gameListRepository;
	private final GameService gameService;

	public GameListService(GameListRepository gameListRepository,
			GameService gameService) {
		this.gameListRepository = gameListRepository;
		this.gameService = gameService;
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findGamesByListId(Long gameListId) {
		if (!gameListRepository.existsById(gameListId)) {
	        throw new GameListNotFoundException(gameListId);
	    }
		return gameService.findByList(gameListId);
	}
	
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {
		List<GameList> result = gameListRepository.findAll();
		return result.stream()
                .map(GameListDTO::new)
                .collect(Collectors.toList());
	}
	
	@Transactional
	public void move(Long gameListId, int sourceIndex, int destinationIndex) {
		if (!gameListRepository.existsById(gameListId)) {
			throw new GameListNotFoundException(gameListId);
		}
		
		List<Long> gameIds = gameService.findIdsByListWithLock(gameListId);
		
		int size = gameIds.size();
		if (sourceIndex < 0 || sourceIndex >= size) {
			throw new InvalidGameIndexException("sourceIndex", sourceIndex, size);
		}
		if (destinationIndex < 0 || destinationIndex >= size) {
			throw new InvalidGameIndexException("destinationIndex", destinationIndex, size);
		}

		Long gameId = gameIds.remove(sourceIndex);
		gameIds.add(destinationIndex, gameId);
		
		int min = Math.min(sourceIndex, destinationIndex);
		int max = Math.max(sourceIndex, destinationIndex);
		
		for (int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(gameListId, gameIds.get(i), i);
		}
	}
}
