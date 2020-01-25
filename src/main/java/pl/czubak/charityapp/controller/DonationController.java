package pl.czubak.charityapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.czubak.charityapp.entity.Category;
import pl.czubak.charityapp.entity.Donation;
import pl.czubak.charityapp.entity.Institution;
import pl.czubak.charityapp.entity.User;
import pl.czubak.charityapp.repository.CategoryRepository;
import pl.czubak.charityapp.repository.DonationRepository;
import pl.czubak.charityapp.repository.InstitutionRepository;
import pl.czubak.charityapp.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/donation")
public class DonationController {

    private CategoryRepository categoryRepository;
    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;
    private UserRepository userRepository;

    public DonationController(CategoryRepository categoryRepository, InstitutionRepository institutionRepository, DonationRepository donationRepository, UserRepository userRepository){
        this.categoryRepository=categoryRepository;
        this.institutionRepository=institutionRepository;
        this.donationRepository=donationRepository;
        this.userRepository=userRepository;
    }

    @GetMapping
    public String getDonationPage(Model model, Principal principal, HttpServletRequest request) {

        User currentUser =  userRepository.findByEmail(principal.getName());
        model.addAttribute("fullName", currentUser.getFullName());
        model.addAttribute("donation", new Donation());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        return "form";
    }


    @PostMapping
    public String processDonation(@RequestParam(value = "category", required = false) List<Category> categories, @RequestParam(value = "organization", required = false) Institution institution, @ModelAttribute Donation donation, Principal principal){
        User currentUser = userRepository.findByEmail(principal.getName());
        donation.setUser(currentUser);
        donation.setInstitution(institution);
        donation.setCategories(categories);
        donationRepository.save(donation);
        return "form-confirmation";
    }


}
