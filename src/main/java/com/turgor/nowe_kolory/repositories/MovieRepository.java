package com.turgor.nowe_kolory.repositories;

import com.turgor.nowe_kolory.domain.entities.MovieEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, String> {
    Iterable<MovieEntity> findByIsFavouriteTrue();
}
