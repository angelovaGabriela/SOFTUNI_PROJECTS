package com.example.demo.services.impl;

import com.example.demo.entities.users.LoginDTO;
import com.example.demo.entities.users.RegisterDTO;
import com.example.demo.entities.users.User;
import com.example.demo.services.ExecutorService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExecutorServiceImpl implements ExecutorService {
    private final UserService userService;

    @Autowired
    public ExecutorServiceImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(String commandLine) {

        String[] commandParts = commandLine.split("\\|");
        String commandName = commandParts[0];

        String commandOutput = "";

        switch (commandName) {
            case REGISTER_USER_COMMAND:
                RegisterDTO registerData = new RegisterDTO(commandParts);
                User user = userService.register(registerData);
                commandOutput = String.format("%s was registered", user.getFullName());
                break;
            case LOGIN_USER_COMMAND:
                LoginDTO loginData = new LoginDTO(commandParts);
                Optional<User> user2 = userService.login(loginData);

                if (user2.isPresent()) {
                    commandOutput = String.format("Successfully logged in %s", user2.get().getFullName());

                } else {
                    commandOutput = "Wrong credentials";
                }

                break;
            case LOGOUT_USER_COMMAND:
                //първо трябва да взема потребителя за да мога да знам, кой е след като го логаутна
               User loggedUser = this.userService.getLoggedUser();
              // check if actual user
                this.userService.logout();
                commandOutput = String.format("User successfully logged out %s",loggedUser.getFullName());
                break;

            case ADD_GAME_COMMAND:
                User loggedInUser = this.userService.getLoggedUser();
                if (!loggedInUser.isAdmin()){
                    // throw new
                }

                //DTO

                 break;
            // default:
            //return "unknown command";
        }

        return commandOutput;
    }
}
