package com.example.shoppingList.service.impl;

import com.example.shoppingList.model.entity.User;
import com.example.shoppingList.model.service.UserServiceModel;
import com.example.shoppingList.repository.UserRepository;
import com.example.shoppingList.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        this.userRepository.save(user);

    }

}
