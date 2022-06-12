package bg.softuni.Mine_mobilelele.web;

import bg.softuni.Mine_mobilelele.models.DTOs.UserLoginDTO;
import bg.softuni.Mine_mobilelele.models.DTOs.UserRegisterDTO;
import bg.softuni.Mine_mobilelele.service.UserService;
import bg.softuni.Mine_mobilelele.user.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @GetMapping("/logout")
    public String logout(CurrentUser currentUser) {
        userService.logoutUser();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(UserLoginDTO userLoginDTO) {
        userService.login(userLoginDTO);
        return "redirect:/";
    }

}
