package io.github.breno.dslist.dto;

import io.github.breno.dslist.model.GameList;

public record GameListDTO(
		Long id,
		String name
) {
	
	public GameListDTO(GameList entity) {
		this(
			entity.getId(),
			entity.getName()
		);
	}
}
