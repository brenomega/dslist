package io.github.breno.dslist.payload;

public record ApiResponse<T>(int count, T data) {
	
}
