package pl.czubak.charityapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/403")
public class AccessDeniedController {

    @GetMapping
    public String getAccessDeniedPage() {
        return "403";
    }
}
