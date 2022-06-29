package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.DTO.UserLoginDTO;
import bg.softuni.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

    private UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/user/login")
    public String login(UserLoginDTO userLoginDTO) {

        System.out.println("User is logged: " + userService.login(userLoginDTO));
        return "redirect:/";
    }

}
