package com.turgor.nowe_kolory.services.impl;


import com.turgor.nowe_kolory.domain.entities.MovieEntity;
import com.turgor.nowe_kolory.errors.customErrors.NoMovieFoundException;
import com.turgor.nowe_kolory.repositories.MovieRepository;
import com.turgor.nowe_kolory.services.MovieService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private RestTemplate restTemplate;
    @Value("${movie.api.key}")
    private String apiKey;
    @Value("${movie.api.url}")
    String apiUrl;

    public MovieServiceImpl(MovieRepository movieRepository, RestTemplate restTemplate) {
        this.movieRepository = movieRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<MovieEntity> findOne(String imdbID) {
        String urlWithParams = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("i", imdbID)
                .queryParam("apikey", apiKey)
                .build()
                .toUriString();
        MovieEntity response = restTemplate.getForObject(urlWithParams, MovieEntity.class);

        if (response.isEmpty()) {
            throw new NoMovieFoundException("No movie found with imdbID: " + imdbID);
        }

        return Optional.of(response);
    }

    @Override
    public MovieEntity addFavourite(MovieEntity movieEntity) {
        return null;
    }

    @Override
    public List<MovieEntity> findAllFavourites() {
        return null;
    }
}
