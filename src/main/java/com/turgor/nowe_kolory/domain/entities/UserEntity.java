package com.turgor.nowe_kolory.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_movie",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "imdbID", referencedColumnName = "imdbID")
    )
    @JsonManagedReference
    @JsonIgnore
    private Set<MovieEntity> movies;


    public boolean hasMovie(MovieEntity movieEntity) {
        return movies != null && movies.contains(movieEntity);
    }

    public void addMovie(MovieEntity movie) {
        this.movies.add(movie);
    }

    public void ensureMoviesInitialized() {
        if (movies == null) {
            this.movies = new HashSet<>();
        }
    }
//    @Override
//    public String toString() {
//        return "UserEntity{" +
//                // Include other fields...
//                ", movies=" + (movies != null ? movies.size() : "null") +
//                '}';
//    }
}
