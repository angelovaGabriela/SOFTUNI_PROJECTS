package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.DTO.UserLoginDTO;
import bg.softuni.mobilele.model.DTO.UserRegisterDTO;
import bg.softuni.mobilele.model.UserEntity;
import bg.softuni.mobilele.model.mapper.UserMapper;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    public UserService(UserRepository userRepository,
                       CurrentUser currentUser,
                       PasswordEncoder passwordEncoder,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    //TODO: validations !
    public void registerAndLogin(UserRegisterDTO userRegisterDTO) {


        UserEntity newUser = userMapper.userDtoToUserEntity(userRegisterDTO);
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));


             this.userRepository.save(newUser);
             login(newUser);


    }

    public boolean login(UserLoginDTO loginDTO) {

        Optional<UserEntity> userOptional = userRepository.
                findByEmail(loginDTO.getEmail());

        if (userOptional.isEmpty()) {

            LOGGER.info("User with not found. User name: {}",
                    loginDTO.getEmail());
            return false;
        }

       String rawPassword = loginDTO.getPassword();
        String encodedPassword= userOptional.get().getPassword();


        boolean success = passwordEncoder
                .matches(rawPassword,encodedPassword);
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
                .setName(userEntity.getFirstName() + " " + userEntity.getLastName())
                .setEmail(userEntity.getEmail());
    }

    public void logout() {
       currentUser.clear();
    }
}
