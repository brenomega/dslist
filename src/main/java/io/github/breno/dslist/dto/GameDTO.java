package io.github.breno.dslist.dto;

import io.github.breno.dslist.model.Game;

public record GameDTO(
		Long id,
		String title,
		Integer year,
		String genre,
		String platforms,
		Double score,
		String imgUrl,
		String shortDescription,
		String longDescription
) {

	public GameDTO(Game entity) {
		this(
			entity.getId(),
			entity.getTitle(),
			entity.getYear(),
			entity.getGenre(),
			entity.getPlatforms(),
			entity.getScore(),
			entity.getImgUrl(),
			entity.getShortDescription(),
			entity.getLongDescription()
		);
	}
}
