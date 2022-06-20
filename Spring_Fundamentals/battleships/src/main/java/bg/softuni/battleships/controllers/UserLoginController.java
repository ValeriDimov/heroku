package bg.softuni.battleships.controllers;

import bg.softuni.battleships.models.dtos.UserLoginDTO;
import bg.softuni.battleships.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserLoginController {

    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userLoginDTO")
    public UserLoginDTO initUserLoginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.userService.loggedUser(userLoginDTO)) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO",
                    bindingResult);

            return "redirect:/login";
        }

        this.userService.loggedUser(userLoginDTO);

        return "redirect:/home";
    }
}
