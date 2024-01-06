package com.turgor.nowe_kolory.errors.customErrors;

import lombok.Data;

@Data

public class UnableToFetchDataException extends RuntimeException {
    private final String imdbID;

    public UnableToFetchDataException(String msg, String imdbID) {
        super(msg);
        this.imdbID = imdbID;
    }
}
