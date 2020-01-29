package pl.czubak.charityapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.czubak.charityapp.entity.User;
import pl.czubak.charityapp.repository.UserRepository;
import pl.czubak.charityapp.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

  private UserService userService;

  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String getRegistrationPage(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping
  public String processRegistration(@ModelAttribute User user) {
    userService.saveUser(user);
    return "redirect:/login";
  }
}
