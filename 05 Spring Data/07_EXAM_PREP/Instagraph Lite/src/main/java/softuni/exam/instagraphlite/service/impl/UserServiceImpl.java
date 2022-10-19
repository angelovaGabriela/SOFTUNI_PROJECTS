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
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.UserService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final static Path path = Path.of("src", "main", "resources", "files", "users.json");

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    public UserServiceImpl(PostRepository postRepository,
                           UserRepository userRepository,
                           PictureRepository pictureRepository) {
        this.postRepository = postRepository;
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
                Optional<Post> post = this.postRepository.findPostByUserUsername(importUser.getUsername());
                user.getPosts().add(post.get());

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
        //Export all users with their posts ordered by count of posts descending, then by user id

            List<String> result = new ArrayList<>();

            List<User> users = this.userRepository.findByOrderByPostsDescIdAsc();


            // Order the posts, inside each user, by the post's picture size in ascending order


            for (User user : users) {
                List <Post> posts = this.postRepository.findPostByUserUsernameOrderByPictureSizeAsc(user.getUsername());
                for (Post post : posts) {
                    result.add(user.toString());
                    result.add(post.toString());
                }
            }

            return String.join("\n", result);
        }
    }


