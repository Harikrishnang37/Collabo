package com.penguins.collabo.service;

import com.penguins.collabo.models.UserEntity;

import java.util.List;
import java.util.Optional;


public interface UserService {

    public List<UserEntity> findAllUsers();
    public UserEntity findUserById(int id);
    public UserEntity findUserByUsername(String username);
    public boolean authenticate(String username, String password);

    public void saveUser(UserEntity user);

}
