package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.model.views.RouteIndexView;
import bg.softuni.pathfinder.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;



    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public String getAllRoutes(Model model) {
        List<RouteIndexView> routes = this.routeService.getAllRoutes();
        model.addAttribute("routes", routes);

        return "routes";

    }

}
