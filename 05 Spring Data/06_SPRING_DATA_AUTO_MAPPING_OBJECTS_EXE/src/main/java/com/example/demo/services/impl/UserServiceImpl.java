package com.example.demo.services.impl;

import com.example.demo.entities.users.LoginDTO;
import com.example.demo.entities.users.RegisterDTO;
import com.example.demo.entities.users.User;
import com.example.demo.exceptions.UserNotLoggedInException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private User currentUser;
    private final UserRepository userRepository;

   @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.currentUser = null;
        this.userRepository = userRepository;
    }


    @Override
    public User register(RegisterDTO registerData) {
      if (this.currentUser != null) {
        // throw exception / return;
      }



       ModelMapper mapper = new ModelMapper(); // add it first in the pom.xml file
       User toRegister = mapper.map(registerData, User.class);

       long userCount = this.userRepository.count();

       if (userCount == 0) {
           toRegister.setAdmin(true);
       }

      return this.userRepository.save(toRegister);
   }

    @Override
    public Optional<User> login(LoginDTO loginData) { // трябва да намери в Repository регистрирания потребител с неговите имел и парола
        if (this.currentUser != null) {
            // throw exception / return;
        }
        Optional<User> user = this.userRepository.findByEmailAndPassword(
                loginData.getEmail(), loginData.getPassword());

        user.ifPresent(value -> this.currentUser = value);

        return user;

    }
    public User getLoggedUser() {
       if (this.currentUser == null) {
           throw new UserNotLoggedInException();
       }
      return this.currentUser;
    }


    @Override
    public void logout() {
       //TODO: Cannot log out. No user was logged in.
       this.currentUser = null;
    }
}
