package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.Picture;
import softuni.exam.instagraphlite.models.Post;
import softuni.exam.instagraphlite.models.User;
import softuni.exam.instagraphlite.models.dtos.importPosts.PostImport;
import softuni.exam.instagraphlite.models.dtos.importPosts.RootPostsImportDTO;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PostService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {
    private final static Path path = Path.of("src", "main", "resources", "files", "posts.xml");

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;

    private final ModelMapper modelMapper;
    private final Unmarshaller unmarshaller;
    private final Validator validator;

    public PostServiceImpl(PostRepository postRepository,
                           UserRepository userRepository,
                           PictureRepository pictureRepository) throws JAXBException {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;

        this.modelMapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

        JAXBContext context = JAXBContext.newInstance(RootPostsImportDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importPosts() throws IOException, JAXBException {
        RootPostsImportDTO posts = (RootPostsImportDTO) this.unmarshaller.unmarshal(new FileReader(path.toAbsolutePath().toString()));

        List<String> result = new ArrayList<>();
        for (PostImport postImport : posts.getPosts()) {
            Set<ConstraintViolation<PostImport>> validationErrors =
                    this.validator.validate(postImport);

            if (validationErrors.isEmpty()) {
                Optional<Picture> picture = this.pictureRepository.findByPath(postImport.getPicture().getPath());
                Optional<User> user = this.userRepository.findByUsername(postImport.getUser().getUsername());

                if (picture.isEmpty() || user.isEmpty()) {
                    result.add("Invalid post");
                } else {

                    Post post = this.modelMapper.map(postImport, Post.class);
                    post.setPicture(picture.get());
                    post.setUser(user.get());

                    this.postRepository.save(post);

                    String message = String.format("Successfully imported Post, made by %s", post.getUser().getUsername());
                    result.add(message);
                }
            } else {
                result.add("Invalid post");
            }
        }

        return String.join("\n", result);
    }
}
