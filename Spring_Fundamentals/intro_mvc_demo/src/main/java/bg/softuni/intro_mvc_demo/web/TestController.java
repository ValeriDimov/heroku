package bg.softuni.intro_mvc_demo.web;

import bg.softuni.intro_mvc_demo.model.entity.IntroDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String newTest(){
        return "test";
    }

    @PostMapping("")
    public String addName(IntroDTO introDTO) {
        System.out.println("this is the name of the: " + introDTO);
        return "redirect:/test";
    }
}
