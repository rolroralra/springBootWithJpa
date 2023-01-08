package com.example.springbootwithjpa.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class ApiResult<T> {
    private T result;
    private String message;

    private HttpStatus status;

    public static <T> ApiResult<T> success(T result) {
        return success(result, HttpStatus.OK);
    }

    public static <T> ApiResult<T> success(T result, HttpStatus status) {
        return new ApiResult<>(result, null, status);
    }

    public static ApiResult<?> fail(HttpStatus status) {
        return fail(status.getReasonPhrase(), status);
    }

    public static ApiResult<?> fail(String message, HttpStatus status) {
        return new ApiResult<>(null, message, status);
    }

}
