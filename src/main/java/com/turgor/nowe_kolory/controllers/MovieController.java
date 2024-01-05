package com.turgor.nowe_kolory.controllers;

import com.turgor.nowe_kolory.domain.dto.MovieDto;
import com.turgor.nowe_kolory.domain.entities.MovieEntity;
import com.turgor.nowe_kolory.mappers.Mapper;
import com.turgor.nowe_kolory.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MovieController {

    private MovieService movieService;

    private Mapper<MovieEntity, MovieDto> movieMapper;

    public MovieController(MovieService movieService, Mapper<MovieEntity, MovieDto> movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @GetMapping(path = "/")
    public String test() {
        return "Hello";
    }

    @GetMapping(path = "/movies")
    public ResponseEntity<MovieDto> getMovie(@RequestParam String imdbID) {
        Optional<MovieEntity> foundMovie = movieService.findOne(imdbID);
        return foundMovie.map(movieEntity -> {
            System.out.println(movieEntity);
            MovieDto dto = movieMapper.mapTo((MovieEntity) movieEntity);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
