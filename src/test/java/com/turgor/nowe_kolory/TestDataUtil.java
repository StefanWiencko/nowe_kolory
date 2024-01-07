package com.turgor.nowe_kolory;

import com.turgor.nowe_kolory.domain.entities.MovieEntity;

public final class TestDataUtil {
    private TestDataUtil() {
    }


    public static MovieEntity createTestMovieEntityA() {
        return MovieEntity.builder()
                .imdbID("tt0490210")
                .title("Sarkar Raj")
                .plot("An even more powerful gang emerges to wipe out Sarkar from the playground. A power plant proposal becomes a cause for greater political turmoil around him. What happens to the Family?")
                .genre("Action, Crime, Drama")
                .director("Ram Gopal Varma")
                .poster("https://m.media-amazon.com/images/M/MV5BYjRmNzI1MzAtYmI0NS00NmRmLTk0Y2ItYWRjMDY2ZmMxNzQ2XkEyXkFqcGdeQXVyNjQ2MjQ5NzM@._V1_SX300.jpg")
                .isFavourite(true)
                .build();
    }

    public static MovieEntity createTestMovieEntityB() {
        return MovieEntity.builder()
                .imdbID("tt0490211")
                .title("Saubere Ernte")
                .plot("N/A")
                .genre("Documentary")
                .director("Peter Heller")
                .poster("N/A")
                .isFavourite(true)
                .build();
    }
}
