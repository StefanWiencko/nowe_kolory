package com.turgor.nowe_kolory.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.turgor.nowe_kolory.domain.entities.UserEntity;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDto {
    private String imdbID;
    private String title;
    private String plot;
    private String genre;
    private String director;
    private String poster;
}
