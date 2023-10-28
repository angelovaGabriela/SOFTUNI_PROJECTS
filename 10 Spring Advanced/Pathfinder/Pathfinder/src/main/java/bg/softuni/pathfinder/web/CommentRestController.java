package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.exceptions.ErrorApiResponse;
import bg.softuni.pathfinder.exceptions.RouteNotFoundException;
import bg.softuni.pathfinder.model.dto.CommentCreationDTO;
import bg.softuni.pathfinder.model.dto.CommentMessageDTO;
import bg.softuni.pathfinder.model.views.CommentDisplayView;
import bg.softuni.pathfinder.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentRestController {
    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{routeId}/comments")
    public ResponseEntity<List<CommentDisplayView>> getAllComments(@PathVariable("routeId") Long routeId) {
        return ResponseEntity.ok(commentService.getAllCommentsForRoute((routeId)));
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

    @ExceptionHandler({RouteNotFoundException.class})
    public ResponseEntity<ErrorApiResponse> handleRouteNotFound() {
        return ResponseEntity.status(404).body(new ErrorApiResponse("Such route doesn't exist!", 1004));
    }
}


// GET /api/{routeId}/comments -> returns the list of the route
// POST /api/{routeId}/comments -> creates comment to the route and returns the comment just created
// * GET /api{routeId}/comments/{commentId} -> returns specific comment by id