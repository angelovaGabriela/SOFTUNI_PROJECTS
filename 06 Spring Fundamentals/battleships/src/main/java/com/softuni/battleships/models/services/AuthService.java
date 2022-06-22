package com.softuni.battleships.models.services;

import com.softuni.battleships.models.User;
import com.softuni.battleships.models.dtos.LoginDTO;
import com.softuni.battleships.models.dtos.UserRegistrationDTO;
import com.softuni.battleships.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(UserRegistrationDTO userRegistrationDTO) {

        if(!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
            return false;
        }

        // duplicate email
        Optional<User> byEmail = this.userRepository.findByEmail(userRegistrationDTO.getEmail());
        if (byEmail.isPresent()) { // if there is somebody else with the same email
            return false; // you can't register with this email, it already exists
        }

        // duplicate username
        Optional<User> byUsername = this.userRepository.findByUsername(userRegistrationDTO.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }

        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setFullName(userRegistrationDTO.getFullName());
        user.setPassword(userRegistrationDTO.getPassword());

        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginDTO loginDTO) {
        return false;
    }
}
