package com.turgor.nowe_kolory.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {
    private Integer errorCode;
    private String errorMsg;
    private Date date;
}
