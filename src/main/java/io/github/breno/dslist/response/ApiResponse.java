package io.github.breno.dslist.response;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "count", "data", "message", "timestamp" })
public record ApiResponse<T>(
		int count, 
		T data, 
		String message, 
		Instant timestamp
) {
	
	public ApiResponse(int count, T data) {
		this(count, data, null, Instant.now());
	}
	
	public ApiResponse(int count, T data, String message) {
		this(count, data, message, Instant.now());
	}
	
	public static <T> ApiResponse<List<T>> ofList(List<T> list) {
        return new ApiResponse<>(list == null ? 0 : list.size(), list);
    }

	public static <T> ApiResponse<T> ofSingle(T item) {
		return new ApiResponse<>(item == null ? 0 : 1, item);
	}

	public static ApiResponse<Void> successMessage(String message) {
		return new ApiResponse<>(0, null, message);
	}

	public static ApiResponse<Void> errorMessage(String message) {
		return new ApiResponse<>(0, null, message);
	}
}
