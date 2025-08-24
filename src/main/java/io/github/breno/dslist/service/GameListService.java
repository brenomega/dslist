package io.github.breno.dslist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.breno.dslist.dto.GameListDTO;
import io.github.breno.dslist.dto.GameMinDTO;
import io.github.breno.dslist.exception.GameListNotFoundException;
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
		List<GameMinDTO> list = gameService.findByList(gameListId);
		
		GameMinDTO obj = list.remove(sourceIndex);
		list.add(destinationIndex, obj);
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex > destinationIndex ? sourceIndex : destinationIndex;
		
		for (int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(gameListId, list.get(i).id(), i);
		}
	}
}
