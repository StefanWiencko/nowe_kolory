package com.turgor.nowe_kolory.services;

import com.turgor.nowe_kolory.domain.entities.UserEntity;
import org.apache.catalina.User;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findByName(String name);

    UserEntity saveUser(UserEntity user);

    UserEntity findOrCreateUserByName(String name);
}
