package bg.softuni.timeforschool.web;

import bg.softuni.timeforschool.model.dto.UserRegisterDTO;
import bg.softuni.timeforschool.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserRegistrationController {

  private final UserService userService;
  private final LocaleResolver localeResolver;

  public UserRegistrationController(UserService userService,
                                    LocaleResolver localeResolver) {
    this.userService = userService;
    this.localeResolver = localeResolver;
  }

  @ModelAttribute("userModel")
  public UserRegisterDTO initUserModel() {
    return new UserRegisterDTO();
  }

  @GetMapping("/register")
  public String register() {
    return "register";
  }

  @PostMapping("/register")
  public String register(@Valid UserRegisterDTO userModel,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         HttpServletRequest request) {

    if (bindingResult.hasErrors() || !userService.passMatcher(userModel.getPassword(), userModel.getConfirmPassword())) {
      redirectAttributes.addFlashAttribute("userModel", userModel);
      redirectAttributes.addFlashAttribute("passMatcher", true);
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
              bindingResult);

      return "redirect:/users/register";
    }

    this.userService.registerAndLogin(
        userModel,
        localeResolver.resolveLocale(request));

    return "redirect:/";
  }
}
