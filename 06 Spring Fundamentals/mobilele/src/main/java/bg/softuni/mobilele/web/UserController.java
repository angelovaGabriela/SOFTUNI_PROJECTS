package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.DTO.UserLoginDTO;
import bg.softuni.mobilele.model.DTO.UserRegisterDTO;
import bg.softuni.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login() {
        return "auth-login";
    }

    @GetMapping("users/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }

    @PostMapping("users/login")
    public String login(UserLoginDTO userLoginDTO) {
        userService.login(userLoginDTO);
        return "redirect:/";
    }





}
