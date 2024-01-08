package com.turgor.nowe_kolory.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.turgor.nowe_kolory.domain.entities.MovieEntity;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String name;
}
