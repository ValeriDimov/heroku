package bg.softuni.Mine_mobilelele.web;

import bg.softuni.Mine_mobilelele.models.DTOs.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users/register")
public class RegisterController {

    @GetMapping
    public String getUserRegister() {
        return "auth-register";
    }

    @PostMapping
    public String addUser(UserDTO userDTO) {
        System.out.println(userDTO);
        return "redirect:/index";
    }

}
