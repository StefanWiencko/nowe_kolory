package com.turgor.nowe_kolory.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.turgor.nowe_kolory.domain.dto.MovieDto;
import com.turgor.nowe_kolory.domain.entities.MovieEntity;
import com.turgor.nowe_kolory.mappers.Mapper;
import com.turgor.nowe_kolory.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MovieController {

    private MovieService movieService;

    private Mapper<MovieEntity, MovieDto> movieMapper;

    public MovieController(MovieService movieService, Mapper<MovieEntity, MovieDto> movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @GetMapping(path = "/movies", params = "imdbID")
    public Mono<ResponseEntity<MovieDto>> getMovie(@RequestParam String imdbID) throws JsonProcessingException {
        return movieService.findOne(imdbID)
                .map(movieEntity -> {
                    MovieDto dto = movieMapper.mapTo(movieEntity);
                    return new ResponseEntity<>(dto, HttpStatus.OK);
                })
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/movies")
    public List<MovieDto> getAllMovies() {
        List<MovieEntity> authors = movieService.findAllFavourites();
        return authors.stream()
                .map(movieMapper::mapTo)
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/movies")
    public ResponseEntity<MovieDto> addFavourite(@RequestParam String imdbID) throws JsonProcessingException {
        Mono<MovieEntity> foundMovie = movieService.findOne(imdbID);
        MovieEntity movieEntity = foundMovie.block();

        boolean isFavourite = movieEntity != null && movieEntity.checkIsFavourite();

        if (isFavourite) {
            MovieDto movieDto = movieMapper.mapTo(movieEntity);

            return new ResponseEntity<>(movieDto, HttpStatus.OK);
        } else {
            MovieEntity savedMovieEntity = movieService.addFavourite(movieEntity);
            MovieDto movieDto = movieMapper.mapTo(savedMovieEntity);

            return new ResponseEntity<>(movieDto, HttpStatus.CREATED);
        }
    }
}
