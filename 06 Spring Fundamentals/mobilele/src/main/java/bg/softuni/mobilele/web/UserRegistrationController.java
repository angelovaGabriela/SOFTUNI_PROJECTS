package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.DTO.UserRegisterDTO;
import bg.softuni.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {
    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }
    @ModelAttribute("userModel")
    public void initUserModel(Model model) {

        model.addAttribute("userModel", new UserRegisterDTO());
    }

    @GetMapping("/users/register")
    public String register(){
        return "auth-register";
    }

    @PostMapping("/users/register")
    public String register(@Valid UserRegisterDTO userModel,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "redirect:/users/register";
        }
        userService.registerAndLogin(userModel);

        return "redirect:/";
    }

}
