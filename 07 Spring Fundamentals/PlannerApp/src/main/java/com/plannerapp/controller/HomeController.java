package com.plannerapp.controller;

import com.plannerapp.service.HomeService;
import com.plannerapp.service.TaskService;
import com.plannerapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final TaskService taskService;
    private final HomeService homeService;

    public HomeController(CurrentUser currentUser,
                          TaskService taskService,
                          HomeService homeService) {
        this.currentUser = currentUser;
        this.taskService = taskService;
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }


        model.addAttribute("tasks", taskService.findAllTasks());
        model.addAttribute("allTasks", this.taskService.getTotalTaskNumber());
        model.addAttribute("assignedTasks", this.taskService.getAssignedTasks(currentUser.getId()));

        //TODO: assignedTasks visualization
        return "home";
    }

    @GetMapping("/task/add/{id}")
    public String addSongToPlaylist(@PathVariable("id") Long id) {

        if (currentUser.getId() == null) {
            return "redirect:/login";
        }

        this.homeService.assignTask(id, this.currentUser.getId());
        return "redirect:/";
    }


}
