package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.DTO.UserLoginDTO;
import bg.softuni.mobilele.model.UserEntity;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    public UserService(UserRepository userRepository,
                       CurrentUser currentUser) {
        this.userRepository = userRepository;

        this.currentUser = currentUser;
    }


    public boolean login(UserLoginDTO loginDTO) {

        Optional<UserEntity> userOptional = userRepository.
                findByEmail(loginDTO.getUsername());

        if (userOptional.isEmpty()) {

            LOGGER.info("User with not found. User name: {}",
                    loginDTO.getUsername());
            return false;
        }

       boolean success = userOptional.get().getPassword().equals(loginDTO.getPassword());

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
