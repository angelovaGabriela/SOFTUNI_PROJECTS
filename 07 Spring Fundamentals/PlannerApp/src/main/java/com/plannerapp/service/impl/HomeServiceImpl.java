package com.plannerapp.service.impl;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.HomeService;
import com.plannerapp.service.TaskService;
import com.plannerapp.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class HomeServiceImpl implements HomeService {
    private final TaskService taskService;
    private final UserService userService;

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;



    public HomeServiceImpl(TaskService taskService, UserService userService, TaskRepository taskRepository, UserRepository userRepository) {
        this.taskService = taskService;
        this.userService = userService;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void assignTask(Long taskID, Long userID) {

        Task task = this.taskService.findTaskById(taskID);
        Optional<User> user = this.userService.findById(userID);
        task.setUser(user.get());
        this.taskRepository.save(task);
        this.userService.addTaskToUser(userID, task);
    }


    @Transactional
    @Override
    public void deleteTask(Long taskID, Long userID) {
        User user = this.userService.getUserById(userID);
        Task task = this.taskService.findTaskById(taskID);

        user.getAssignedTasks().remove(task);
        this.userRepository.save(user);

        this.taskRepository.deleteById(taskID);
    }


}
