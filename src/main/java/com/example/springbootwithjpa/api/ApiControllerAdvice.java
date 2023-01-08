package com.example.springbootwithjpa.api;

import com.example.springbootwithjpa.exception.NotEnoughStockException;
import com.example.springbootwithjpa.exception.NotFoundItemException;
import com.example.springbootwithjpa.exception.NotFoundMemberException;
import com.example.springbootwithjpa.exception.NotFoundOrderException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {
    @ExceptionHandler(value = {
        IllegalArgumentException.class,
        IllegalStateException.class,
        NotEnoughStockException.class
    })
    public ApiResult<?> badRequest(Exception e) {
        return ApiResult.fail(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
        NotFoundItemException.class,
        NotFoundMemberException.class,
        NotFoundOrderException.class
    })
    public ApiResult<?> notFound(Exception e) {
        return ApiResult.fail(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ApiResult<?> exception(Exception e) {
        return ApiResult.fail(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
