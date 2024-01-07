package com.turgor.nowe_kolory.errors.customErrors;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data

public class InvalidJsonResponseException extends RuntimeException {
    private final String jsonString;
    private final HttpStatus statusCode;

    public InvalidJsonResponseException(String msg, String jsonString, HttpStatus statusCode) {
        super(msg);
        this.jsonString = jsonString;
        this.statusCode = statusCode;
    }
}
