package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.user.UserLoginDTO;
import bg.softuni.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userModel")
    public UserLoginDTO intUserModel() {
        return new UserLoginDTO();
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
    public String login(UserLoginDTO userLoginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !this.userService.login(userLoginDTO)) {

            redirectAttributes.addFlashAttribute("userModel", userLoginDTO);

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                    bindingResult);

            bindingResult.rejectValue("password", "InvalidPasswordError", "Invalid password");
            return "redirect:/users/login";
        }

        return "redirect:/";
    }





}
