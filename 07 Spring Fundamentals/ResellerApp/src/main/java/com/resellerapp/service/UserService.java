package com.resellerapp.service;

import com.resellerapp.model.entity.User;
import com.resellerapp.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    User findById(Long id);

    void saveUser(User seller);
}
