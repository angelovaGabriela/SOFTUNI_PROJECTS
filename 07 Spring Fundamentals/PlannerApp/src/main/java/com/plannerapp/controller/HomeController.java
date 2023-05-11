package com.plannerapp.controller;

import com.plannerapp.service.HomeService;
import com.plannerapp.service.TaskService;
import com.plannerapp.service.UserService;
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

    private final UserService userService;

    public HomeController(CurrentUser currentUser,
                          TaskService taskService,
                          HomeService homeService,
                          UserService userService) {
        this.currentUser = currentUser;
        this.taskService = taskService;
        this.homeService = homeService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }


        model.addAttribute("tasks", taskService.findAllTasks());
        model.addAttribute("allTasks", this.taskService.getTotalTaskNumber());
        model.addAttribute("assignedTasks", this.userService.getAssignedTasks(currentUser.getId()));

        return "home";
    }

    @GetMapping("/task/add/{id}")
    public String assignTaskToUser(@PathVariable("id") Long id) {

        if (currentUser.getId() == null) {
            return "redirect:/login";
        }

        this.homeService.assignTask(id, this.currentUser.getId());
        return "redirect:/";
    }


}
