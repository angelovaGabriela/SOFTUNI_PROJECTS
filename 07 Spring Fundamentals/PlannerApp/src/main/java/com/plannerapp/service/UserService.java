package com.plannerapp.service;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void addTaskToUser(Long userID, Task task);
}
