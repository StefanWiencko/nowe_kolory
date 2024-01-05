package com.turgor.nowe_kolory.errors.customErrors;

public class NoMovieFoundException extends RuntimeException {
    public NoMovieFoundException(String msg) {
        super(msg);
    }
}
