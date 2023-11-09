package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.service.UserServiceModel;

import java.util.Optional;

public interface UserService {

    void registerUser(UserServiceModel userServiceModel);


    UserServiceModel findUser(String username, String password);

    void login(Long id, String username);
}
