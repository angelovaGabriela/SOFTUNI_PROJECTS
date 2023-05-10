package com.plannerapp.service.impl;

import com.plannerapp.model.entity.Task;
import com.plannerapp.service.HomeService;
import com.plannerapp.service.TaskService;
import com.plannerapp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {
    private final TaskService taskService;
    private final UserService userService;

    public HomeServiceImpl(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @Override
    public void assignTask(Long taskID, Long userID) {

        Task task = this.taskService.findTaskById(taskID);
        this.userService.addTaskToUser(userID, task);
    }
}
