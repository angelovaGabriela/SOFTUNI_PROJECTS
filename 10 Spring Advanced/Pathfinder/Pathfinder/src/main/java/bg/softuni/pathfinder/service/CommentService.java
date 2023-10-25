package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.exceptions.RouteNotFoundException;
import bg.softuni.pathfinder.model.Comment;
import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.model.dto.CommentCreationDTO;
import bg.softuni.pathfinder.model.views.CommentDisplayView;
import bg.softuni.pathfinder.repository.CommentRepository;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final RouteRepository routeRepository;
    private final UserRepository userRepository;

    private final CommentRepository commentRepository;
    public CommentService(RouteRepository routeRepository,
                          UserRepository userRepository,
                          CommentRepository commentRepository) {
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public List<CommentDisplayView> getAllCommentsForRoute(Long routeId) {
      Route route = this.routeRepository.findById(routeId).orElseThrow(RouteNotFoundException::new);

        List<Comment> comments = commentRepository.findAllByRoute(route).get();

        return comments.stream().map(comment ->
                new CommentDisplayView(
                        comment.getId(),
                        comment.getAuthor().getFullName(),
                        comment.getText()))
                .collect(Collectors.toList());

    }

    public CommentDisplayView createComment(CommentCreationDTO commentCreationDTO) {
        User author = userRepository.findByUsername(commentCreationDTO.getUsername()).get();


        Comment comment = new Comment();
            comment.setCreated(LocalDateTime.now());
            comment.setRoute(routeRepository.getById(commentCreationDTO.getRouteId()));
            comment.setAuthor(author);
            comment.setApproved(true);
            comment.setText(commentCreationDTO.getMessage());

            commentRepository.save(comment);

            return new CommentDisplayView(
                    comment.getId(), author.getFullName(), comment.getText());
    }

}
