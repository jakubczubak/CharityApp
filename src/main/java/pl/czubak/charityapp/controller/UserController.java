package pl.czubak.charityapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.czubak.charityapp.entity.User;
import pl.czubak.charityapp.repository.UserRepository;
import pl.czubak.charityapp.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
@SessionAttributes("fullName")
public class UserController {

    private UserRepository userRepository;
    private UserService userService;

    public UserController(UserRepository userRepository, UserService userService){
        this.userRepository=userRepository;
        this.userService=userService;
    }

    @GetMapping("/edit")
    public String editUser(Model model, Principal principal){
        User currentUser = userRepository.findByEmail(principal.getName());
        model.addAttribute("fullName", currentUser.getFullName());
        model.addAttribute("user", currentUser);
        return "user-edit-page";
    }

    @PostMapping("/edit")
    public String processEditUser(@ModelAttribute User user, WebRequest request){
        userService.saveUser(user);
        request.removeAttribute("fullName", WebRequest.SCOPE_SESSION);
        return "redirect:/user/edit";
    }
}
