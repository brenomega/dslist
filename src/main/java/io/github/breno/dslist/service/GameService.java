package io.github.breno.dslist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.breno.dslist.dto.GameDTO;
import io.github.breno.dslist.model.Game;
import io.github.breno.dslist.repository.GameRepository;

@Service
public class GameService {
	
	private final GameRepository gameRepository;

	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	public List<GameDTO> findAll() {
		List<Game> result = gameRepository.findAll();
		return result.stream()
                .map(GameDTO::new)
                .collect(Collectors.toList());
	}
}
