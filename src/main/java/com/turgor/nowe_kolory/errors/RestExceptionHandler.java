package com.turgor.nowe_kolory.errors;

import com.turgor.nowe_kolory.errors.customErrors.InvalidJsonResponseException;
import com.turgor.nowe_kolory.errors.customErrors.UnableToFetchMovieException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@ControllerAdvice

public class RestExceptionHandler {

    @ExceptionHandler(value = InvalidJsonResponseException.class)
    public ResponseEntity<ApiError> handleNoMovieFoundException(InvalidJsonResponseException err) {
        ApiError error = new ApiError("Failed to parse string response to json", err.getJsonString(), new Date());
        return new ResponseEntity<ApiError>(error, err.getStatusCode());
    }

    @ExceptionHandler(value = UnableToFetchMovieException.class)
    public ResponseEntity<ApiError> handleNoMovieFoundException(UnableToFetchMovieException err) {
        err.getApiError().setTimestamp(new Date());
        return new ResponseEntity<ApiError>(err.getApiError(), err.getStatusCode());
    }
}
