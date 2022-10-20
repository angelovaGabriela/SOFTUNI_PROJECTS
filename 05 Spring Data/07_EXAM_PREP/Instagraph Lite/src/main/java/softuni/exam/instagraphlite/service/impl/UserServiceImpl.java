package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.Picture;
import softuni.exam.instagraphlite.models.Post;
import softuni.exam.instagraphlite.models.User;
import softuni.exam.instagraphlite.models.dtos.importUsers.UsersImportDTO;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.UserService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private final static Path path = Path.of("src", "main", "resources", "files", "users.json");

    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    public UserServiceImpl(UserRepository userRepository,
                           PictureRepository pictureRepository) {
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.gson = new GsonBuilder().create();
        this.modelMapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {
        return this.userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importUsers() throws IOException {
        String json = this.readFromFileContent();
        UsersImportDTO[] users = this.gson.fromJson(json, UsersImportDTO[].class);

        List<String> result = new ArrayList<>();
        for (UsersImportDTO importUser : users) {
            Set<ConstraintViolation<UsersImportDTO>> validationErrors =
                    this.validator.validate(importUser);

            if (validationErrors.isEmpty()) {
                Optional<Picture> picture = this.pictureRepository.findByPath(importUser.getProfilePicture());
            if (picture.isEmpty()){
                result.add("Invalid user");
            } else {
                User user = this.modelMapper.map(importUser, User.class);
                user.setProfilePicture(picture.get());

                this.userRepository.save(user);

                String message = String.format("Successfully imported User: %s", user.getUsername());
                result.add(message);
            }

            } else {
                result.add("Invalid user");
            }
        }
        return String.join("\n", result);
    }

        @Override
        public String exportUsersWithTheirPosts () {
            List<User> result = new ArrayList<>();


            userRepository.getAllUsersOrderedByCountOfPostsThenById()
                    .forEach(user -> {
                        Set<Post> sortedPosts = user.getPosts()
                                .stream().sorted(Comparator.comparing(postOne -> postOne.getPicture().getSize())).collect(Collectors.toCollection(LinkedHashSet::new));
                        user.setPosts(sortedPosts);
                        result.add(user);
                    });
            return result.stream().map(User::toString).collect(Collectors.joining("\n"));

    }

 @Override
 public User findUserByUsername(String name) {
 return userRepository.findUserByUsername(name);

 }
    }


