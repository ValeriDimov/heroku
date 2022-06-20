package bg.softuni.battleships.controllers;

import bg.softuni.battleships.models.dtos.FireDTO;
import bg.softuni.battleships.services.ShipService;
import bg.softuni.battleships.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class HomeController {

    private final UserService userService;
    private final ShipService shipService;

    public HomeController(UserService userService, ShipService shipService) {
        this.userService = userService;
        this.shipService = shipService;
    }

    @ModelAttribute("fireDTO")
    public FireDTO initFireDTO() {
        return new FireDTO();
    }

//    @GetMapping("/home")
//    public String home() {
//        return "home";
//
//    }

    @GetMapping("/home")
    public String home(Model model) {

        if (model.containsAttribute("fireDTO")) {
            model.addAttribute("fireDTO", new FireDTO());
        }

        model.addAttribute("attackerShips", this.shipService.getAttackerShips());
        model.addAttribute("defenderShips", this.shipService.getDefenderShips());
        model.addAttribute("allShips", this.shipService.getAllShips());

        return "home";
    }

    @GetMapping("/logout")
    public String logout(UserService userService) {

        this.userService.logout();
        return "redirect:/";
    }

    @PostMapping("/home")
    public String fire(@Valid FireDTO fireDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("fireDTO", fireDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.fireDTO",
                    bindingResult);

            return "redirect:/home";

        }

        this.shipService.fireNow(fireDTO);

        return "redirect:/home";
    }
}
