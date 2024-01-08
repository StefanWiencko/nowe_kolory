package com.turgor.nowe_kolory.services.impl;

import com.turgor.nowe_kolory.domain.entities.UserEntity;
import com.turgor.nowe_kolory.repositories.UserRepository;
import com.turgor.nowe_kolory.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity findOrCreateUserByName(String name) {
        Optional<UserEntity> existingUser = userRepository.findByName(name);

        if (existingUser.isPresent()) {
            return existingUser.get();
        } else {
            UserEntity newUser = new UserEntity();
            newUser.setName(name);
            return userRepository.save(newUser);
        }
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    public Optional<UserEntity> findByName(String name) {
        return userRepository.findByName(name);
    }
}
