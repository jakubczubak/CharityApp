package pl.czubak.charityapp.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.czubak.charityapp.config.entity.Donation;
import pl.czubak.charityapp.config.entity.Institution;
import pl.czubak.charityapp.config.repository.CategoryRepository;
import pl.czubak.charityapp.config.repository.InstitutionRepository;

@Controller
@RequestMapping("/donation")
public class DonationController {

    private CategoryRepository categoryRepository;
    private InstitutionRepository institutionRepository;

    public DonationController(CategoryRepository categoryRepository, InstitutionRepository institutionRepository){
        this.categoryRepository=categoryRepository;
        this.institutionRepository=institutionRepository;
    }

    @GetMapping("")
    public String getDonationPage(Model model){

        model.addAttribute("donation", new Donation());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        return "form";
    }


    @PostMapping("")
    public String processDonation(){
        return "form-confirmation";
    }


}
