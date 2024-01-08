package com.turgor.nowe_kolory.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.turgor.nowe_kolory.domain.dto.MovieDto;
import com.turgor.nowe_kolory.domain.dto.UserDto;
import com.turgor.nowe_kolory.domain.entities.MovieEntity;
import com.turgor.nowe_kolory.domain.entities.UserEntity;
import com.turgor.nowe_kolory.errors.customErrors.UserNotFoundException;
import com.turgor.nowe_kolory.mappers.Mapper;
import com.turgor.nowe_kolory.services.MovieService;
import com.turgor.nowe_kolory.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class MovieController {
    private final MovieService movieService;
    private final UserService userService;
    private final Mapper<UserEntity, UserDto> usermapper;

    private final Mapper<MovieEntity, MovieDto> movieMapper;

    public MovieController(MovieService movieService, UserService userService, Mapper<MovieEntity, MovieDto> movieMapper, Mapper<UserEntity, UserDto> userMapper) {
        this.movieService = movieService;
        this.userService = userService;
        this.usermapper = userMapper;
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
    public ResponseEntity<List<MovieDto>> getAllFavouriteMovies(@RequestParam String name) {
        Optional<UserEntity> user = userService.findByName(name);

        if (user.isPresent()) {
            List<MovieDto> movieDtos = user.get().getMovies().stream()
                    .map(movieMapper::mapTo)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(movieDtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(path = "/movies")
    public ResponseEntity<MovieDto> saveFavouriteMovie(@RequestParam String imdbID, @RequestParam String name) throws JsonProcessingException {
        Mono<MovieEntity> foundMovie = movieService.findOne(imdbID);
        UserEntity user = userService.findOrCreateUserByName(name);
        MovieEntity movieEntity = foundMovie.block();

        boolean isFavourite = user.hasMovie(movieEntity);
        if (isFavourite) {

            MovieDto movieDto = movieMapper.mapTo(movieEntity);
            return new ResponseEntity<>(movieDto, HttpStatus.OK);
        } else {
            user.ensureMoviesInitialized();
            user.addMovie(movieEntity);
            userService.saveUser(user);

            MovieDto movieDto = movieMapper.mapTo(movieEntity);
            return new ResponseEntity<>(movieDto, HttpStatus.CREATED);
        }
    }
}
