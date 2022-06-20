package bg.softuni.battleships.controllers;

import bg.softuni.battleships.models.dtos.AddShipDTO;
import bg.softuni.battleships.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AddShipController {

    private final UserService userService;

    public AddShipController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("addShipDTO")
    public AddShipDTO initaddShipDTO() {
        return new AddShipDTO();
    }

    @GetMapping("/ship-add")
    public String addShip() {
        return "ship-add";
    }

    @PostMapping("/ship-add")
    public String addShip(@Valid AddShipDTO addShipDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.userService.addShip(addShipDTO)) {
            redirectAttributes.addFlashAttribute("addShipDTO", addShipDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addShipDTO",
                    bindingResult);

            return "/ship-add";
        }

        this.userService.addShip(addShipDTO);

        return "redirect:/home";
    }
}
