package com.turgor.nowe_kolory.services.impl;


import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turgor.nowe_kolory.errors.ApiError;
import com.turgor.nowe_kolory.errors.customErrors.InvalidJsonResponseException;
import com.turgor.nowe_kolory.errors.customErrors.UnableToFetchMovieException;
import com.turgor.nowe_kolory.domain.entities.MovieEntity;
import com.turgor.nowe_kolory.repositories.MovieRepository;
import com.turgor.nowe_kolory.services.MovieService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final WebClient webClient;
    @Value("${movie.api.key}")
    private String apiKey;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.webClient = WebClient.builder()
                .baseUrl("https://www.omdbapi.com/")
                .build();
        this.movieRepository = movieRepository;
    }

    @Override
    public Mono<MovieEntity> findOne(String imdbID) {
        Optional<MovieEntity> savedEntity = findOneFavourite(imdbID);
        return savedEntity.map(Mono::just)
                .orElseGet(() -> webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/")
                                .queryParam("i", imdbID)
                                .queryParam("apikey", apiKey)
                                .build())
                        .retrieve().onStatus(HttpStatusCode::is4xxClientError, response ->
                                response.bodyToMono(String.class)
                                        .flatMap(errorBody -> {
                                            HttpStatus status = HttpStatus.valueOf(response.statusCode().value());
                                            throw new UnableToFetchMovieException("Error 4xx in API response", convertToEntity(errorBody, ApiError.class), status);
                                        })
                        )
                        .bodyToMono(String.class)
                        .flatMap(responseBody -> {
                            if (isErrorResponse(responseBody)) {
                                System.out.println(responseBody);
                                throw new UnableToFetchMovieException("Error in API response", convertToEntity(responseBody, ApiError.class), HttpStatus.BAD_REQUEST);
                            } else {
                                return Mono.just(convertToEntity(responseBody, MovieEntity.class));
                            }
                        }));
    }

    private <T> T convertToEntity(String responseBody, Class<T> targetClass) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(responseBody, targetClass);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new InvalidJsonResponseException("Error parsing string to json", responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isErrorResponse(String responseBody) {
        return responseBody.contains("\"Response\":\"False\"");
    }

    @Override
    public MovieEntity addFavourite(MovieEntity movieEntity) {
        movieEntity.setIsFavourite(true);
        return movieRepository.save(movieEntity);
    }

    @Override
    public Optional<MovieEntity> findOneFavourite(String imdbID) {
        return movieRepository.findById(imdbID);
    }

    @Override
    public boolean isExists(String imdbID) {
        return movieRepository.existsById(imdbID);
    }

    @Override
    public List<MovieEntity> findAllFavourites() {
        return StreamSupport.stream(movieRepository
                                .findByIsFavouriteTrue()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }
}
