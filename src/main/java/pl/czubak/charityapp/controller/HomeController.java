package pl.czubak.charityapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.czubak.charityapp.entity.Category;
import pl.czubak.charityapp.entity.Institution;
import pl.czubak.charityapp.repository.CategoryRepository;
import pl.czubak.charityapp.repository.DonationRepository;
import pl.czubak.charityapp.repository.InstitutionRepository;
import pl.czubak.charityapp.service.DonationService;

@Controller
public class HomeController {

    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;
    private DonationService donationService;
    private CategoryRepository categoryRepository;
    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, DonationService donationService, CategoryRepository categoryRepository){
        this.donationRepository=donationRepository;
        this.institutionRepository=institutionRepository;
        this.donationService=donationService;
        this.categoryRepository=categoryRepository;
    }
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("numberOfSupportedInstitutions", donationRepository.numberOfSupportedInstitutions());
        model.addAttribute("donationAmount", donationService.donationAmount());
        return "index";
    }
}

