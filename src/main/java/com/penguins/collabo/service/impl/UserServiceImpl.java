package com.penguins.collabo.service.impl;


import com.penguins.collabo.Repository.UserRepository;
import com.penguins.collabo.models.UserEntity;
import com.penguins.collabo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> findAllUsers(){

        return userRepository.findAll();

    }

    @Override
    public UserEntity findUserById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        List<UserEntity> users = userRepository.findAll();
        for (UserEntity user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean authenticate(String username, String password) {
        List<UserEntity> users = userRepository.findAll();
        boolean found = false;
        for (UserEntity user : users) {
            if (user.getUsername().equals(username)) {
                found = true;
                return user.getPassword().equals(password);
            }
        }
        return false;
    }

    @Override
    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }


}
