package io.github.breno.dslist.exception;

public abstract class GameListException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public GameListException(String message) {
		super(message);
	}
}
