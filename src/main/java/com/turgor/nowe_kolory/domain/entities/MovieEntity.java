package com.turgor.nowe_kolory.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movies")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieEntity {

    @Id
    private String imdbID;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Plot")
    private String plot;

    @JsonProperty("Genre")
    private String genre;

    @JsonProperty("Director")
    private String director;

    @JsonProperty("Poster")
    private String poster;

    private Boolean isFavourite;

    public boolean checkIsFavourite() {
        return Boolean.TRUE.equals(isFavourite);
    }
}
