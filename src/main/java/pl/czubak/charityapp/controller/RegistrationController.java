package pl.czubak.charityapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @GetMapping("")
    public String getRegistrationPage(){
        return "register";
    }

    @PostMapping("")
    public String processRegistration(){
        return "";
    }

}
