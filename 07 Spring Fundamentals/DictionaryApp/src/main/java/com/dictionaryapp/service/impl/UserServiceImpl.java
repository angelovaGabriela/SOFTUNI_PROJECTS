package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.service.UserServiceModel;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper, CurrentUser currentUser) {
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
    public UserServiceModel findUser(String username, String password) {

        return userRepository.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class)).orElse(null);

    }

    @Override
    public void login(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }
}
