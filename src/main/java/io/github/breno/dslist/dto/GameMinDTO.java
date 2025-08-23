package io.github.breno.dslist.dto;

import io.github.breno.dslist.model.Game;

public record GameMinDTO(
		Long id,
		String title,
		Integer year,
		String imgUrl,
		String shortDescription
) {

	public GameMinDTO(Game entity) {
		this(
			entity.getId(),
			entity.getTitle(),
			entity.getYear(),
			entity.getImgUrl(),
			entity.getShortDescription()
		);
	}
}