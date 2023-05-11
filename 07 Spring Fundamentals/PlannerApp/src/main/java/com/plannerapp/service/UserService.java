package com.plannerapp.service;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.model.service.UserServiceModel;
import com.plannerapp.model.view.TaskViewModel;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void addTaskToUser(Long userID, Task task);

    Set<TaskViewModel> getAssignedTasks(Long userID);



}
