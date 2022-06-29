package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.DTO.UserLoginDTO;
import bg.softuni.mobilele.model.UserEntity;
import bg.softuni.mobilele.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(UserLoginDTO loginDTO) {

        Optional<UserEntity> userOptional = userRepository.
                findByEmail(loginDTO.getUsername());

        if (userOptional.isEmpty()) {

            LOGGER.debug("User with [{}] not found", loginDTO.getUsername());
            return false;
        }

        return userOptional.get().getPassword().equals(loginDTO.getPassword());
    }
}
