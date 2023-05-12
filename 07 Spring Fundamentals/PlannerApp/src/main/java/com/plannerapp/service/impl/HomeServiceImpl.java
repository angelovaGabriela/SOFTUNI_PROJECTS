package com.plannerapp.service.impl;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.model.view.TaskViewModel;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.HomeService;
import com.plannerapp.service.TaskService;
import com.plannerapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class HomeServiceImpl implements HomeService {
    private final TaskService taskService;
    private final UserService userService;

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private ModelMapper modelMapper;


    public HomeServiceImpl(TaskService taskService, UserService userService, TaskRepository taskRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.userService = userService;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
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

        user.deleteTask(task);
        this.userRepository.save(user);

        this.taskRepository.deleteById(taskID);
    }

    @Override
    public void returnToAvailable(Long taskID, Long userID) {
        User user = this.userService.getUserById(userID);
        Task task = this.taskService.findTaskById(taskID);

        user.deleteTask(task);

        task.setUser(null);
        this.userRepository.save(user);
        this.taskRepository.save(task);

        //TODO: return the task to the "AVAILABLE" and delete it from "ASSIGNED"
        // I remove the user ID from the task
        // Searching how to delete the task from the "AVAILABLE"

    }


}
