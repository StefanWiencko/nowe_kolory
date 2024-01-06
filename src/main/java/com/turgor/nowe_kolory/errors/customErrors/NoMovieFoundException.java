package com.turgor.nowe_kolory.errors.customErrors;

import lombok.Data;

@Data

public class NoMovieFoundException extends RuntimeException {
    private final String imdbID;

    public NoMovieFoundException(String msg, String imdbID) {
        super(msg);
        this.imdbID = imdbID;
    }
}
