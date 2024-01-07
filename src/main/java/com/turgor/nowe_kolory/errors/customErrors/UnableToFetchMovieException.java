package com.turgor.nowe_kolory.errors.customErrors;

import com.turgor.nowe_kolory.errors.ApiError;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data

public class UnableToFetchMovieException extends RuntimeException {
    private final ApiError apiError;
    private final HttpStatus statusCode;

    public UnableToFetchMovieException(String msg, ApiError apiError, HttpStatus statusCode) {
        super(msg);
        this.apiError = apiError;
        this.statusCode = statusCode;
    }
}
