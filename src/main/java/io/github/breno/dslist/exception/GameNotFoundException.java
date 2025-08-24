package io.github.breno.dslist.exception;

public class GameNotFoundException extends GameException {
	private static final long serialVersionUID = 1L;

	public GameNotFoundException(Long id) {
		super("Game not found with ID: " + id);
	}
}
