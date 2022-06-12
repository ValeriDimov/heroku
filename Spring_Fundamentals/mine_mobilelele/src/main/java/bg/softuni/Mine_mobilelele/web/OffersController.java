package bg.softuni.Mine_mobilelele.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OffersController {

    @GetMapping("/all")
    public String getAllOffers() {
        return "offers";
    }

    @GetMapping("/add")
    public String getAddOffer() {
        return "offer-add";
    }

}
