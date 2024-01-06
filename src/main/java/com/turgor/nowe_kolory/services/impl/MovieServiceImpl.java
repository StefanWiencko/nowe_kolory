package com.turgor.nowe_kolory.services.impl;


import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turgor.nowe_kolory.domain.entities.MovieEntity;
import com.turgor.nowe_kolory.errors.customErrors.NoMovieFoundException;
import com.turgor.nowe_kolory.repositories.MovieRepository;
import com.turgor.nowe_kolory.services.MovieService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    //    @Override
//    public Optional<MovieEntity> findOne(String imdbID) {
//        if (isExists(imdbID)) {
//            return findOneFavourite(imdbID);
//        }
//        try {
//            String urlWithParams = UriComponentsBuilder.fromUriString(apiUrl)
//                    .queryParam("i", imdbID)
//                    .queryParam("apikey", apiKey)
//                    .build()
//                    .toUriString();
//            String jsonResponse = restTemplate.getForObject(urlWithParams, String.class);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.readTree(jsonResponse);
//
//            // If parsing succeeds, convert the JSON string to MovieEntity
//            MovieEntity response = objectMapper.readValue(jsonResponse, MovieEntity.class);
//
//            if (response.isEmpty()) {
//                throw new NoMovieFoundException("No movie found", imdbID);
//            }
////            return null;
//            return Optional.of(response);
//        } catch (RestClientException e) {
//            throw new UnableToFetchDataException("Error during fetching data.", imdbID);
//        } catch (JsonMappingException e) {
//            throw new UnableToFetchDataException("Error during mapping", imdbID);
//        } catch (JsonProcessingException e) {
//            throw new UnableToFetchDataException("Error during fetching data.", imdbID);
//        }
//
//    }
    @Override
    public Optional<MovieEntity> findOne(String imdbID) {
        String urlWithParams = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("i", imdbID)
                .queryParam("apikey", apiKey)
                .build()
                .toUriString();
        MovieEntity response = restTemplate.getForObject(urlWithParams, MovieEntity.class);

        if (response.isEmpty()) {
            throw new NoMovieFoundException("No movie found", imdbID);
        }

        return Optional.of(response);
    }

    @Override
    public MovieEntity addFavourite(MovieEntity movieEntity) {
        movieEntity.setFavourite(true);
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
