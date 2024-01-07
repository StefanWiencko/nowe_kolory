package com.turgor.nowe_kolory.errors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatusCode;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiError {
    @JsonProperty("Error")
    private String error;
    @JsonProperty("Response")
    private String response;
    @JsonProperty("Timestamp")
    private Date timestamp;
}
