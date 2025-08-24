package io.github.breno.dslist.exception;

public class GameListNotFoundException extends GameListException {
	private static final long serialVersionUID = 1L;

	public GameListNotFoundException(Long id) {
		super("Game list not found with ID: " + id);
	}
}
