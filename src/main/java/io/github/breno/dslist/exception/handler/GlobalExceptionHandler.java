package io.github.breno.dslist.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.github.breno.dslist.exception.GameException;
import io.github.breno.dslist.exception.GameListException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(GameException.class)
	public ResponseEntity<String> handleGameException(GameException ex) {
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
	
	@ExceptionHandler(GameListException.class)
	public ResponseEntity<String> handleGameListException(GameListException ex) {
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
