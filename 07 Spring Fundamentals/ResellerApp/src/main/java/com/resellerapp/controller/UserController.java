package com.resellerapp.controller;

import com.resellerapp.model.binding.LoginUserBindingModel;
import com.resellerapp.model.binding.RegisterUserBindingModel;
import com.resellerapp.model.service.UserServiceModel;
import com.resellerapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @PostMapping("/register")
    public String confirmRegister(@Valid RegisterUserBindingModel registerUserBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors() ||
                !registerUserBindingModel.getPassword().equals(registerUserBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("registerUserBindingModel", registerUserBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerUserBindingModel", bindingResult);

            return "redirect:register";
        }
        this.userService.registerUser(modelMapper.map(registerUserBindingModel, UserServiceModel.class));
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
        }

        return "login";
    }


    @PostMapping("/login")
    public String confirmLogin(@Valid LoginUserBindingModel loginUserBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginUserBindingModel", loginUserBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginUserBindingModel", bindingResult);

            return "redirect:login";
        }

      UserServiceModel userServiceModel = this.userService.findByUsernameAndPassword(loginUserBindingModel.getUsername(), loginUserBindingModel.getPassword());

        if (userServiceModel == null) {
            redirectAttributes.addFlashAttribute("loginUserBindingModel", loginUserBindingModel);
            redirectAttributes.addFlashAttribute("isFound", false);
            return "redirect:login";
        }

        userService.loginUser(userServiceModel.getId(), loginUserBindingModel.getUsername());


        return "redirect:/";

    }

    @ModelAttribute
    public RegisterUserBindingModel registerUserBindingModel() {
        return new RegisterUserBindingModel();
    }

    @ModelAttribute
    public LoginUserBindingModel loginUserBindingModel() {
        return new LoginUserBindingModel();
    }

}
