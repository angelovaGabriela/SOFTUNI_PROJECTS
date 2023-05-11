package com.plannerapp.service.impl;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.service.HomeService;
import com.plannerapp.service.TaskService;
import com.plannerapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeServiceImpl implements HomeService {
    private final TaskService taskService;
    private final UserService userService;

    private final TaskRepository taskRepository;



    public HomeServiceImpl(TaskService taskService, UserService userService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.userService = userService;
        this.taskRepository = taskRepository;
    }

    @Override
    public void assignTask(Long taskID, Long userID) {

        Task task = this.taskService.findTaskById(taskID);
        Optional<User> user = this.userService.findById(userID);
        task.setUser(user.get());
        this.taskRepository.save(task);
        this.userService.addTaskToUser(userID, task);
    }


}
