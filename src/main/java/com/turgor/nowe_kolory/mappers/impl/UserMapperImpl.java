package com.turgor.nowe_kolory.mappers.impl;

import com.turgor.nowe_kolory.domain.dto.UserDto;
import com.turgor.nowe_kolory.domain.entities.UserEntity;
import com.turgor.nowe_kolory.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements Mapper<UserEntity, UserDto> {
    private final ModelMapper modelMapper;

    public UserMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto mapTo(UserEntity movieEntity) {
        return modelMapper.map(movieEntity, UserDto.class);
    }

    @Override
    public UserEntity mapFrom(UserDto movieDto) {
        return modelMapper.map(movieDto, UserEntity.class);
    }
}

