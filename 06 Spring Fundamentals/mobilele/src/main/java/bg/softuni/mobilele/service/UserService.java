package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.DTO.UserLoginDTO;
import bg.softuni.mobilele.model.UserEntity;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private PasswordEncoder passwordEncoder;

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    public UserService(UserRepository userRepository,
                       CurrentUser currentUser,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
    }


    public boolean login(UserLoginDTO loginDTO) {

        Optional<UserEntity> userOptional = userRepository.
                findByEmail(loginDTO.getUsername());

        if (userOptional.isEmpty()) {

            LOGGER.info("User with not found. User name: {}",
                    loginDTO.getUsername());
            return false;
        }

       var rawPassword = loginDTO.getPassword();
        var encodedPassword= userOptional.get().getPassword();


        boolean success = passwordEncoder
                .matches(rawPassword, encodedPassword);
        if (success) {
            login(userOptional.get());

        } else {
            logout();
        }
        return success;


    }

    private void login(UserEntity userEntity){
        currentUser
                .setLoggedIn(true)
                .setName(userEntity.getFirstName() + " " + userEntity.getLastName());
    }

    public void logout() {
       currentUser.clear();
    }
}
