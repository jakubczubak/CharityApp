package pl.czubak.charityapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.czubak.charityapp.entity.User;
import pl.czubak.charityapp.repository.UserRepository;
import pl.czubak.charityapp.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

  private UserService userService;
  private UserRepository userRepository;

  public RegistrationController(UserService userService, UserRepository userRepository) {
    this.userService = userService;
    this.userRepository = userRepository;
  }

  @GetMapping
  public String getRegistrationPage(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping
  public String processRegistration(@Valid @ModelAttribute User user, BindingResult result) {
    User existUser = userRepository.findByEmail(user.getEmail());
    if (existUser != null) {
      result.rejectValue("email", "error.user", "Podany email istnieje w naszej bazie danych :)");
      return "register";
    }
    if (!user.getPassword().equals(user.getRePassword())) {
      result.rejectValue("password", "error.user", "Wpisane hasła różnią się od siebie");
      return "register";
    }
    if (result.hasErrors()) {
      return "register";
    }

    userService.saveUser(user);
    return "redirect:/login";
  }
}
