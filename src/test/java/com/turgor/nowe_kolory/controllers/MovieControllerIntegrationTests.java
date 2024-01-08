package com.turgor.nowe_kolory.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turgor.nowe_kolory.TestDataUtil;
import com.turgor.nowe_kolory.domain.entities.MovieEntity;
import com.turgor.nowe_kolory.services.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class MovieControllerIntegrationTests {
    private final MovieService movieService;

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @Autowired
    public MovieControllerIntegrationTests(MockMvc mockMvc, MovieService movieService) {
        this.mockMvc = mockMvc;
        this.movieService = movieService;
        this.objectMapper = new ObjectMapper();
    }


    @Test
    public void testThatAddFavouriteSuccessfullyReturnsHttp201Created() throws Exception {
        MovieEntity testMovieA = TestDataUtil.createTestMovieEntityA();
        String movieJson = objectMapper.writeValueAsString(testMovieA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("imdbID", "tt0490210")
                        .content(movieJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.imdbID").value("tt0490210")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isFavourite").value("true")
        );
    }

//    @Test
//    public void testThatAddFavouriteSuccessfullyReturnsHttp200WhenMovieInDB() throws Exception {
//        MovieEntity testMovieA = TestDataUtil.createTestMovieEntityA();
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/movies")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("imdbID", "tt0490210")
//        ).andExpect(
//                MockMvcResultMatchers.status().isOk()
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.imdbID").value("tt0490210")
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.isFavourite").value("true")
//        );
//    }


    @Test
    public void testThatGetAllFavouriteSuccessfullyReturnsHttp200() throws Exception {
        MovieEntity testMovieA = TestDataUtil.createTestMovieEntityA();
        String movieJson = objectMapper.writeValueAsString(testMovieA);


        mockMvc.perform(
                MockMvcRequestBuilders.get("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(movieJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

//    @Test
//    public void testThatGetAllFavouriteSuccessfullyReturnsCorrectNumberOfMovies() throws Exception {
//        MovieEntity testMovieA = TestDataUtil.createTestMovieEntityA();
//        MovieEntity testMovieB = TestDataUtil.createTestMovieEntityB();
//
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/movies")
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(
//                MockMvcResultMatchers.status().isOk()
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$[0].imdbID").value("tt0490210")
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$[0].isFavourite").value("true")
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$[1].imdbID").value("tt0490211")
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$[1].isFavourite").value("true")
//        );
//    }

    @Test
    public void testThatGetAllFavouriteSuccessfullyReturnsEmptyArrWhenNOData() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                content().json("[]"));
    }

    @Test
    public void testThatGetMovieSuccessfullyReturnsHttp200() throws Exception {
        MovieEntity testMovieA = TestDataUtil.createTestMovieEntityA();
        String movieJson = objectMapper.writeValueAsString(testMovieA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(movieJson)
                        .param("imdbID", "tt0490210")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }
}
