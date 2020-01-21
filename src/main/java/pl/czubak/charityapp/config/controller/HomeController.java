package pl.czubak.charityapp.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.czubak.charityapp.config.repository.DonationRepository;
import pl.czubak.charityapp.config.repository.InstitutionRepository;
import pl.czubak.charityapp.config.service.DonationService;

import java.util.List;

@Controller
public class HomeController {

    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;
    private DonationService donationService;
    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, DonationService donationService){
        this.donationRepository=donationRepository;
        this.institutionRepository=institutionRepository;
        this.donationService=donationService;
    }
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("numberOfSupportedInstitutions", donationRepository.numberOfSupportedInstitutions());
        model.addAttribute("donationAmount", donationService.donationAmount());
        return "index";
    }
}

