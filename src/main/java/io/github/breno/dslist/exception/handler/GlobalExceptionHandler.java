package io.github.breno.dslist.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.breno.dslist.exception.GameException;
import io.github.breno.dslist.exception.GameListException;
import io.github.breno.dslist.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(GameException.class)
	public ResponseEntity<ApiResponse<Void>> handleGameException(GameException ex) {
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    			.body(ApiResponse.errorMessage(ex.getMessage()));
    }
	
	@ExceptionHandler(GameListException.class)
	public ResponseEntity<ApiResponse<Void>> handleGameListException(GameListException ex) {
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    			.body(ApiResponse.errorMessage(ex.getMessage()));
    }
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleAny(Exception ex) {
		logger.error("Unhandled exception", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ApiResponse.errorMessage("Internal server error."));
	}
}
