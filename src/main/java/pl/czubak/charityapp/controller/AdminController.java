package pl.czubak.charityapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.czubak.charityapp.entity.Donation;
import pl.czubak.charityapp.entity.User;
import pl.czubak.charityapp.repository.InstitutionRepository;
import pl.czubak.charityapp.repository.UserRepository;
import pl.czubak.charityapp.service.DonationService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserRepository userRepository;
    private DonationService donationService;
    private InstitutionRepository institutionRepository;
    public AdminController(UserRepository userRepository, DonationService donationService, InstitutionRepository institutionRepository){
        this.userRepository=userRepository;
        this.donationService=donationService;
        this.institutionRepository=institutionRepository;
    }
    @GetMapping
    public String getAdminPage(Model model, Principal principal){
        User currentUser = userRepository.findByEmail(principal.getName());
        model.addAttribute("fullName", currentUser.getFullName());
        model.addAttribute("donationAmount", donationService.donationAmount());
        model.addAttribute("AmountOfGoodPeople",userRepository.findAll().size());
        model.addAttribute("AmountOfTrustedInstitution",institutionRepository.findAll());
        model.addAttribute("AmountOfAdmins", userRepository.findByAdmin(true));
        return "adminPage";
    }
}
