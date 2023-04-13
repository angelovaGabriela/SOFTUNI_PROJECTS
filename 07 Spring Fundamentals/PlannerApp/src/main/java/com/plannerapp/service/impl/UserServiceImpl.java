package com.plannerapp.service.impl;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.model.service.UserServiceModel;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.UserService;
import com.plannerapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final CurrentUser currentUser;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {

        return userRepository.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    public void addTasksToUser(Long userId, Task task) {
        User user = this.getUserById(userId);
        if (user.getAssignedTasks().stream().noneMatch(s -> s.getId().equals(task.getId()))) {
            user.addTask(task);
            this.userRepository.save(user);
        }
    }

    public void removeTasksFromUser(Long userID, Long taskID) {
        User user = getUserById(userID);
        user.removeTaskFromList(taskID);
        this.userRepository.save(user);
    }

    public void deleteTasks(Long id) {
        User user = getUserById(id);
        user.deleteAllTasks();
        this.userRepository.save(user);
    }

    private User getUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow();
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }
}
