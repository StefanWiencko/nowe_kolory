package com.turgor.nowe_kolory.repositories;

import com.turgor.nowe_kolory.domain.entities.MovieEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, String> {
}
