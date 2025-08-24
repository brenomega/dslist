package io.github.breno.dslist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.breno.dslist.dto.GameDTO;
import io.github.breno.dslist.dto.GameMinDTO;
import io.github.breno.dslist.exception.GameNotFoundException;
import io.github.breno.dslist.model.Game;
import io.github.breno.dslist.projection.GameMinProjection;
import io.github.breno.dslist.repository.GameRepository;

@Service
public class GameService {
	
	private final GameRepository gameRepository;

	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	@Transactional(readOnly = true)
	public GameDTO findById(Long id) {
		return gameRepository.findById(id)
				.map(GameDTO::new)
				.orElseThrow(() -> new GameNotFoundException(id));
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByList(Long gameListId) {
		List<GameMinProjection> result = gameRepository.findByList(gameListId);
		return result.stream()
				.map(GameMinDTO::new)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public List<Long> findIdsByListWithLock(Long gameListId) {
		return gameRepository.findIdsByListWithLock(gameListId);
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll() {
		List<Game> result = gameRepository.findAll();
		return result.stream()
                .map(GameMinDTO::new)
                .collect(Collectors.toList());
	}
}
