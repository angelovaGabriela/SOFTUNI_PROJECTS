package com.example.eprep.service.impl;

import com.example.eprep.model.entity.User;
import com.example.eprep.model.service.UserServiceModel;
import com.example.eprep.repository.UserRepository;
import com.example.eprep.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);

        return modelMapper.map(userRepository.save(user), UserServiceModel.class);
    }
}
