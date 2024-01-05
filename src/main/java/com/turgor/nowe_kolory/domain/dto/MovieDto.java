package com.turgor.nowe_kolory.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {

    private String imdbID;

    private String title;
    private String plot;
    private String genre;
    private String director;
    private String poster;
}
