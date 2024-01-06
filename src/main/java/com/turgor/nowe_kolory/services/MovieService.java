package com.turgor.nowe_kolory.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.turgor.nowe_kolory.domain.entities.MovieEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Optional<MovieEntity> findOne(String imdbID) throws JsonProcessingException;

    MovieEntity addFavourite(MovieEntity movieEntity);

    List<MovieEntity> findAllFavourites();

    Optional<MovieEntity> findOneFavourite(String imdbID);

    boolean isExists(String imdbID);

}
