package bg.softuni.timeforschool.web;

import bg.softuni.timeforschool.exception.ObjectNotFoundException;
import bg.softuni.timeforschool.model.dto.CreateOrUpdateOfferDTO;
import bg.softuni.timeforschool.model.dto.OfferDetailDTO;
import bg.softuni.timeforschool.model.dto.SearchOfferDTO;
import bg.softuni.timeforschool.model.user.TimeForSchoolUserDetails;
import bg.softuni.timeforschool.service.OfferService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OfferController {

    private CreateOrUpdateOfferDTO offer = new CreateOrUpdateOfferDTO();

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/offers/all")
    public String allOffers(
        Model model,
        @PageableDefault(
            sort = "id",
            direction = Sort.Direction.DESC,
            page = 0,
            size = 3) Pageable pageable) {

        model.addAttribute("offers", offerService.getAllOffers(pageable));

        return "offers";
    }

    @GetMapping("/offers/add")
    public String addOffer(Model model) {
        if (!model.containsAttribute("addOfferCourse")) {
            model.addAttribute("addOfferCourse", new CreateOrUpdateOfferDTO());
        }

        return "offer-add";
    }

    @PostMapping("/offers/add")
    public String addOffer(@Valid CreateOrUpdateOfferDTO addOfferCourse,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal TimeForSchoolUserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferCourse", addOfferCourse);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferCourse",
                    bindingResult);
            return "redirect:/offers/add";
        }

        offerService.addOffer(addOfferCourse, userDetails);

        return "redirect:/offers/all";
    }



    @GetMapping("/offers/search")
    public String searchQuery(@Valid SearchOfferDTO searchOfferDTO,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchOfferModel", searchOfferDTO);
            model.addAttribute(
                    "org.springframework.validation.BindingResult.searchOfferModel",
                    bindingResult);
            return "offer-search";
        }

        if (!model.containsAttribute("searchOfferModel")) {
            model.addAttribute("searchOfferModel", searchOfferDTO);
        }

        if (!searchOfferDTO.isEmpty() || searchOfferDTO.getCourse() != null) {
            model.addAttribute("offers", offerService.searchOffer(searchOfferDTO));
        }

        return "offer-search";
    }
    @PreAuthorize("isOwner(#id)")
    @PostMapping("/offers/{id}/edit")
    public String edit(@PathVariable("id") Long id,
                       Model model) {
        offer = offerService.getOfferEditDetails(id).
            orElseThrow(() -> new ObjectNotFoundException("Offer with ID "+ id + "not found"));

        model.addAttribute("offer", offer);

        return "redirect:/offers/edit";
    }

    @GetMapping("/offers/edit")
    public String editOffer(Model model) {
        if (!model.containsAttribute("offerEdit")) {
            model.addAttribute("offerEdit", offer);
        }

        return "offer-edit";
    }

    @PostMapping("/offers/edit")
    public String editOffer(@Valid CreateOrUpdateOfferDTO offer,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal TimeForSchoolUserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferCourse", offer);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offer",
                    bindingResult);
            return "redirect:/offers/edit";
        }

        offerService.editOffer(offer, userDetails);

        return "redirect:/offers/all";
    }

//    @PreAuthorize("@offerService.isOwner(#principal.name, #id)")
    @PreAuthorize("isOwner(#id)")
    @DeleteMapping("/offers/{id}")
    public String deleteOffer(
        @PathVariable("id") Long id) {
        offerService.deleteOfferById(id);

        return "redirect:/offers/all";
    }

    @GetMapping("/offers/{id}")
    public String getOfferDetail(@PathVariable("id") Long id,
                                 Model model) {

        OfferDetailDTO offerDto =
            offerService.findOfferByID(id).
                orElseThrow(() -> new ObjectNotFoundException("Offer with ID " +
                    id + " not found!"));

        model.addAttribute("offer", offerDto);

        return "details";
    }

}
