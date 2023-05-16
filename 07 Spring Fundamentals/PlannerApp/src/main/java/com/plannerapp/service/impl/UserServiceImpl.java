package com.plannerapp.service.impl;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.model.service.UserServiceModel;
import com.plannerapp.model.view.TaskViewModel;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.UserService;
import com.plannerapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
                .map(user -> modelMapper.map(user, UserServiceModel.class)).orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }

    @Override
    public void addTaskToUser(Long userID, Task task) {
        User user = this.getUserById(userID);
        if (user.getAssignedTasks().stream().noneMatch(t -> t.getId().equals(task.getId()))) {
            user.addTaskToUser(task);
            this.userRepository.save(user);
        }
    }

    @Override
    public Set<TaskViewModel> getAssignedTasks(Long userID) {
        return this.userRepository.findAssignedTasksById(userID)
            .stream().filter(task -> task.getUser() != null)
               .map(this::mapToTaskViewModel)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<User> findById(Long userID) {
        return userRepository.findById(userID);
    }

    @Override
    public String getUsername(Long id) {
        return this.currentUser.getUsername();
    }

    private TaskViewModel mapToTaskViewModel(Task task) {
       TaskViewModel taskView = new TaskViewModel();
        taskView.setId(task.getId());
        taskView.setDueDate(task.getDueDate());
        taskView.setPriority(task.getPriority());
        taskView.setDescription(task.getDescription());
        taskView.setUser(task.getUser());
        return taskView;
    }

    public User getUserById(Long userID) {
        return this.userRepository.findById(userID).orElseThrow();
    }

}
