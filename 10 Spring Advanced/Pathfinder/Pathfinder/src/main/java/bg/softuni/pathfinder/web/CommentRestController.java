package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.CommentCreationDTO;
import bg.softuni.pathfinder.model.dto.CommentMessageDTO;
import bg.softuni.pathfinder.model.views.CommentDisplayView;
import bg.softuni.pathfinder.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/")
public class CommentRestController {
    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/{routeId}/comments", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CommentDisplayView> createComment(@PathVariable("routeId") Long routeId,
                                                           @AuthenticationPrincipal UserDetails userDetails,

                                                           @RequestBody CommentMessageDTO commentMessageDTO) {

        CommentCreationDTO commentCreationDTO = new CommentCreationDTO(
                userDetails.getUsername(), // current logged user who created the comment
                routeId,
                commentMessageDTO.getMessage()
        );

        CommentDisplayView comment = commentService.createComment(commentCreationDTO);
        return ResponseEntity
                .created(URI.create(String.format("api/%d/comments/%d", routeId, comment.getId())))
                .body(comment);

    }
}


// GET /api/{routeId}/comments -> returns the list of the route
// POST /api/{routeId}/comments -> creates comment to the route and returns the comment just created
// * GET /api{routeId}/comments/{commentId} -> returns specific comment by id