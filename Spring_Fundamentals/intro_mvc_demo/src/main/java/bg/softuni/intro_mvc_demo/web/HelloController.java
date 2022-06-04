package bg.softuni.intro_mvc_demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/index")
public class HelloController {

//    @GetMapping
//    public String HelloValeri(Model model) {
//        model.addAttribute("name", "Valeri");
//        return "index";
//    }

    @GetMapping("/index/{id}/test")
    public String hello(Model model,
                        @PathVariable("id") Integer id) {
        model.addAttribute("num", id);
        return "index";
    }
}
