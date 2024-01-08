package com.turgor.nowe_kolory.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.turgor.nowe_kolory.domain.entities.MovieEntity;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Mono<MovieEntity> findOne(String imdbID) throws JsonProcessingException;
    
}
