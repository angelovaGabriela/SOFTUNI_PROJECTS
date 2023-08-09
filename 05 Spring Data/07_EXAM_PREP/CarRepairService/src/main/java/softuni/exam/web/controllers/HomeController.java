package softuni.exam.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.service.MechanicService;
import softuni.exam.service.PartService;
import softuni.exam.service.TasksService;
import softuni.exam.service.CarService;

@Controller
public class HomeController extends BaseController {

    private final PartService partService;
    private final TasksService tasksService;
    private final CarService carService;
    private final MechanicService mechanicService;

    @Autowired
    public HomeController(PartService partService, TasksService tasksService, CarService carService, MechanicService mechanicService) {
        this.partService = partService;
        this.tasksService = tasksService;
        this.carService = carService;
        this.mechanicService = mechanicService;
    }


    @GetMapping("/")
    public ModelAndView index() {
        boolean areImported = this.partService.areImported() &&
                this.carService.areImported() &&
                this.partService.areImported() &&
                this.tasksService.areImported();

        return super.view("index", "areImported", areImported);
    }
}
