package pl.czubak.charityapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.czubak.charityapp.entity.Category;
import pl.czubak.charityapp.entity.Donation;
import pl.czubak.charityapp.entity.Institution;
import pl.czubak.charityapp.repository.CategoryRepository;
import pl.czubak.charityapp.repository.DonationRepository;
import pl.czubak.charityapp.repository.InstitutionRepository;

import java.util.List;

@Controller
@RequestMapping("/donation")
public class DonationController {

    private CategoryRepository categoryRepository;
    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;

    public DonationController(CategoryRepository categoryRepository, InstitutionRepository institutionRepository, DonationRepository donationRepository){
        this.categoryRepository=categoryRepository;
        this.institutionRepository=institutionRepository;
        this.donationRepository=donationRepository;
    }

    @GetMapping
    public String getDonationPage(Model model){

        model.addAttribute("donation", new Donation());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        return "form";
    }


    @PostMapping
    public String processDonation(@RequestParam(value = "category", required = false) List<Category> categories, @RequestParam(value = "organization", required = false) Institution institution, @ModelAttribute Donation donation){
        donation.setInstitution(institution);
        donation.setCategories(categories);
        donationRepository.save(donation);
        return "form-confirmation";
    }


}
