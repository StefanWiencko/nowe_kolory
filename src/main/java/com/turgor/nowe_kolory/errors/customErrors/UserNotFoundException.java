package com.turgor.nowe_kolory.errors.customErrors;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserNotFoundException extends RuntimeException {
    private final HttpStatus statusCode;

    public UserNotFoundException(String msg, HttpStatus statusCode) {
        super(msg);
        this.statusCode = statusCode;
    }
}
