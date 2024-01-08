package com.turgor.nowe_kolory.errors;

import com.turgor.nowe_kolory.errors.customErrors.InvalidJsonResponseException;
import com.turgor.nowe_kolory.errors.customErrors.UnableToFetchMovieException;
import com.turgor.nowe_kolory.errors.customErrors.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.Date;

@RestController
@ControllerAdvice

public class RestExceptionHandler {

    @ExceptionHandler(value = InvalidJsonResponseException.class)
    public ResponseEntity<ApiError> handleNoMovieFoundException(InvalidJsonResponseException err) {
        ApiError error = new ApiError("Failed to parse string response to json", err.getJsonString(), new Date());
        return new ResponseEntity<>(error, err.getStatusCode());
    }

    @ExceptionHandler(value = UnableToFetchMovieException.class)
    public ResponseEntity<ApiError> handleNoMovieFoundException(UnableToFetchMovieException err) {
        err.getApiError().setTimestamp(new Date());
        return new ResponseEntity<>(err.getApiError(), err.getStatusCode());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ApiError> handleNoMovieFoundException(UserNotFoundException err) {
        ApiError error = new ApiError(err.getMessage(), new Date());
        return new ResponseEntity<>(error, err.getStatusCode());
    }
}
