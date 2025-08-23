package io.github.breno.dslist.response;

public record ApiResponse<T>(int count, T data) {
	
}
