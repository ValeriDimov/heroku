package bg.softuni.timeforschool.web;

import bg.softuni.timeforschool.exception.ObjectNotFoundException;
import bg.softuni.timeforschool.model.dto.SchoolDetailDTO;
import bg.softuni.timeforschool.model.dto.SearchSchoolDTO;
import bg.softuni.timeforschool.service.CityService;
import bg.softuni.timeforschool.service.DistrictService;
import bg.softuni.timeforschool.service.SchoolService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

@Controller
public class SchoolController {

    private final SchoolService schoolService;
    private final DistrictService districtService;
    private final CityService cityService;

    public SchoolController(SchoolService schoolService, DistrictService districtService, CityService cityService) {
        this.schoolService = schoolService;
        this.districtService = districtService;
        this.cityService = cityService;
    }

    @GetMapping("/schools/all")
    public String allSchools(
            Model model,
            @PageableDefault(
                    sort = "id",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 5) Pageable pageable) {

        model.addAttribute("schools", schoolService.getAllSchools(pageable));

        return "schools";
    }

    @GetMapping("/schools/search")
    public String searchQuery(@Valid SearchSchoolDTO searchSchoolDTO,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchSchoolModel", searchSchoolDTO);
            model.addAttribute(
                    "org.springframework.validation.BindingResult.searchSchoolModel",
                    bindingResult);
            return "school-search";
        }
//        model.addAttribute("districts", districtService.getAllDistricts());
//        model.addAttribute("cities", cityService.getAllCities());

        if (!model.containsAttribute("searchSchoolModel")) {
            model.addAttribute("searchSchoolModel", searchSchoolDTO);
        }


        if (!searchSchoolDTO.isEmpty() || searchSchoolDTO.getDistrictName() != null) {
            model.addAttribute("schools", schoolService.searchSchool(searchSchoolDTO));
        }

        return "school-search";
    }

    @GetMapping("/schools/{id}")
    public String getSchoolDetail(@PathVariable("id") Long id,
                                 Model model) {

        SchoolDetailDTO schoolDetailDTO =
                schoolService.findSchoolByID(id).
                        orElseThrow(() -> new ObjectNotFoundException("School with ID " +
                                id + " not found!"));

        model.addAttribute("school", schoolDetailDTO);

        return "school-details";
    }

}
