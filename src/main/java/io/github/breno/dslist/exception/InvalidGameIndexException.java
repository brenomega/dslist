package io.github.breno.dslist.exception;

public class InvalidGameIndexException extends GameListException {
	private static final long serialVersionUID = 1L;

	public InvalidGameIndexException(String indexName, int indexValue, int size) {
		super(String.format(
			"Invalid index '%s': %d. Must be between 0 and %d.", 
			indexName, indexValue, size - 1
			));
	}
}
