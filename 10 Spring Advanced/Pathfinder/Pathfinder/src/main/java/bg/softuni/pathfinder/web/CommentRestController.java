package bg.softuni.pathfinder.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class CommentRestController {
}


// GET /api/{routeId}/comments -> returns the list of the route
// POST /api/{routeId}/comments -> creates comment to the route and returns the comment just created
// * GET /api{routeId}/comments/{commentId} -> returns specific comment by id