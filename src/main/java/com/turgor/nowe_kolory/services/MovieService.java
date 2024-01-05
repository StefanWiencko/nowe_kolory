package com.turgor.nowe_kolory.services;

import com.turgor.nowe_kolory.domain.entities.MovieEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Optional<MovieEntity> findOne(String imdbID);

    MovieEntity addFavourite(MovieEntity movieEntity);

    List<MovieEntity> findAllFavourites();

}
