package com.turgor.nowe_kolory.errors;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private Integer errorCode;
    private String errorMsg;
    private Date date;
    private String imdbID;
}
