package com.turgor.nowe_kolory.errors;

import com.turgor.nowe_kolory.errors.customErrors.NoMovieFoundException;
import com.turgor.nowe_kolory.errors.customErrors.UnableToFetchDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@ControllerAdvice

public class RestExceptionHandler {

    @ExceptionHandler(value = NoMovieFoundException.class)
    public ResponseEntity<ApiError> handleNoMovieFoundException(NoMovieFoundException err) {
        ApiError error = new ApiError(400, err.getMessage(), new Date(), err.getImdbID());
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UnableToFetchDataException.class)
    public ResponseEntity<ApiError> handleNoMovieFoundException(UnableToFetchDataException err) {
        ApiError error = new ApiError(400, err.getMessage(), new Date(), err.getImdbID());
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }
}
