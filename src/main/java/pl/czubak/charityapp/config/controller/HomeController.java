package pl.czubak.charityapp.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.czubak.charityapp.config.entity.Category;
import pl.czubak.charityapp.config.entity.Institution;
import pl.czubak.charityapp.config.repository.CategoryRepository;
import pl.czubak.charityapp.config.repository.DonationRepository;
import pl.czubak.charityapp.config.repository.InstitutionRepository;
import pl.czubak.charityapp.config.service.DonationService;

import java.util.List;

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
        Category category = new Category();
        category.setName("sadasdasd");
        Institution institution = new Institution();
        institution.setName("Asdasdasd");
        institution.setDescription("adsdasdasdasd");
        institutionRepository.save(institution);
        categoryRepository.save(category);

        return "index";
    }
}

